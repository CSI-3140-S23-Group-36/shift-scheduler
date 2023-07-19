package shift.scheduler.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import shift.scheduler.app.controllers.auth.AuthController;
import shift.scheduler.app.models.Employee;
import shift.scheduler.app.repositories.EmployeeRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AvailabilityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void unauthorizedRequestShouldReturnUnauthorized() throws Exception {

        mockMvc.perform(
                post("/api/availabilities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(generateAvailabilityRequest())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "test", password = "employeepw", authorities = {"USER", "EMPLOYEE"})
    public void authorizedRequestShouldReturnOk() throws Exception {

        employeeRepository.save(generateTestEmployee());

        mockMvc.perform(
                post("/api/availabilities/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(generateAvailabilityRequest())
        ).andExpect(status().isOk());
    }

    public String generateAvailabilityRequest() {
        return "{\"username\": \"test\", \"day\": \"MON\", \"from\": 8, \"to\": 20}";
    }

    public Employee generateTestEmployee() {
        return new Employee("Test", "test", encoder.encode("employeepw"), 20);
    }
}
