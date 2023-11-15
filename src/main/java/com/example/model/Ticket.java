package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Ticket {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("created_at")
    private String createdAt;

    public String getParsedCreatedAt() {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return createdAt == null ? "" : isoFormat.parse(createdAt).toString();
        } catch (ParseException e) {
            return "";
        }
    }

    public Ticket() {}

public Ticket(Long Id,String createdAt,String updatedAt,String subject,String status) {
    this.id=Id;
    this.createdAt=createdAt;
    this.updatedAt=updatedAt;
    this.subject=subject;
    this.status=status;
}

    @JsonProperty("updated_at")
    private String updatedAt;

    public String getParsedUpdatedAt() {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return updatedAt == null ? "" : isoFormat.parse(updatedAt).toString();
        } catch (ParseException e) {
            return "";
        }
    }
    @JsonProperty("status")
    private String status;

    @JsonProperty("subject")
    private String subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt == null ? "" : createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt == null ? "" : updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status  == null ? "" : status ;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject  == null ? "" : subject;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", status='" + status + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

