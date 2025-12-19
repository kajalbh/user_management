package com.user_management.service;

import com.user_management.dto.ProjectDto;
import com.user_management.entity.Project;
import com.user_management.entity.User;
import com.user_management.exception.ResourceNotFoundException;
import com.user_management.exception.UnauthorizedException;
import com.user_management.repository.ProjectRepository;
import com.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

        @Autowired
        private ProjectRepository projectRepository;

        @Autowired
        private UserRepository userRepository;

        public Project createProject(ProjectDto dto, String username) {

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            Project project = new Project();
            project.setName(dto.getName());
            project.setDescription(dto.getDescription());
            project.setUser(user);

            return projectRepository.save(project);
        }

        public List<Project> getAllProjects(String username) {

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            return projectRepository.findByUserId(user.getId());
        }

        public Project updateProject(Long id, ProjectDto dto, String username) {

            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

            if (!project.getUser().getUsername().equals(username)) {
                throw new UnauthorizedException("Unauthorized access");
            }

            project.setName(dto.getName());
            project.setDescription(dto.getDescription());
            project.setUpdatedAt(LocalDateTime.now());

            return projectRepository.save(project);
        }

        public void deleteProject(Long id, String username) {

            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

            if (!project.getUser().getUsername().equals(username)) {
                throw new UnauthorizedException("Unauthorized access");
            }

            projectRepository.delete(project);
        }

}
