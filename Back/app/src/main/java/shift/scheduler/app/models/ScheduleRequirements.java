package shift.scheduler.app.models;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRequirements {

    private static final int SHIFT_LENGTH_DIVISOR = 4;

    private int numberOfEmployeesPerHour;

    private Day day;

    private int startHour;
    private int endHour;
    private int numHours;

    private List<Hour> hours;

    public ScheduleRequirements(int numberOfEmployeesPerHour, Day day, int startHour, int endHour) {

        this.numberOfEmployeesPerHour = numberOfEmployeesPerHour;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.numHours = endHour - startHour + 1;
        this.hours = new ArrayList<>();

        for (int i = startHour; i <= endHour; i += SHIFT_LENGTH_DIVISOR)
            this.hours.add(new Hour(i));
    }

    public int getNumberOfEmployeesPerHour() {
        return numberOfEmployeesPerHour;
    }

    public Day getDay() {
        return day;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getNumHours() {
        return numHours;
    }

    public List<Hour> getHours() {
        return hours;
    }
}
