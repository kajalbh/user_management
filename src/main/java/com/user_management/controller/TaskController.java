package com.user_management.controller;

import com.user_management.dto.TaskDto;
import com.user_management.entity.Task;
import com.user_management.service.TaskService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

        @Autowired
        private TaskService taskService;

        @PostMapping
        public ResponseEntity<Task> createTask(
                @RequestBody TaskDto dto,
                Authentication authentication) {

            return ResponseEntity.ok(
                    taskService.createTask(dto, authentication.name()));
        }

        @GetMapping("/project/{projectId}")
        public ResponseEntity<List<Task>> getTasks(
                @PathVariable Long projectId,
                @RequestParam(required = false) String sortBy,
                Authentication authentication) {

            return ResponseEntity.ok(
                    taskService.getTasks(projectId, sortBy, authentication.name()));
        }

        @GetMapping("/search")
        public ResponseEntity<List<Task>> searchTasks(
                @RequestParam String keyword,
                Authentication authentication) {

            return ResponseEntity.ok(
                    taskService.searchTasks(keyword, authentication.name()));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Task> updateTask(
                @PathVariable Long id,
                @RequestBody TaskDto dto,
                Authentication authentication) {

            return ResponseEntity.ok(
                    taskService.updateTask(id, dto, authentication.name()));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteTask(
                @PathVariable Long id,
                Authentication authentication) {

            taskService.deleteTask(id, authentication.name());
            return ResponseEntity.ok("Task deleted successfully");
        }

}
