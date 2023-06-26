package shift.scheduler.app;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shift.scheduler.app.models.Day;
import shift.scheduler.app.models.Hour;
import shift.scheduler.app.models.ScheduleRequirements;
import shift.scheduler.app.models.TimePeriod;
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

        ScheduleRequirements requirements = new ScheduleRequirements(2, 8, 9);

        KieSession session = kieContainer.newKieSession();
        List<TimePeriod> timePeriods = repository.findByDay(Day.MON);
        List<TimePeriod> possibleShifts = generatePossibleShifts(timePeriods);

        session.insert(requirements);
        possibleShifts.forEach(session::insert);

        // Insert all hours of operation into the rule engine
        for (int i = requirements.getStartHour(); i <= requirements.getEndHour(); i++)
            session.insert(new Hour(i));

        session.fireAllRules();

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
