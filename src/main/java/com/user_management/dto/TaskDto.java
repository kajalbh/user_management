package com.user_management.dto;
import com.user_management.entity.Task;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @NotNull(message = "project name is requird")
    private String name;
    private String description;


    public Task.Priority getPriority() {
        return getPriority();
    }

    public Task.Status getStatus() {
        return  getStatus();
    }

    public Object getDueDate() {
        return getDueDate();
    }

    public String getTitle() {
        return getTitle();
    }

    public Long getProjectId() {
        return getProjectId();
    }
}
