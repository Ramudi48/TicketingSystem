package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

public class Simulate {
    public static void main(String[] args) {

        TicketPool ticketPool = new TicketPool();

            Vendor vendor = new Vendor(ticketPool);
            Thread vendorThread = new Thread(vendor, "Vendor Thread");

            Customer customer = new Customer(ticketPool);
            Thread customerThread = new Thread(customer, "Customer Thread");

            vendorThread.start();
            customerThread.start();

    }
}
