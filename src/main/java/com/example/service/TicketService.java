package com.example.service;

import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Service
public class TicketService {

    private String username;
    private String password;
    private Environment env;
    String url ="";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public TicketService(Environment env) {
        this.env=env;
        this.username = env.getProperty("zendesk.username");
        this.password = env.getProperty("zendesk.password");
    }

    public TicketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public  ResponseEntity<TicketResponse> getTickets(String cursor) {
        url = env.getProperty("zendesk.url.getalltickets");
        if (cursor != null && !cursor.equals("")) {
            url = cursor;
        }
        ResponseEntity<TicketResponse> response = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(createHeaders(username,password)), TicketResponse.class);
        return response;
    }

    public Ticket getTicketById(Long id) {
        url = env.getProperty("zendesk.url.getticket") + id;
        ResponseEntity<TicketWrapper> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(username,password)),
                TicketWrapper.class
        );
        return response.getBody().getTicket();
    }

    public void updateTicket(Long id, Ticket updatedTicket) {
        url = env.getProperty("zendesk.url.updateticket") + id;
        HttpHeaders headers = createHeaders(username,password);
        headers.setContentType(MediaType.APPLICATION_JSON);
        UpdatableTicket updatableTicket = new UpdatableTicket();
        updatableTicket.setStatus(updatedTicket.getStatus());
        HttpEntity<UpdatableTicketWrapper> entity = new HttpEntity<>(new UpdatableTicketWrapper(updatableTicket), headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, UpdatableTicketWrapper.class);
    }

    HttpHeaders createHeaders(String uname, String pass) {
        return new HttpHeaders() {{
            String auth = uname + ":" + pass;
            String encodedAuth = Base64Utils.encodeToString(auth.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + encodedAuth;
            set("Authorization", authHeader);
        }};
    }
}
