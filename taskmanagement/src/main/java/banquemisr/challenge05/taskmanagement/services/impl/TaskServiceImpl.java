package banquemisr.challenge05.taskmanagement.services.impl;

import banquemisr.challenge05.taskmanagement.dto.*;
import banquemisr.challenge05.taskmanagement.mapper.TaskHistoryMapper;
import banquemisr.challenge05.taskmanagement.mapper.TaskMapper;
import banquemisr.challenge05.taskmanagement.models.Task;
import banquemisr.challenge05.taskmanagement.models.TaskHistory;
import banquemisr.challenge05.taskmanagement.models.User;
import banquemisr.challenge05.taskmanagement.repositories.TaskHistoryRepository;
import banquemisr.challenge05.taskmanagement.repositories.TaskRepository;
import banquemisr.challenge05.taskmanagement.repositories.UserRepository;
import banquemisr.challenge05.taskmanagement.services.TaskService;
import banquemisr.challenge05.taskmanagement.specification.TaskSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final TaskMapper taskMapper;
    private final TaskHistoryMapper taskHistoryMapper;

    private final TaskHistoryRepository taskHistoryRepository;


    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper, TaskHistoryMapper taskHistoryMapper, TaskHistoryRepository taskHistoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
        this.taskHistoryMapper = taskHistoryMapper;
        this.taskHistoryRepository = taskHistoryRepository;
    }

    @Override
    public TaskDto createTask(TaskRequestDto taskRequestDto, String userName) {
        Task task = taskMapper.toEntity(taskRequestDto);
        System.out.println("task:"+task.toString());
        task.setUser(getUser(userName));
        task.setStatus("TODO");
        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public List<TaskDto> getAllTasks(int page, int pageSize) {
        System.out.println("Page: " + page);
        System.out.println("PageSize: " + pageSize);
        Pageable pageable = PageRequest.of(page, pageSize);
        return (taskRepository.findAll(pageable).stream().map((taskMapper::toDto)).toList());
    }

    @Override
    public List<TaskDto> getUserTasks(String username, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return taskRepository.findByUserId(getUser(username).getId(), pageable)
                .stream()
                .map((taskMapper::toDto)).toList();
    }



    @Override
    public TaskDto getTaskById(Long id, String role, String userName) {
        if("ROLE_ADMIN".equals(role)) {
            return taskRepository.findById(id)
                    .map(taskMapper::toDto)
                    .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
        }
        else
        {

            Task task =
                    taskRepository.findByIdAndAndUserId(id, getUser(userName).getId());
            return taskMapper.toDto(task);

        }
    }

    @Override
    public TaskDto updateTask(Long id, UpdateTaskRequestDto task, String updatedBy, String role) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));

        if("ROLE_ADMIN".equals(role)) {

            saveTaskHistory(existingTask, updatedBy);

            taskMapper.updateTaskFromDto(task, existingTask);
            return taskMapper.toDto(taskRepository.save(existingTask));

        }
        else
        {
            if(getUser(updatedBy).getId().equals(existingTask.getUser().getId()))
            {
                saveTaskHistory(existingTask, updatedBy);

                taskMapper.updateTaskFromDto(task, existingTask);
                return taskMapper.toDto(taskRepository.save(existingTask));

            }
        }

return null;
    }

    @Override
    public void deleteTask(Long id, String userName, String role) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));

        if("ROLE_ADMIN".equals(role)) {

            taskRepository.deleteById(id);
        }
        else
        {
            if(getUser(userName).getId().equals(existingTask.getUser().getId()))

                taskRepository.deleteById(id);
        }
    }

    @Override
    public List<TaskDto> searchTasks(SearchRequestDto searchRequestDto, String userName, String role) {
        Specification<Task> spec = Specification
                .where(TaskSpecification.hasTitle(searchRequestDto.getTitle()))
                .and(TaskSpecification.hasDescription(searchRequestDto.getDescription()))
                .and(TaskSpecification.hasStatus(searchRequestDto.getStatus()))
                .and(TaskSpecification.hasPriority(searchRequestDto.getPriority()))
                .and(TaskSpecification.hasDueDate(searchRequestDto.getDueDate()));

        if(!"ROLE_ADMIN".equals(role))
            spec.and(TaskSpecification.hasUser(getUser(userName)));
        Pageable pageable = PageRequest.of(searchRequestDto.getPage(), searchRequestDto.getPageSize());
        return  taskRepository.findAll(spec,pageable).stream()
                .map((taskMapper::toDto)).toList();
    }
    private void saveTaskHistory(Task task, String updatedBy) {
        TaskHistory history = new TaskHistory();
        history.setTaskId(task.getId());
        history.setTitle(task.getTitle());
        history.setDescription(task.getDescription());
        history.setStatus(task.getStatus());
        history.setPriority(task.getPriority());
        history.setDueDate(task.getDueDate());
        history.setUpdatedBy(updatedBy);
        history.setUpdatedAt(new Date());

        taskHistoryRepository.save(history);
    }

    @Override
    public List<TaskHistoryDto> getTaskHistory(Long id, int page, int pageSize, String userName, String role) {
        Pageable pageable = PageRequest.of(page, pageSize);
        if("ROLE_ADMIN".equals(role)) {
            return taskHistoryRepository.findByTaskId(id, pageable).stream()
                    .map((taskHistoryMapper::toDto)).toList();
        }
        else
        {
           Optional<Task> task = taskRepository.findById(id);
           if(task != null)
           {
               if(task.get().getId().equals(id))
                   return taskHistoryRepository.findByTaskId(id, pageable).stream()
                   .map((taskHistoryMapper::toDto)).toList();
           }
        }
        return  null;
    }

    public User getUser(String userName)
    {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }
}
