package com.example.controller;

import com.example.model.Ticket;
import com.example.model.TicketResponse;
import com.example.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;

@Controller
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService=ticketService;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "homePage";
    }
    @GetMapping("/tickets")
    public String getAllTickets(@RequestParam(value="cursor",required = false) String cursor, Model model, HttpSession session) {
        if (cursor != null) {
            cursor = UriUtils.decode(cursor, Charset.defaultCharset());
        }
        ResponseEntity<TicketResponse> responseEntity = ticketService.getTickets(cursor);
        TicketResponse ticketsResponse = responseEntity.getBody();
        model.addAttribute("tickets", responseEntity.getBody().getTickets());
        model.addAttribute("nextUrl", ticketsResponse.getLinks().getNext() != null ? UriUtils.decode(ticketsResponse.getLinks().getNext(), Charset.defaultCharset()) : null);
        model.addAttribute("prevUrl", ticketsResponse.getLinks().getPrev() != null ? UriUtils.decode(ticketsResponse.getLinks().getPrev(), Charset.defaultCharset()) : null);
        return "tickets";
    }

    @GetMapping("/tickets/{id}")
    public String getTicketById(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticketDetail";
    }

    @GetMapping("/tickets/{id}/update")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "updateTicket";
    }

    @PostMapping("/tickets/{id}")
    public String updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        ticketService.updateTicket(id,updatedTicket);
        return "redirect:/tickets/"+id;
    }
}
