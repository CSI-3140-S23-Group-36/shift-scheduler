package shift.scheduler.app.controllers.auth.payloads;

import jakarta.validation.constraints.NotBlank;

public class AvailabilityRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String day;
    @NotBlank
    private int from;
    @NotBlank
    private int to;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
