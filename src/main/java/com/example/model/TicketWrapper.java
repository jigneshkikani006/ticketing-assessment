package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


public class TicketWrapper {

    @JsonProperty("ticket")
    private Ticket ticket;

    public TicketWrapper() {
    }

    public TicketWrapper(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketWrapper(UpdatableTicket updatableTicket) {
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
