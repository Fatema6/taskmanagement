package banquemisr.challenge05.taskmanagement.repositories;

import banquemisr.challenge05.taskmanagement.models.TaskHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    Page<TaskHistory> findByTaskId(Long taskId, Pageable pageable);

}
