package shift.scheduler.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shift.scheduler.app.models.Manager;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUsername(String username);

    Boolean existsByUsername(String username);
}
