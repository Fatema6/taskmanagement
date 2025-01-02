package banquemisr.challenge05.taskmanagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskHistoryDto {

    private Long id;
    private Long taskId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private Date dueDate;
    private String updatedBy;
    private Date updatedAt;
}
