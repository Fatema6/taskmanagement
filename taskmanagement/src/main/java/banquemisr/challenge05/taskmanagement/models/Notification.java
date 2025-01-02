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
@Table(name = "NOTIFICATIONS")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RECIPIENT", nullable = false)
    private String recipient;

    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @Column(name = "MESSAGE", columnDefinition = "TEXT")
    private String message;

    @Column(name = "STATUS", nullable = false)
    private String status; // e.g., SENT, FAILED

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SENT_AT", nullable = false)
    private Date sentAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FAILED_AT")
    private Date failedAt;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;
}
