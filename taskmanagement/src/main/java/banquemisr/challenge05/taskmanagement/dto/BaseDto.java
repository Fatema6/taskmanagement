package banquemisr.challenge05.taskmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseDto {

    @NotNull(message = "Page is required")
    private Integer page;
    @NotNull(message = "Page Size is required")
    private Integer pageSize;
}
