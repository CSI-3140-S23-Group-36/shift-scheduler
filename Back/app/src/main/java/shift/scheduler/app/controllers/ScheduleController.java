package shift.scheduler.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shift.scheduler.app.models.ScheduleForWeek;
import shift.scheduler.app.repositories.ScheduleForWeekRepository;

import java.sql.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScheduleForWeekRepository repository;

    @GetMapping(value = "/week", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> viewWeeklySchedule(@RequestParam(name = "year") String year,
                                                     @RequestParam(name = "month") String month,
                                                     @RequestParam(name = "day") String day) {

        Date firstDayOfWeek = stringComponentsToSqlDate(year, month, day);

        ScheduleForWeek schedule = repository.findByFirstDayOfWeek(firstDayOfWeek);

        try {
            String scheduleJson = objectMapper.writeValueAsString(schedule);
            return ResponseEntity.ok(scheduleJson);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to serialize the schedule.");
        }
    }

    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> deleteWeeklySchedule(@RequestParam(name = "year") String year,
                                                     @RequestParam(name = "month") String month,
                                                     @RequestParam(name = "day") String day) {

        Date firstDayOfWeek = stringComponentsToSqlDate(year, month, day);

        repository.deleteByFirstDayOfWeek(firstDayOfWeek);

        return ResponseEntity.ok("Successfully deleted the schedule.");
    }

    private Date stringComponentsToSqlDate(String year, String month, String day) {

        return new Date(Integer.parseInt(year) - 1900,
                Integer.parseInt(month) - 1,
                Integer.parseInt(day));
    }
}
