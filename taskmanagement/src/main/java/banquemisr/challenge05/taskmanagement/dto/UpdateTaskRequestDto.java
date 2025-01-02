package banquemisr.challenge05.taskmanagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UpdateTaskRequestDto {


    private String title;

    private String description;


    private String priority;

    @FutureOrPresent(message = "Due date must be in the present or future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dueDate;

}
