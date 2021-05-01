package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ToDoItemDto implements Serializable {

    private Long id;

    private String name;

    private boolean isCompleted;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy hh:mm a")
    @JsonSerialize(using = UserTimeZoneSerializer.class)
    @JsonDeserialize(using = UserTimeZoneDeserializer.class)
    private LocalDateTime startedAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy hh:mm a")
    @JsonSerialize(using = UserTimeZoneSerializer.class)
    @JsonDeserialize(using = UserTimeZoneDeserializer.class)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItemDto that = (ToDoItemDto) o;
        return isCompleted == that.isCompleted && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(startedAt, that.startedAt) && Objects.equals(endedAt, that.endedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isCompleted, startedAt, endedAt);
    }

    @Override
    public String toString() {
        return "ToDoItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCompleted=" + isCompleted +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }
}
