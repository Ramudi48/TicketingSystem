package oop.cwk.ramudi.TicketingSystem.SimulateTicketing;

import oop.cwk.ramudi.TicketingSystem.PostingTool;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConfigTickets {
    static String json;
    static int totalTickets;
    static int ticketReleaseRate;
    static int customerRetrieverRate;
    static int maxTicketCapacity;

    public static void main (String[] args) throws IOException {
        configering();

        json= "{"
                + "\"totalTickets\":"+totalTickets+","
                + "\"ticketReleaseRate\":"+ticketReleaseRate+","
                + "\"customerRetrievalRate\":"+customerRetrieverRate+","
                + "\"maxCapacity\":"+maxTicketCapacity
      +"}";

        String url= "http://localhost:1200/api/tickets";
        PostingTool.SendPostReqWithJson(url,json);
    }

    public static void configering() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        System.out.println("******System Configuration******");
        totalTickets = inputValue(sc,"Enter Total Tickets:");
        ticketReleaseRate = inputValue(sc,"Enter Ticket Release Rate:");
        customerRetrieverRate = inputValue(sc,"Enter Customer Retriver Rate:");
        maxTicketCapacity = inputValue(sc,"Enter Max Ticket Capacity:");



    }

    private static int inputValue(Scanner scanner, String input ){
        int value;
        while (true){
            System.out.println(input);
            String updatedInput = scanner.nextLine().trim(); /*Removing spaces from the input
                                                            reason- So user won't be able to input space,
                                                            because it doesn't hold a value*/

            if(updatedInput.isEmpty()){
                System.out.println("Invalid input. Please enter a number");
                continue;
            }
            try{
                value = Integer.parseInt(updatedInput);
                break;
            }catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid number");

            }

        }
        return value;
    }
}
