package com.example.taskfragment.data;

import android.graphics.Bitmap;

public class Task {

    private int id;
    private int imageResource;
    private String title = "Task title";
    private String description = "Task description";
    private String status = "Task status";

    public Task(){ }

    public Task(int id, int imageResource, String title, String description, String status) {
        this.id = id;
        this.imageResource = imageResource;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
