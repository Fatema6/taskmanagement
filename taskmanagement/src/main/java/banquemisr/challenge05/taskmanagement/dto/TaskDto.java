package banquemisr.challenge05.taskmanagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date dueDate;
}
