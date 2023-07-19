package shift.scheduler.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.List;

@Entity
public class ScheduleForWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date firstDayOfWeek;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "daily_schedule_id")
    private List<ScheduleForDay> dailySchedules;

    public ScheduleForWeek() {}

    public ScheduleForWeek(Date firstDayOfWeek, List<ScheduleForDay> dailySchedules) {

        this.firstDayOfWeek = firstDayOfWeek;
        this.dailySchedules = dailySchedules;
    }

    public Date getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    public List<ScheduleForDay> getDailySchedules() {
        return dailySchedules;
    }

    public void setFirstDayOfWeek(Date firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public void setDailySchedules(List<ScheduleForDay> dailySchedules) {
        this.dailySchedules = dailySchedules;
    }
}
