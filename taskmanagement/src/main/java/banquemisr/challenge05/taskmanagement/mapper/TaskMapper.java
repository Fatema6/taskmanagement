package banquemisr.challenge05.taskmanagement.mapper;

import banquemisr.challenge05.taskmanagement.dto.TaskDto;
import banquemisr.challenge05.taskmanagement.dto.TaskRequestDto;
import banquemisr.challenge05.taskmanagement.dto.UpdateTaskRequestDto;
import banquemisr.challenge05.taskmanagement.models.Task;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper(componentModel = "spring")
@Component
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskRequestDto taskDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromDto(UpdateTaskRequestDto taskRequestDto, @MappingTarget Task task);

}
