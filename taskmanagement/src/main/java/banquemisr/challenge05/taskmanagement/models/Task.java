package banquemisr.challenge05.taskmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", length = 255 ,nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Column(name = "PRIORITY", length = 50)
    private String priority;

    @Column(name = "DUE_DATE")
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false) // Specifies the foreign key column
    private User user;
}
