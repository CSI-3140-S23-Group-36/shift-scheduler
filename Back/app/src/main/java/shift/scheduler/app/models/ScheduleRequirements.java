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

    public ScheduleRequirements() {}

    public ScheduleRequirements(int numberOfEmployeesPerHour, Day day, int startHour, int endHour) {

        this.numberOfEmployeesPerHour = numberOfEmployeesPerHour;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.numHours = endHour - startHour + 1;

        setHours();
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

    public void setNumberOfEmployeesPerHour(int numberOfEmployeesPerHour) {
        this.numberOfEmployeesPerHour = numberOfEmployeesPerHour;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setStartHour(int startHour) {

        this.startHour = startHour;
        setNumHours(endHour - startHour + 1);
    }

    public void setEndHour(int endHour) {

        this.endHour = endHour;
        setNumHours(endHour - startHour + 1);
    }

    public void setNumHours(int numHours) {

        this.numHours = numHours;
        setHours();
    }

    public void setHours() {

        if (this.hours == null)
            this.hours = new ArrayList<>();

        for (int i = startHour; i <= endHour; i += SHIFT_LENGTH_DIVISOR)
            this.hours.add(new Hour(i));
    }
}
