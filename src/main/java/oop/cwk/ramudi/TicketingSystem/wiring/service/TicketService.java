package oop.cwk.ramudi.TicketingSystem.wiring.service;

import oop.cwk.ramudi.TicketingSystem.init.table.TicketTable;
import oop.cwk.ramudi.TicketingSystem.init.table.TicketTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketTableRepository ticketTableRepository;

    /**
     * Add ticketst to the total when vendor calls method
     * @param id - primary key of the ticket table
     * @param addingTickets
     * @return - newTicketTable of type TicketTable
     */
    public TicketTable addTicket(int id, int addingTickets) {
        Optional<TicketTable> ticketTable = ticketTableRepository.findById(id);
        if (ticketTable.isPresent()) {
            TicketTable newTicketTable = ticketTable.get();
            newTicketTable.setTotalTickets(newTicketTable.getTotalTickets() + addingTickets);
            System.out.println("Added "+addingTickets+" tickets.");
            return newTicketTable;
        }else {
            throw new RuntimeException("No such ticket table at ID: "+id);
        }

    }

    public TicketTable buyTicket(int id, int removingTickets) {
        Optional<TicketTable> ticketTable = ticketTableRepository.findById(id);
        if (ticketTable.isPresent()) {
            TicketTable newTicketTable = ticketTable.get();
            if(newTicketTable.getTotalTickets() >= removingTickets) {
                newTicketTable.setTotalTickets(newTicketTable.getTotalTickets()- removingTickets);
                System.out.println("Bought "+removingTickets+" tickets.");
                return ticketTableRepository.save(newTicketTable);
            }else{
                throw new RuntimeException("Not enough tickets in table.");
            }
        }else throw new RuntimeException("No such ticket table at ID: "+id);
    }

}
