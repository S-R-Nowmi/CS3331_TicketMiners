import java.util.*;
import java.io.*;

public class CsvFileCreator {
    
    public static void newEventsCsv(Map<Integer , Event> eventsDict){
        String inputFileName = "EventListPA4.csv";
        String outputFileName = "UpdatedEventListPA4.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))){
            String line;
            String newHeader = reader.readLine();

            String[] headerColumns = newHeader.split(",");
            Map<String , Integer> columnIndexMap = new HashMap<>();
            for(int i = 0; i < headerColumns.length; i++){
                columnIndexMap.put(headerColumns[i] , i);
            }

            writer.write(newHeader + ",VIP Seats Sold,Gold Seats Sold,Silver Seats Sold,Bronze Seats Sold,General Admission Seats Sold,VIP Revenue,Gold Revenue,Silver Revenue,Bronze Revenue,General Admission Revenue\n");

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                int eventId = Integer.parseInt(parts[columnIndexMap.get("Event ID")].trim());

                Event event = eventsDict.get(eventId);
                if(event != null){
                    StringBuilder extendedLine = new StringBuilder(line);
                    extendedLine.append("," + event.getVip().getSeatsSold());
                    extendedLine.append("," + event.getGold().getSeatsSold());
                    extendedLine.append("," + event.getSilver().getSeatsSold());
                    extendedLine.append("," + event.getBronze().getSeatsSold());
                    extendedLine.append("," + event.getGa().getSeatsSold());

                    extendedLine.append("," + event.getVip().getRevenue());
                    extendedLine.append("," + event.getGold().getRevenue());
                    extendedLine.append("," + event.getSilver().getRevenue());
                    extendedLine.append("," + event.getBronze().getRevenue());
                    extendedLine.append("," + event.getGa().getRevenue());

                    writer.write(extendedLine.toString() + "\n");
                } else{
                    writer.write(line + "\n");
                }
            }
            System.out.println("\nNew CSV file creates called: " + outputFileName);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void newCustomerCsv(Map<Integer , Customer> customerDict){
        String inputFileName = "CustomerListPA4.csv";
        String outputFileName = "UpdatedCustomerListPA4.csv";
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))){
            String line;
            String newHeader = reader.readLine();
            String[] headerColumns = newHeader.split(",");
            Map<String , Integer> columnIndexMap = new HashMap<>();
            for(int i = 0; i < headerColumns.length; i++){
                columnIndexMap.put(headerColumns[i] , i);
            }
            writer.write(newHeader + "\n");

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                int customerId = Integer.parseInt(parts[columnIndexMap.get("ID")].trim());
                Customer customer = customerDict.get(customerId);

                if(customer != null){
                    parts[columnIndexMap.get("Money Available")] = Double.toString(customer.getMoneyAvailable());
                    parts[columnIndexMap.get("Tickets Purchased")] = Integer.toString(customer.getConcertsPurchase());
 
                    String updatedLine = String.join("," , parts);
                    writer.write(updatedLine + "\n");
                } else{
                    writer.write(line + "\n");
                }
            }
            System.out.println("\nNew CSV file creates called: " + outputFileName);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
