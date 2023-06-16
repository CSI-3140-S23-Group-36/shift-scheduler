package shift.scheduler.app.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends User {

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @Where(clause = "type == 0")
    @Size(max = 7)
    private List<TimePeriod> availabilities;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @Where(clause = "type == 1")
    private List<TimePeriod> shifts;
}
