package shift.scheduler.app.models;

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
    private Date firstDayOfWeek;

    @OneToMany
    @Column(name = "daily_schedule_id")
    private List<ScheduleForDay> dailySchedules;

    public ScheduleForWeek(Date firstDayOfWeek, List<ScheduleForDay> dailySchedules) {

        this.firstDayOfWeek = firstDayOfWeek;
        this.dailySchedules = dailySchedules;
    }
}
