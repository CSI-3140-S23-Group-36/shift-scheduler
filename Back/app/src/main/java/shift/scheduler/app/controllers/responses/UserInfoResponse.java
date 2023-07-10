package shift.scheduler.app.controllers.responses;


import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String username;
    private String name;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, String name, List<String> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}