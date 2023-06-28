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

        KieSession session = kieContainer.newKieSession();
        List<TimePeriod> timePeriods = repository.findAll();

        // Add employees
        timePeriods.stream().map(TimePeriod::getEmployee).forEach(session::insert);

        // Add possible shifts
        List<TimePeriod> possibleShifts = generatePossibleShifts(timePeriods);
        possibleShifts.forEach(session::insert);

        // Add schedule requirements for each day of the week
        for (Day day : Day.values())
            session.insert(new ScheduleRequirements(2, day, 9, 10));

        session.fireAllRules();

        /* Retrieve the generated schedules
           Based on https://stackoverflow.com/questions/15035209/ */
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
                for (int start = earliestStart; start <= latestStart; start++) {
                    int end = start + shiftLength;
                    shifts.add(new TimePeriod(availability.getEmployee(),
                            null, availability.getDay(), start, end, TimePeriod.Type.SHIFT));
                }
            }
        }

        return shifts;
    }
}
