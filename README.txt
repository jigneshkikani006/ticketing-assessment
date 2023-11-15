###########################################
Zendesk Tickets System
###########################################

This application is a simple Ticket Management web solution built using Spring Boot, Thymeleaf templating engine, and Bootstrap.
It allows users to view all tickets, view individual tickets based on their ID, and update ticket statuses.


Features:
----------------------
View all tickets
View individual tickets by using their ID
Update tickets' status
Pagination support


Prerequisites:
----------------------
Java 8 or higher
Gradle

How to Install and Run the project
-----------------------------------------
1.Clone the project in IDE(intelliJ) from URL -> https://github.com/jigneshkikani006/ticketing-assessment
2.Compile the project using Gradle
    -CLI(./gradlew clean build) or locate gradle icon on right side panel->Tasks->build->click clean & build
3.Once successfully built, run the application
    -CLI(./gradlew bootRun) or click Run icon.
4.Access the application at http://localhost:8080


How to Use
---------------------------
1.View All Tickets: From the homepage, click on the 'View All Tickets' button.
2.View Individual Tickets: Enter the ID of the ticket in the 'Enter Ticket ID' input box and click on the 'View Ticket' button.Also click the 'View' button next to any ticket.
3.Update Ticket Status: Click the 'Update' button next to any ticket. Then, in the form that appears, change the ticket status and click 'Submit'.

Test Cases
---------------------------
Created Test Case under test/java/com/example/controller/TicketControllerTest using Boot and Mockito to test all the methods of controller and Service.
