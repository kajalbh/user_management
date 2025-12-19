package com.user_management.controller;

import com.user_management.dto.ProjectDto;
import com.user_management.entity.Project;
import com.user_management.service.ProjectService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

        @Autowired
        private ProjectService projectService;

        @PostMapping
        public ResponseEntity<Project> createProject(
                @RequestBody ProjectDto dto,
                Authentication authentication) {

            return ResponseEntity.ok(
                    projectService.createProject(dto, authentication.name()));
        }

        @GetMapping
        public ResponseEntity<List<Project>> getAllProjects(
                Authentication authentication) {

            return ResponseEntity.ok(
                    projectService.getAllProjects(authentication.name()));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Project> updateProject(
                @PathVariable Long id,
                @RequestBody ProjectDto dto,
                Authentication authentication) {

            return ResponseEntity.ok(
                    projectService.updateProject(id, dto, authentication.name()));
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteProject(
                @PathVariable Long id,
                Authentication authentication) {

            projectService.deleteProject(id, authentication.name());
            return ResponseEntity.ok("Project deleted successfully");
        }

}
