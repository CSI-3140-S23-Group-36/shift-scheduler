package shift.scheduler.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shift.scheduler.app.controllers.auth.payloads.AvailabilityRequest;
import shift.scheduler.app.controllers.auth.payloads.LoginRequest;
import shift.scheduler.app.generator.ScheduleGenerator;
import shift.scheduler.app.models.*;
import shift.scheduler.app.repositories.EmployeeRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {
    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<String> addAvailability(@Valid @RequestBody AvailabilityRequest availabilityRequest) {
        Employee emp = employeeRepository.findByUsername(availabilityRequest.getUsername()).orElseThrow();
        TimePeriod t = new TimePeriod();
        t.setEmployee(emp);
        t.setType(TimePeriod.Type.AVAILABILITY);
        t.setDay(Day.valueOf(availabilityRequest.getDay()));
        t.setStartHour(availabilityRequest.getFrom());
        t.setEndHour(availabilityRequest.getTo());
        emp.getAvailabilities().add(t);
        employeeRepository.save(emp);

        return ResponseEntity.ok("Successfully added availability");
    }

}
