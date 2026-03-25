//Task.java (Entity)
package com.yourpackage.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "tasks")   
public class Task {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "status")
    private String status;

    // Constructors
    public Task() {}
    public Task(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

  

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
