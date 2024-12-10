package oop.cwk.ramudi.TicketingSystem.post.controller;

import oop.cwk.ramudi.TicketingSystem.SimulateTicketing.FetchTicketRow;
import oop.cwk.ramudi.TicketingSystem.init.table.TicketTable;
import oop.cwk.ramudi.TicketingSystem.init.table.TicketTableRepository;
import oop.cwk.ramudi.TicketingSystem.wiring.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/tickets")
public class Controller {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketTableRepository ticketTableRepository;

    @PostMapping
    public ResponseEntity<TicketTable> createTicket(@RequestBody TicketTable ticket) {
        TicketTable savedTicket = ticketTableRepository.save(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Integer>> countTickets() {
        FetchTicketRow fetchTicketRow = new FetchTicketRow();
        int totalTicket = fetchTicketRow.fetchLastRowTotalTickets();
        int ticketReleaseRate= fetchTicketRow.fetchLastRowReleseRate();
        int customerRetrievalRate = fetchTicketRow.fetchLastRowRetrievelRate();
        int maxCapacity = fetchTicketRow.fetchLastRowMaxCapacity();

        Map<String,Integer> response = new HashMap<>();
        response.put("totalTicket",totalTicket);
        response.put("ticketReleaseRate",ticketReleaseRate);
        response.put("customerRetrievalRate",customerRetrievalRate);
        response.put("maxCapacity",maxCapacity);
        return ResponseEntity.ok(response);


    }

    @GetMapping
    public List<TicketTable> getAllTickets() {
        return ticketTableRepository.findAll();
    }

    @PostMapping("/{id}/add")
    public TicketTable addTicket(@PathVariable int id, @RequestParam int count) {
        return ticketService.addTicket(id, count);
    }

    @PostMapping ("/{id}/buy")
    public TicketTable buyTicket (@PathVariable int id, @RequestParam int count) {
        return ticketService.buyTicket(id, count);
    }

}
