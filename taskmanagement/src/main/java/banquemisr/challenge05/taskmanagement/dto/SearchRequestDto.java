package banquemisr.challenge05.taskmanagement.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SearchRequestDto extends BaseDto{

    private String title;
    private String description;
    private String priority;
    private Date dueDate;
    private String status;

}
