package com.user_management.service;

import com.user_management.dto.TaskDto;
import com.user_management.entity.Project;
import com.user_management.entity.Task;
import com.user_management.exception.ResourceNotFoundException;
import com.user_management.exception.UnauthorizedException;
import com.user_management.repository.ProjectRepository;
import com.user_management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
        @Autowired
        private TaskRepository taskRepository;

        @Autowired
        private ProjectRepository projectRepository;

        public Task createTask(TaskDto dto, String username) {

            Project project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

            if (!project.getUser().getUsername().equals(username)) {
                throw new UnauthorizedException("Unauthorized access");
            }

            Task task = new Task();
            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setPriority(dto.getPriority());
            task.setStatus(dto.getStatus());
            task.setDueDate(dto.getDueDate());
            task.setProject(project);

            return taskRepository.save(task);
        }

        public List<Task> getTasks(Long projectId, String sortBy, String username) {

            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

            if (!project.getUser().getUsername().equals(username)) {
                throw new UnauthorizedException("Unauthorized access");
            }

            if ("dueDate".equalsIgnoreCase(sortBy)) {
                return taskRepository.findByProjectIdOrderByDueDateAsc(projectId);
            } else if ("priority".equalsIgnoreCase(sortBy)) {
                return taskRepository.findByProjectIdOrderByPriorityDesc(projectId);
            }
            return taskRepository.findByProjectId(projectId);
        }

        public List<Task> searchTasks(String keyword, String username) {
            return taskRepository
                    .findByProjectUserUsernameAndTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                            username, keyword, keyword);
        }

        public Task updateTask(Long id, TaskDto dto, String username) {

            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

            if (!task.getProject().getUser().getUsername().equals(username)) {
                throw new UnauthorizedException("Unauthorized access");
            }

            task.setTitle(dto.getTitle());
            task.setDescription(dto.getDescription());
            task.setStatus(dto.getStatus());
            task.setPriority(dto.getPriority());
            task.setDueDate(dto.getDueDate());
            task.setUpdatedAt(LocalDateTime.now());

            return taskRepository.save(task);
        }

        public void deleteTask(Long id, String username) {

            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

            if (!task.getProject().getUser().getUsername().equals(username)) {
                throw new UnauthorizedException("Unauthorized access");
            }
            taskRepository.delete(task);
        }
}
