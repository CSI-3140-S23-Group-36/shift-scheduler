package shift.scheduler.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import shift.scheduler.app.config.security.JwtUtils;
import shift.scheduler.app.controllers.auth.AuthController;
import shift.scheduler.app.models.Manager;
import shift.scheduler.app.repositories.ManagerRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController controller;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;


    @Test
    public void validLoginShouldReturnOk() throws Exception {

        Manager manager = managerRepository.save(createManager());

        mockMvc.perform(
                post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createLoginJson("manager", "awfulpassword"))
        ).andExpect(status().isOk());
    }

    @Test
    public void invalidLoginShouldReturnError() throws Exception {

        Manager manager = managerRepository.save(createManager());

        mockMvc.perform(
                post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createLoginJson("manager", "wrongpassword"))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void validSignupShouldReturnOk() throws Exception {

        mockMvc.perform(
                post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createSignupJson("Test", "test", "awfulpassword"))
        ).andExpect(status().isOk());
    }

    @Test
    public void signupWithShortPasswordShouldReturnBadRequest() throws Exception {

        mockMvc.perform(
                post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createSignupJson("Test", "test", "test"))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void signoutShouldReturnOk() throws Exception {

        mockMvc.perform(
                post("/api/auth/signout")
        ).andExpect(status().isOk());
    }

    //===== Helper Methods =====//

    public Manager createManager() {
        return new Manager("Manager", "manager", encoder.encode("awfulpassword"));
    }

    public String createLoginJson(String username, String password) {
        return String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
    }

    public String createSignupJson(String name, String username, String password) {

        return String.format("{\"username\": \"%s\", \"name\": \"%s\", \"role\": \"EMPLOYEE\",  \"password\": \"%s\"}",
                name, username, password);
    }
}
