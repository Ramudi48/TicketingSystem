package oop.cwk.ramudi.TicketingSystem.post.controller;

import oop.cwk.ramudi.TicketingSystem.init.table.TicketTable;
import oop.cwk.ramudi.TicketingSystem.init.table.TicketTableRepository;
import oop.cwk.ramudi.TicketingSystem.wiring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class Controller {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketTableRepository ticketTableRepository;


    @PatchMapping
    public ResponseEntity<TicketTable> createTicket(@RequestBody TicketTable ticket) {
        TicketTable savedTicket = ticketTableRepository.save(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping
    public List<TicketTable> getAllTickets() {
        return ticketTableRepository.findAll();
    }

}
