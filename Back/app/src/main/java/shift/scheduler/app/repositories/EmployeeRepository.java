package shift.scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shift.scheduler.app.models.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    Boolean existsByUsername(String username);

}
