package shift.scheduler.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "time_periods")
public class TimePeriod {

    public enum Type {
        AVAILABILITIY,
        SHIFT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Employee employee;

    @NotNull
    private Date date;

    @NotNull
    private Integer startHour;

    @NotNull
    private Integer endHour;

    @NotNull
    private Type type;
}
