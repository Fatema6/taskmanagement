package banquemisr.challenge05.taskmanagement.services;

import banquemisr.challenge05.taskmanagement.dto.*;

import java.util.List;

public interface TaskService {

    TaskDto createTask(TaskRequestDto taskRequestDto, String userName);

    List<TaskDto> getAllTasks(int page, int pageSize);

    List<TaskDto> getUserTasks(String username, int page, int pageSize);

    TaskDto getTaskById(Long id, String role, String username);

    TaskDto updateTask(Long id, UpdateTaskRequestDto task, String updatedBy, String role);

    void deleteTask(Long id, String userName, String role);

    List<TaskDto> searchTasks(SearchRequestDto searchRequestDto, String userName, String role);

     List<TaskHistoryDto> getTaskHistory(Long id, int page, int pageSize, String userName, String role);
}
