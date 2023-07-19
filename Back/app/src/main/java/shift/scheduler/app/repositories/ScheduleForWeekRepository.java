package shift.scheduler.app.repositories;

import org.springframework.data.repository.Repository;
import shift.scheduler.app.models.ScheduleForWeek;

import java.sql.Date;

public interface ScheduleForWeekRepository extends Repository<ScheduleForWeek, Long> {

    ScheduleForWeek findByFirstDayOfWeek(Date firstDayOfWeek);

    ScheduleForWeek save(ScheduleForWeek scheduleForWeek);
}
