package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private String name;

    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "started_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime startedAt;

    @Column(name = "ended_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime endedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return isCompleted == toDoItem.isCompleted && Objects.equals(id, toDoItem.id) && Objects.equals(name, toDoItem.name) && Objects.equals(user, toDoItem.user) && Objects.equals(startedAt, toDoItem.startedAt) && Objects.equals(endedAt, toDoItem.endedAt) && Objects.equals(createdAt, toDoItem.createdAt) && Objects.equals(updatedAt, toDoItem.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isCompleted, user, startedAt, endedAt, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCompleted=" + isCompleted +
                ", user=" + user +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
