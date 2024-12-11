package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private List<String> TicketList= new LinkedList<>();
    FetchTicketRow fetchTicketRow= new FetchTicketRow();
    private  int maxTicketCapacity = fetchTicketRow.fetchLastRowMaxCapacity();

    public synchronized void addTicket(String ticket) {
        while (TicketList.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        TicketList.add(ticket);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
    }

    public synchronized String buyTicket() {
        while (TicketList.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
        return TicketList.get(TicketList.size()-1);
    }
}
