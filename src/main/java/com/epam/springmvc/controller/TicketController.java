package com.epam.springmvc.controller;

import com.epam.springmvc.facade.BookingFacade;
import com.epam.springmvc.model.Ticket;
import com.epam.springmvc.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final BookingFacade bookingFacade;

    public TicketController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping("/booked-tickets/user/{userId}")
    public String getBookedTicketsByUser(@PathVariable("userId") long userId, Model model) {
        User user = bookingFacade.getUserById(userId);
        List<Ticket> tickets = bookingFacade.getBookedTickets(user, 1, 1);
        model.addAttribute("tickets", tickets);
        return "tickets-list";
    }

    @GetMapping(path = "/preload")
    public ResponseEntity<?> preloadTickets() {
        if (bookingFacade.preloadTickets()) {
            return ResponseEntity.ok("Successfully preloaded tickets");
        }
        return ResponseEntity.badRequest().body("Tickets weren't preloaded");
    }
}