package com.example.controller;

import com.example.model.Links;
import com.example.model.Ticket;
import com.example.model.TicketResponse;
import com.example.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @InjectMocks
    TicketController ticketController;

    @MockBean
    TicketService ticketService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTickets() throws Exception {
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTickets(Arrays.asList(new Ticket(), new Ticket()));
        ticketResponse.setLinks(new Links("#","#"));
        when(ticketService.getTickets(any())).thenReturn(ResponseEntity.ok(ticketResponse));

        mockMvc.perform(get("/tickets"))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets"))
                .andExpect(model().attributeExists("tickets"));
        System.out.println("testGetTickets test passed successfully...");
    }

    @Test
    public void testGetTicketById() throws Exception{
        Long ticketId = 1L;

        when(ticketService.getTicketById(ticketId))
                .thenReturn(new Ticket());

        mockMvc.perform(get("/tickets/" + ticketId))
                .andExpect(view().name("ticketDetail"))
                .andExpect(model().attributeExists("ticket"));
        System.out.println("testGetTicketById test passed successfully...");
    }

    @Test
    public void testGetHomePage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(view().name("homePage"));
        System.out.println("testGetHomePage test passed successfully...");
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        Long ticketId = 1L;
        when(ticketService.getTicketById(ticketId)).thenReturn(new Ticket());

        mockMvc.perform(get("/tickets/" + ticketId + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("updateTicket"))
                .andExpect(model().attributeExists("ticket"));

        verify(ticketService, times(1)).getTicketById(ticketId);
        System.out.println("testShowUpdateForm test passed successfully...");
    }

    @Test
    public void testUpdateTicket() throws Exception {
        Long id = 1L;
        Ticket updatedTicket = new Ticket();

        doNothing().when(ticketService).updateTicket(eq(id), any(Ticket.class));

        mockMvc.perform(post("/tickets/" + id)
                        .content(new ObjectMapper().writeValueAsString(updatedTicket))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tickets/" + id));

        verify(ticketService, times(1)).updateTicket(eq(id), any(Ticket.class));
        System.out.println("testUpdateTicket test passed successfully...");
    }
}
