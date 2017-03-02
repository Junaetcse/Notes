package com.example.user.notes;

/**
 * Created by User on 3/2/2017.
 */

public class DatabaseModel {

    private int id;
    private String title;
    private String date;
    private String subject;
    private String details;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DatabaseModel{" +
                "date='" + date + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
