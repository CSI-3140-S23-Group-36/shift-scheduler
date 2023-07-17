package shift.scheduler.app.repositories;

import org.springframework.data.repository.Repository;
import shift.scheduler.app.models.ScheduleForWeek;

public interface ScheduleForWeekRepository extends Repository<ScheduleForWeek, Long> {

    ScheduleForWeek save(ScheduleForWeek scheduleForWeek);
}
