package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import java.util.logging.Logger;

public class Simulate {

    private static  TicketPool ticketPool = new TicketPool();

    private static  Vendor vendor;
    private static  Thread vendorThread;

    private static  Customer customer;
    private static  Thread customerThread;

    private static Logger logger = Logger.getLogger("StartSimulation");

    public static void main(String[] args) {
        startSimulation();

    }

    public static void startSimulation() {

        vendor = new Vendor(ticketPool);
        vendorThread = new Thread(vendor,"Vendor Thread");
        customer = new Customer(ticketPool);
        customerThread = new Thread(customer,"Customer Thread");

        vendorThread.start();
        customerThread.start();
    }

    public static Thread getCustomerThread(){
        return customerThread;
    }
    public static Thread getVendorThread(){
        return vendorThread;
    }
}