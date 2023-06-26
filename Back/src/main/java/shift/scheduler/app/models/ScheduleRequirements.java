package shift.scheduler.app.models;

public class ScheduleRequirements {

    private int numberOfEmployeesPerHour;
    private int startHour;
    private int endHour;
    private int numHours;

    public ScheduleRequirements(int numberOfEmployeesPerHour, int startHour, int endHour) {

        this.numberOfEmployeesPerHour = numberOfEmployeesPerHour;
        this.startHour = startHour;
        this.endHour = endHour;
        this.numHours = endHour - startHour + 1;
    }

    public int getNumberOfEmployeesPerHour() {
        return numberOfEmployeesPerHour;
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
}
