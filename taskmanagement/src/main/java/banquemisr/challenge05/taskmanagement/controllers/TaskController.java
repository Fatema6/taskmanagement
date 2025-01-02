package banquemisr.challenge05.taskmanagement.controllers;

import banquemisr.challenge05.taskmanagement.dto.*;
import banquemisr.challenge05.taskmanagement.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("username:"+userName);
        return ResponseEntity.ok(taskService.createTask(taskRequestDto, userName));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@Valid @RequestBody BaseDto baseDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().getAuthority();
        List<TaskDto> tasksDtoList;
        if ("ROLE_ADMIN".equals(role)) {
            System.out.println("Page: " + baseDto.getPage());
            System.out.println("PageSize: " + baseDto.getPageSize());
            tasksDtoList = taskService.getAllTasks(baseDto.getPage(),baseDto.getPageSize());
        } else {
            tasksDtoList = taskService.getUserTasks(username,baseDto.getPage(),baseDto.getPageSize());
        }
        return ResponseEntity.ok(tasksDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().getAuthority();

        return ResponseEntity.ok(taskService.getTaskById(id, role,userName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequestDto taskRequestDto) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().getAuthority();

        return ResponseEntity.ok(taskService.updateTask(id, taskRequestDto, userName, role ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().getAuthority();

        taskService.deleteTask(id, username, role);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDto>> searchTasks(@Valid @RequestBody SearchRequestDto searchRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().getAuthority();

        List<TaskDto> taskDtoList = taskService.searchTasks(searchRequestDto, username, role);
        return ResponseEntity.ok(taskDtoList);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<TaskHistoryDto>> getTaskHistory(@PathVariable Long id,@Valid @RequestBody BaseDto baseDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().findFirst().get().getAuthority();

        List<TaskHistoryDto> history = taskService.getTaskHistory(id, baseDto.getPage(), baseDto.getPageSize(), username, role);
        return ResponseEntity.ok(history);
    }
}


