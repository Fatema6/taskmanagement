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
@Table(name = "TASK_HISTORY")
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TASK_ID", nullable = false)
    private Long taskId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
