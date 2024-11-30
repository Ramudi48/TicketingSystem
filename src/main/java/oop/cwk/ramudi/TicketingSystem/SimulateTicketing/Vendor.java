package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import oop.cwk.ramudi.TicketingSystem.PostingTool;

public class Vendor implements Runnable{

    private TicketPool ticketPool;
    private FetchTicketRow fetchTicketRow;
    private int rowID = fetchTicketRow.fetchTicketRowID();
    private int ticketReleseRate = fetchTicketRow.fetchLastRowReleseRate();

    public Vendor (){

    }

    @Override
    public void run() {
        for (int i = 0; i < ticketReleseRate; i++) {
            ticketPool.addTicket(""+i);

        }
    }

    public void addingTicket(){
        String url  = "http://localhost:1200/api/tickets/"+rowID+"/add?count=1";
        PostingTool.sendPostReq(url);
    }
}
