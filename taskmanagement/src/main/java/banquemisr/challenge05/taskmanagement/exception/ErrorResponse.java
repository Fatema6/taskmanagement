package banquemisr.challenge05.taskmanagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
}

