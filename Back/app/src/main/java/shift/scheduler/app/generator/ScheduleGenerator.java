package shift.scheduler.app.generator;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shift.scheduler.app.models.*;
import shift.scheduler.app.repositories.ScheduleForWeekRepository;
import shift.scheduler.app.repositories.TimePeriodRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleGenerator {

    private static final int SHIFT_LENGTH_DIVISOR = 4;

    private static final long NUM_MILLIS_PER_DAY = 86_400_000;

    private final KieContainer kieContainer;

    private final TimePeriodRepository timePeriodRepository;

    private final ScheduleForWeekRepository scheduleForWeekRepository;

    @Autowired
    public ScheduleGenerator(KieContainer kieContainer, TimePeriodRepository timePeriodRepository,
                             ScheduleForWeekRepository scheduleForWeekRepository) {

        this.kieContainer = kieContainer;
        this.timePeriodRepository = timePeriodRepository;
        this.scheduleForWeekRepository = scheduleForWeekRepository;
    }

    public List<ScheduleForWeek> generateSchedules(ScheduleRequirements[] requirements) {

        List<List<ScheduleForDay>> dailySchedules = new ArrayList<>();

        for (int i = 0; i < 7; i++)
            dailySchedules.add(new ArrayList<>());

        // Generate daily schedules
        for (Day day : Day.values()) {
            KieSession session = kieContainer.newKieSession();

            List<TimePeriod> timePeriods = timePeriodRepository.findByTypeAndDay(TimePeriod.Type.AVAILABILITY, day);

            // Add schedule requirements for the day
            session.insert(requirements[day.ordinal()]);

            // Add employees
            timePeriods.stream().map(TimePeriod::getEmployee).forEach(session::insert);

            // Add possible shifts
            List<TimePeriod> possibleShifts = generatePossibleShifts(timePeriods);
            possibleShifts.forEach(session::insert);

            session.fireAllRules();

            /* Retrieve the generated schedules
               Based on https://stackoverflow.com/questions/15035209/ */
            List<ScheduleForDay> schedules = dailySchedules.get(day.ordinal());
            QueryResults results = session.getQueryResults("getDailySchedules");
            results.forEach(row -> schedules.add((ScheduleForDay) row.get("$dailySchedule")));

            session.dispose();
        }

        // Generate weekly schedules
        KieSession session = kieContainer.newKieSession();

        dailySchedules.forEach(scheduleList -> scheduleList.forEach(session::insert));

        session.fireAllRules();

        List<ScheduleForWeek> schedules = new ArrayList<>();
        QueryResults results = session.getQueryResults("getWeeklySchedules");
        results.forEach(row -> schedules.add((ScheduleForWeek) row.get("$schedule")));
        session.dispose();

        return schedules;
    }

    private List<TimePeriod> generatePossibleShifts(List<TimePeriod> availabilities) {

        List<TimePeriod> shifts = new ArrayList<>();

        for (TimePeriod availability : availabilities) {
            int shiftLength = availability.getEmployee().getMaxHoursPerDay();
            int earliestStart = availability.getStartHour();
            int latestStart = availability.getEndHour() - shiftLength;
            int maxHoursPerDay = availability.getEmployee().getMaxHoursPerDay();

            if ((availability.getEndHour() - earliestStart) < maxHoursPerDay)
            {
                /* Handle cases where the time period is shorter than the maximum number of hours that
                   an employee is willing to work per day */
                shifts.add(new TimePeriod(availability.getEmployee(), null, availability.getDay(),
                                          earliestStart, availability.getEndHour(), TimePeriod.Type.SHIFT));
            }
            else
            {
                for (int start = earliestStart; start <= latestStart; start += SHIFT_LENGTH_DIVISOR) {
                    int end = start + shiftLength;
                    shifts.add(new TimePeriod(availability.getEmployee(),
                            null, availability.getDay(), start, end, TimePeriod.Type.SHIFT));
                }
            }
        }

        return shifts;
    }

    public boolean saveShifts(ScheduleForWeek schedule) {

        /* Increment the first day of the week, since the date gets deserialized incorrectly to be one day
           sooner than the provided date; sql.Date could be replaced with some other class if time permits
           later */
        schedule.setFirstDayOfWeek(new Date(schedule.getFirstDayOfWeek().getTime() + NUM_MILLIS_PER_DAY));
        Date date = schedule.getFirstDayOfWeek();

        // Set the date of each shift before saving the schedule
        for (int i = 0; i < schedule.getDailySchedules().size(); i++) {
            long dateIncrement = i * NUM_MILLIS_PER_DAY;
            ScheduleForDay dailySchedule = schedule.getDailySchedules().get(i);

            dailySchedule.getShifts().forEach(shift -> shift.setDate(
                    new Date(date.getTime() + dateIncrement))
            );
        }

        try {
            scheduleForWeekRepository.save(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
