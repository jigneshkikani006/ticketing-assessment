package com.example.model;

public class UpdatableTicket {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status  == null ? "" : status ;
    }
}
