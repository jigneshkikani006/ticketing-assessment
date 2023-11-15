package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatableTicketWrapper {

    private UpdatableTicket ticket;

    public UpdatableTicketWrapper() {
    }

    public UpdatableTicketWrapper(UpdatableTicket ticket) {
        this.ticket = ticket;
    }

    public UpdatableTicket getTicket() {
        return ticket;
    }

    public void setTicket(UpdatableTicket ticket) {
        this.ticket = ticket;
    }
}
