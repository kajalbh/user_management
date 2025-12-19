package com.user_management.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status= Status.PENDING;
    private Priority priority=Priority.MEDIUM;

    private LocalDateTime created_at=LocalDateTime.now();
    private LocalDateTime updated_at=LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public void setDueDate(Object dueDate) {
    }

    public void setUpdatedAt(LocalDateTime now) {

    }

    public enum Status {
        PENDING,IN_PROGRESS,COMPLETED
    }
    public enum Priority {
        HIGH,MEDIUM,LOW
    }
}
