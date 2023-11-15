package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TicketResponse {
    @JsonProperty("tickets")
    private List<Ticket> tickets;

    @JsonProperty("links")
    private Links links;
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
