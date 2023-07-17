package shift.scheduler.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class ScheduleForDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Day day;

    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name = "shift_id")
    private List<TimePeriod> shifts;

    public ScheduleForDay() {}

    public ScheduleForDay(Day day, List<TimePeriod> shifts) {

        this.day = day;
        this.shifts = shifts;
    }

    public Day getDay() {
        return day;
    }

    public List<TimePeriod> getShifts() {
        return shifts;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setShifts(List<TimePeriod> shifts) {
        this.shifts = shifts;
    }
}
