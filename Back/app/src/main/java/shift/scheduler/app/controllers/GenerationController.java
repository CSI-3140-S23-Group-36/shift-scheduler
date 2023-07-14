package shift.scheduler.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shift.scheduler.app.generator.ScheduleGenerator;
import shift.scheduler.app.models.ScheduleForWeek;
import shift.scheduler.app.models.ScheduleRequirements;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/generate")
public class GenerationController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScheduleGenerator scheduleGenerator;

    @PostMapping(value = "/week", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> generateWeeklySchedule(@RequestBody String requirementsJSON) {

        ScheduleRequirements[] requirements = null;

        try {
            requirements = objectMapper.readValue(
                    requirementsJSON, ScheduleRequirements[].class);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to parse schedule requirements.");
        }

        List<ScheduleForWeek> schedules = scheduleGenerator.generateSchedules(requirements);
        String schedulesJSON = null;

        try {
            schedulesJSON = objectMapper.writeValueAsString(schedules);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to serialize the schedules.");
        }

        return ResponseEntity.ok(schedulesJSON);
    }

}
