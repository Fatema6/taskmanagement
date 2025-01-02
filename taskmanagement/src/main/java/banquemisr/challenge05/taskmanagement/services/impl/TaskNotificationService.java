package banquemisr.challenge05.taskmanagement.services.impl;

import banquemisr.challenge05.taskmanagement.models.Task;
import banquemisr.challenge05.taskmanagement.repositories.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class TaskNotificationService {
    private final TaskRepository taskRepository;
    private final EmailService emailService;

    public TaskNotificationService(TaskRepository taskRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    // Run every day at 8 AM
    //@Scheduled(cron = "0 0 8 * * ?")
    @Scheduled(cron = "0 30 23 * * ?")

    public void sendDeadlineNotifications() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Date tomorrowDate = Date.valueOf(tomorrow);

        List<Task> upcomingTasks = taskRepository.findByDueDate(tomorrow);

        for (Task task : upcomingTasks) {
            String to = task.getUser().getEmail();
            String subject = "Task Reminder: Upcoming Deadline";
            String body = String.format(
                    "Hello %s,<br><br>Your task <strong>%s</strong> is due tomorrow (%s).<br><br>Please complete it on time.<br><br>Regards,<br>Task Management Team",
                    task.getUser().getUsername(),
                    task.getTitle(),
                    task.getDueDate()
            );

            emailService.sendEmail(to, subject, body);
        }
    }
}

