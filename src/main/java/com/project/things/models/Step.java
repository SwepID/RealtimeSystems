package com.project.things.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
public class Step {

    String title;
    Boolean isDone;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Step() {
    }
}
