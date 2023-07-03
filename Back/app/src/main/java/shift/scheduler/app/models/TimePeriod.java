package shift.scheduler.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "time_periods")
public class TimePeriod {

    public enum Type {
        AVAILABILITY,
        SHIFT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Employee employee;

    private Date date;

    @NotNull
    private Day day;

    @NotNull
    private Integer startHour;

    @NotNull
    private Integer endHour;

    @NotNull
    private Type type;

    public TimePeriod() {}

    public TimePeriod(Employee employee, Date date, Day day, Integer startHour,
                      Integer endHour, Type type) {

        this.employee = employee;
        this.date = date;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Day getDay() {
        return day;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public Type getType() {
        return type;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }
}
