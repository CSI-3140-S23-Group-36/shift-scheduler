package shift.scheduler.app;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shift.scheduler.app.models.*;
import shift.scheduler.app.repositories.TimePeriodRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Remove this class later
 * A temporary class used to test Drools rules and queries while implementing
 * the shift scheduling algorithm.
 */
@Component
public class DroolsTester implements CommandLineRunner {

    private static final int SHIFT_LENGTH_DIVISOR = 4;

    private final KieContainer kieContainer;

    private final TimePeriodRepository repository;

    @Autowired
    public DroolsTester(KieContainer kieContainer, TimePeriodRepository repository) {

        this.kieContainer = kieContainer;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        // TODO: Remove to test Drools rules
        if (true) return;

        List<List<ScheduleForDay>> dailySchedules = new ArrayList<>();

        for (int i = 0; i < 7; i++)
            dailySchedules.add(new ArrayList<>());

        // Generate daily schedules
        for (Day day : Day.values()) {
            KieSession session = kieContainer.newKieSession();

            List<TimePeriod> timePeriods = repository.findByDay(day);

            // Add schedule requirements for the day
            session.insert(new ScheduleRequirements(2, day, 8, 16));

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
    }

    public List<TimePeriod> generatePossibleShifts(List<TimePeriod> availabilities) {

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
}
