package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import oop.cwk.ramudi.TicketingSystem.PostingTool;

public class Vendor implements Runnable{

    private TicketPool ticketPool;
    FetchTicketRow fetchTicketRow = new FetchTicketRow();
    private int rowID = fetchTicketRow.fetchTicketRowID();
    private int ticketReleseRate = fetchTicketRow.fetchLastRowReleseRate();

    public Vendor (TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        for (int i = 0; i < ticketReleseRate; i++) {
            ticketPool.addTicket(""+i);
            addingTicket();
            System.out.println(Thread.currentThread().getName() + "Added ticket: " + i);

        }
    }

    public void addingTicket(){
        String url  = "http://localhost:1200/api/tickets/"+rowID+"/add?count=1";
        PostingTool.sendPostReq(url);
    }
}
