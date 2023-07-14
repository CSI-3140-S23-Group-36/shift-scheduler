package shift.scheduler.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shift.scheduler.app.models.ScheduleForWeek;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/generate")
public class GenerationController {

    @GetMapping("/week")
    @PreAuthorize("hasAuthority('MANAGER')")
    public List<ScheduleForWeek> generateWeeklySchedule() {
        // Generate 3 possible schedules for the coming week
        return new ArrayList<>();
    }

}
