package shift.scheduler.app.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import shift.scheduler.app.jackson.EmployeeDeserializer;

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
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonDeserialize(using = EmployeeDeserializer.class)
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
