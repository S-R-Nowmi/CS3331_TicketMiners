import java.io.*;

public class InvoiceGenerator {
    public static String generateInvoice(Customer customer , Event event , Tickets ticket , int numberOfTickets , String ticketType){
        String invoiceFileName = ("TicketsInvoice.txt");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(invoiceFileName))){
            writer.write("Invoice for customer: " + customer.getFirstName() + " " + customer.getLastName());
            writer.write("\nEvent: " + event.getName());
            writer.write("\nEvent Date and Time: " + event.getDate() + " , " + event.getTime());
            writer.write("\nTicket Type and Quantity: " + ticketType + " , " + numberOfTickets);
            writer.write("\nTotal Price: " + ticket.getPrice() * numberOfTickets);

            System.out.println("\nInvoice generated and saved as: " + invoiceFileName);
        } catch(IOException e){e.printStackTrace();}
        return invoiceFileName;
    }
}
