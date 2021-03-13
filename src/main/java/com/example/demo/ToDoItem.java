package com.example.demo;

import javax.persistence.*;
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

    @Column(name = "started_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime startedAt;

    @Column(name = "ended_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime endedAt;

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

    public ZonedDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(ZonedDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return isCompleted == toDoItem.isCompleted && Objects.equals(id, toDoItem.id) && Objects.equals(name, toDoItem.name) && Objects.equals(startedAt, toDoItem.startedAt) && Objects.equals(endedAt, toDoItem.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isCompleted, startedAt, endedAt);
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCompleted=" + isCompleted +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }
}
