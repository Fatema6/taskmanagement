package banquemisr.challenge05.taskmanagement.mapper;

import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.dto.TaskHistoryDto;
import banquemisr.challenge05.taskmanagement.dto.TaskRequestDto;
import banquemisr.challenge05.taskmanagement.models.Task;
import banquemisr.challenge05.taskmanagement.models.TaskHistory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TaskHistoryMapper {

    TaskHistoryDto toDto(TaskHistory taskHistory);

    TaskHistory toEntity(TaskHistoryDto taskHistoryDto);

}
