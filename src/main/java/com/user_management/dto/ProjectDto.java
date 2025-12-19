package com.user_management.dto;

import com.user_management.entity.Task;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDto {
    @NotNull(message = "task_Id is requird")
    private Long id;
    @NotNull(message = "task_title required")
    private String title;
    @NotNull(message = "task_description required")
    private  String description;
    @NotNull(message = "task_status required")
    private Task.Status status;

    private Task.Priority priority;
    private LocalDate dueDate;


    public String getName() {
        return getName();
    }
}
