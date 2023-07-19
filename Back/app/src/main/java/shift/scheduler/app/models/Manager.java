package shift.scheduler.app.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "managers")
public class Manager extends User {

    public Manager() {}

    public Manager(String name, String username, String passwordHash) {
        super(name, username, passwordHash);
    }
}
