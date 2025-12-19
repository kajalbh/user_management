package com.user_management.repository;

import com.user_management.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task>findByProjectUserIdAndTitleContainingOrDescriptionContaining(Long userId,String title,String description);
    List<Task>findByProjectIdOrderByDueDateAsc(Long projectId);
    List<Task> findByProjectIdOrderByPriorityDesc(Long projectId);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByProjectUserUsernameAndTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String username, String keyword, String keyword1);
}
