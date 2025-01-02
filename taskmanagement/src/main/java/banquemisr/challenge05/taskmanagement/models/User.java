package banquemisr.challenge05.taskmanagement.models;

import banquemisr.challenge05.taskmanagement.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

}
