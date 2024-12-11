package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import oop.cwk.ramudi.TicketingSystem.PostingTool;
import oop.cwk.ramudi.TicketingSystem.init.table.Logging;

import java.io.IOException;
import java.util.logging.Logger;

public class Customer implements Runnable{
    private TicketPool ticketPool;
    FetchTicketRow fetchTicketRow = new FetchTicketRow();
    private int rowID= fetchTicketRow.fetchTicketRowID();
    private int customerRetrieveRate = fetchTicketRow.fetchLastRowRetrievelRate();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Customer (TicketPool ticketPool) {this.ticketPool = ticketPool;}


    @Override
    public void run() {
        for (int i=1 ;i<=customerRetrieveRate;i++){
            ticketPool.buyTicket();
            buyingTicket();
            System.out.println(Thread.currentThread().getName() + "Bought ticket: "+i);
            Logging.logMethod("Buying ticket "+i ,logger);
            try {
                PostingTool.SendPostReqWithJson("http://localhost:1200/api/logs",Thread.currentThread().getName() + " Bought ticket: " + i);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }

    public void buyingTicket(){
        String url  = "http://localhost:1200/api/tickets/"+rowID+"/buy?count=1";
        PostingTool.sendPostReq(url);
    }
}
