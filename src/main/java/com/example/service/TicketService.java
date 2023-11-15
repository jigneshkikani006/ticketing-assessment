package com.example.service;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class TicketService {
    @Autowired
    private final RestTemplate restTemplate;

    public TicketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public  ResponseEntity<TicketResponse> getTickets(String cursor) {
        String url = "https://z3nzendeskcodingchallenge.zendesk.com/api/v2/tickets.json?page[size]=25";
        if (cursor != null && !cursor.equals("")) {
            url = cursor;
        }
        ResponseEntity<TicketResponse> response = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(createHeaders("jignesh.kikani@zendesk.com","John@1820")), TicketResponse.class);
        return response;
    }

    public Ticket getTicketById(Long id) {
        String url = "https://z3nzendeskcodingchallenge.zendesk.com/api/v2/tickets/" + id;
        ResponseEntity<TicketWrapper> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders("jignesh.kikani@zendesk.com", "John@1820")),
                TicketWrapper.class
        );
        return response.getBody().getTicket();
    }

    public void updateTicket(Long id, Ticket updatedTicket) {
        String url = "https://z3nzendeskcodingchallenge.zendesk.com/api/v2/tickets/" + id;
        HttpHeaders headers = createHeaders("jignesh.kikani@zendesk.com", "John@1820");
        headers.setContentType(MediaType.APPLICATION_JSON);
        UpdatableTicket updatableTicket = new UpdatableTicket();
        updatableTicket.setStatus(updatedTicket.getStatus());
        HttpEntity<UpdatableTicketWrapper> entity = new HttpEntity<>(new UpdatableTicketWrapper(updatableTicket), headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, UpdatableTicketWrapper.class);
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            String encodedAuth = Base64Utils.encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + encodedAuth;
            set("Authorization", authHeader);
        }};
    }
}
