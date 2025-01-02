package banquemisr.challenge05.taskmanagement.repositories;

import banquemisr.challenge05.taskmanagement.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {


    Page<Task> findAll(Specification<Task> spec, Pageable pageable);
    List<Task> findByUserId(Long userId, Pageable pageable);
    List<Task> findByDueDate(Date dueDate);

    Task findByIdAndAndUserId(Long id, Long userId);


}
