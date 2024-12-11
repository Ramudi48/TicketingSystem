package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import oop.cwk.ramudi.TicketingSystem.PostingTool;
import oop.cwk.ramudi.TicketingSystem.init.table.Logging;

import java.io.IOException;
import java.util.logging.Logger;

public class Vendor implements Runnable{

    private TicketPool ticketPool;
    FetchTicketRow fetchTicketRow = new FetchTicketRow();
    private int rowID = fetchTicketRow.fetchTicketRowID();
    private int ticketReleseRate = fetchTicketRow.fetchLastRowReleseRate();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Vendor (TicketPool ticketPool){
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        Logging logob = new Logging();
        for (int i = 1; i <= ticketReleseRate; i++) {
            ticketPool.addTicket(""+i);
            addingTicket();
            System.out.println(Thread.currentThread().getName() + "Added ticket: " + i);
            Logging.logMethod("Added ticket "+i,logger);

        }
    }

    public void addingTicket(){
        String url  = "http://localhost:1200/api/tickets/"+rowID+"/add?count=1";
        PostingTool.sendPostReq(url);
    }
}
