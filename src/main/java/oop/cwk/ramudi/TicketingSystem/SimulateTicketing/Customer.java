package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import oop.cwk.ramudi.TicketingSystem.PostingTool;

public class Customer implements Runnable{
    private TicketPool ticketPool;
    FetchTicketRow fetchTicketRow = new FetchTicketRow();
    private int rowID= fetchTicketRow.fetchTicketRowID();
    private int customerRetrieveRate = fetchTicketRow.fetchLastRowRetrievelRate();

    public Customer (TicketPool ticketPool) {this.ticketPool = ticketPool;}


    @Override
    public void run() {
        for (int i=1 ;i<=customerRetrieveRate;i++){
            ticketPool.buyTicket();
            buyingTicket();
            System.out.println(Thread.currentThread().getName() + "Bought ticket: "+i);

        }
    }

    public void buyingTicket(){
        String url  = "http://localhost:1200/api/tickets/"+rowID+"/buy?count=1";
        PostingTool.sendPostReq(url);
    }
}
