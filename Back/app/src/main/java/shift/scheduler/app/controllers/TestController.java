package shift.scheduler.app.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/employee")
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    public String employeeAccess() {
        return "Employee Content.";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String moderatorAccess() {
        return "Manager content.";
    }


}