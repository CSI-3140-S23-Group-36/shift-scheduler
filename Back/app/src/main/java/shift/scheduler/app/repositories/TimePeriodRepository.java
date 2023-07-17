package shift.scheduler.app.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shift.scheduler.app.models.Day;
import shift.scheduler.app.models.TimePeriod;

import java.util.List;

@RepositoryRestResource
public interface TimePeriodRepository extends Repository<TimePeriod, Integer> {

    List<TimePeriod> findAll();

    List<TimePeriod> findByEmployeeId(Integer employeeId);

    List<TimePeriod> findByDay(Day day);

    List<TimePeriod> findByTypeAndDay(TimePeriod.Type type, Day day);

    TimePeriod save(TimePeriod timePeriod);

    @Modifying
    Integer deleteById(Integer id);
}
