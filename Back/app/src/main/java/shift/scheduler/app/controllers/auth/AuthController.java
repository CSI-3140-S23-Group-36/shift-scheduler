package shift.scheduler.app.controllers.auth;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import shift.scheduler.app.config.security.JwtUtils;
import shift.scheduler.app.config.security.UserDetailsImpl;
import shift.scheduler.app.controllers.auth.payloads.LoginRequest;
import shift.scheduler.app.controllers.auth.payloads.SignupRequest;
import shift.scheduler.app.controllers.auth.responses.MessageResponse;
import shift.scheduler.app.controllers.auth.responses.UserInfoResponse;
import shift.scheduler.app.models.Employee;
import shift.scheduler.app.models.Manager;
import shift.scheduler.app.models.User;
import shift.scheduler.app.repositories.EmployeeRepository;
import shift.scheduler.app.repositories.ManagerRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getName(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (employeeRepository.existsByUsername(signUpRequest.getUsername()) || managerRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        User user;
        if (signUpRequest.getRole().equals("MANAGER")) {
            user = new Manager();
        } else if (signUpRequest.getRole().equals("EMPLOYEE")){
            user = new Employee();
            ((Employee) user).setMaxHoursPerDay(4);
        } else { return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid role!"));}
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setPasswordHash(encoder.encode(signUpRequest.getPassword()));

        if (signUpRequest.getRole().equals("MANAGER")) {
            managerRepository.save((Manager) user);
        } else {
            employeeRepository.save((Employee) user);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}