package banquemisr.challenge05.taskmanagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TaskRequestDto{

    @NotNull(message = "Title is mandatory")
    @Size(min= 4, max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;


    @NotNull(message = "Priority is mandatory")
    private String priority;

    @FutureOrPresent(message = "Due date must be in the present or future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dueDate;

}
