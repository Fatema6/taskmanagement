package banquemisr.challenge05.taskmanagement.specification;

import banquemisr.challenge05.taskmanagement.models.Task;
import banquemisr.challenge05.taskmanagement.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskSpecification {

    public static Specification<Task> hasTitle(String title) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (title == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("title"), "%"+title+"%");
        };
    }

    public static Specification<Task> hasDescription(String description)  {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (description == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("description"), "%"+description+"%");
        };
    }

    public static Specification<Task> hasStatus(String status) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Task> hasPriority(String priority) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (priority == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("priority"), priority);
        };
    }
    public static Specification<Task> hasDueDate(Date dueDate) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (dueDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dueDate"),dueDate);
        };
    }

    public static Specification<Task> hasUser(User user) {
        return (Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (user == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("user"),user);
        };
    }
}


