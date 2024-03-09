import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PurchasingTickets {
    private int userId;
    private Map<Integer, Event> eventsDict;
    private Map<Integer, Customer> customerDict;

    public PurchasingTickets() {
    }

    public PurchasingTickets(int userId, Map<Integer, Event> eventsDict, Map<Integer, Customer> customerDict) {
        this.userId = userId;
        this.eventsDict = eventsDict;
        this.customerDict = customerDict;
    }

    public void Purchase() {
        Map<Integer, Event> eventsDict = this.eventsDict;
        Map<Integer, Customer> customerDict = this.customerDict;
        Scanner kb = new Scanner(System.in);
        double txSalesTax = .0825;
        double subtotal;
        double unroundedTotal;
        BigDecimal finalTotal;
        double taxedAmnt;
        double discountAmnt = 0;
        int desiredEventID;
        int desiredTicketType;
        int amountOfTickets;
        Customer thisUser = customerDict.get(userId);

        // beginning of loop for the customer login menu
        boolean purchasingFlag = true;
        while (purchasingFlag) {
            System.out.println("\nPlease enter the event ID you wish to purchase tickets for: \nOr enter 0 to EXIT\n");
            desiredEventID = kb.nextInt();
            kb.nextLine();
            if (desiredEventID == 0) {
                purchasingFlag = false;
            }
            else if (!eventsDict.containsKey(desiredEventID)) {
                System.out.println("\nThe event ID you entered is non-existent\n");
            } else {
                Event srch = eventsDict.get(desiredEventID);
                System.out.println("\n" + srch.toString() + "\nWhich type of ticket type would you like to purchase? Enter 0 to exit\n" + "Enter 1 for VIP , 2 for Gold , 3 for Silver , 4 for Bronze , or 5 for GA\n");
                desiredTicketType = kb.nextInt();
                kb.nextLine();
                if (desiredTicketType != 0) {
                    System.out.println("\nHow many tickets would you like to purchase?\n");
                    amountOfTickets = kb.nextInt();
                    if (amountOfTickets > 6) {
                        System.out.println("\nCannot purchase more than 6 tickets to an event at a time!\n");
                    } else {
                        //customer has entered a quantity of 1-6
                        //switch/case block for different ticket types
                        switch (desiredTicketType) {
                            case 1:
                                //every case makes sure theres enough tickets available, and that the user has enough money
                                if (srch.getVip().getAvailableTickets() > amountOfTickets && thisUser.getMoneyAvailable() > amountOfTickets * srch.getVip().getPrice()) {
                                    subtotal = srch.getVip().getPrice() * amountOfTickets;
                                    //checks for membership, applies discount if true
                                    if(thisUser.getMembership()){
                                        discountAmnt = subtotal * 0.1;
                                        subtotal -= discountAmnt;
                                        System.out.printf("\nThank you for being a TicketMiner member, you saved $%.2f on this purchase!", discountAmnt);
                                    }
                                    taxedAmnt = subtotal * txSalesTax;
                                    unroundedTotal = subtotal + taxedAmnt;
                                    //following line simply formats price to have to numbers after the decimal point
                                    finalTotal = new BigDecimal(unroundedTotal).setScale(2, RoundingMode.HALF_UP);
                                    System.out.println("\nWould you like to confirm " + amountOfTickets + " VIP tickets for " + finalTotal + "?\n1 for YES , 2 for NO\n");
                                    int conf = kb.nextInt();
                                    kb.nextLine();
                                    //this block runs if purchase is confirmed, updating all appropriate values, srch is the event that the customer bought tickets for
                                    if (conf == 1) {
                                        InvoiceGenerator.generateInvoice(thisUser, srch, srch.getVip(), amountOfTickets, "VIP");
                                        thisUser.setMoneyAvailable(thisUser.getMoneyAvailable() - unroundedTotal);
                                        srch.getVip().setAvailableTickets(srch.getVip().getAvailableTickets() - amountOfTickets);
                                        srch.getVip().setRevenue(amountOfTickets);
                                        srch.getVip().setSeatsSold(amountOfTickets);
                                        srch.setTotalTax(taxedAmnt);
                                        thisUser.setConcertsPurchase(amountOfTickets);
                                        myLogger.logInfo("User " + thisUser.getUsername() + " purchased " + amountOfTickets + " VIP tickets for event " + srch.getName());
                                    } else if (conf == 2) {
                                        System.out.println("\nPurchase cancelled\n");
                                    } else {
                                        System.out.println("\nIncorrect input\n");
                                    }
                                } else {
                                    System.out.println("\nERROR: insufficient funds or not enough tickets available for purchase!\n");
                                }
                                break;
                            case 2:
                                if (srch.getGold().getAvailableTickets() > amountOfTickets && thisUser.getMoneyAvailable() > amountOfTickets * srch.getGold().getPrice()) {
                                    subtotal = srch.getGold().getPrice() * amountOfTickets;
                                    if(thisUser.getMembership()){
                                        discountAmnt = subtotal * 0.1;
                                        subtotal -= discountAmnt;
                                        System.out.printf("\nThank you for being a TicketMiner member, you saved $%.2f on this purchase!", discountAmnt);
                                    }
                                    taxedAmnt = subtotal * txSalesTax;
                                    unroundedTotal = subtotal + taxedAmnt;
                                    finalTotal = new BigDecimal(unroundedTotal).setScale(2, RoundingMode.HALF_UP);
                                    System.out.println("\nWould you like to confirm " + amountOfTickets + " Gold tickets for " + finalTotal + "?\n1 for YES , 2 for NO\n");
                                    int conf = kb.nextInt();
                                    kb.nextLine();
                                    if (conf == 1) {
                                        InvoiceGenerator.generateInvoice(thisUser, srch, srch.getGold(), amountOfTickets, "Gold");
                                        thisUser.setMoneyAvailable(thisUser.getMoneyAvailable() - unroundedTotal);
                                        srch.getGold().setAvailableTickets(srch.getGold().getAvailableTickets() - amountOfTickets);
                                        srch.getGold().setRevenue(amountOfTickets);
                                        srch.getGold().setSeatsSold(amountOfTickets);
                                        srch.setTotalTax(taxedAmnt);
                                        thisUser.setConcertsPurchase(amountOfTickets);
                                        myLogger.logInfo("User " + thisUser.getUsername() + " purchased " + amountOfTickets + " Gold tickets for event " + srch.getName());
                                    } else if (conf == 2) {
                                        System.out.println("\nPurchase cancelled\n");
                                    } else {
                                        System.out.println("Incorrect input");
                                    }
                                } else {
                                    System.out.println("\nERROR: insufficient funds or not enough tickets available for purchase!\n");
                                }
                                break;
                            case 3:
                                if (srch.getSilver().getAvailableTickets() > amountOfTickets && thisUser.getMoneyAvailable() > amountOfTickets * srch.getSilver().getPrice()) {
                                    subtotal = srch.getSilver().getPrice() * amountOfTickets;
                                    if(thisUser.getMembership()){
                                        discountAmnt = subtotal * 0.1;
                                        subtotal -= discountAmnt;
                                        System.out.printf("\nThank you for being a TicketMiner member, you saved $%.2f on this purchase!", discountAmnt);
                                    }
                                    taxedAmnt = subtotal * txSalesTax;
                                    unroundedTotal = subtotal + taxedAmnt;
                                    finalTotal = new BigDecimal(unroundedTotal).setScale(2, RoundingMode.HALF_UP);
                                    System.out.println("\nWould you like to confirm " + amountOfTickets + " Silver tickets for " + finalTotal + "?\n1 for YES , 2 for NO\n");
                                    int conf = kb.nextInt();
                                    kb.nextLine();
                                    if (conf == 1) {
                                        InvoiceGenerator.generateInvoice(thisUser, srch, srch.getSilver(), amountOfTickets, "Silver");
                                        thisUser.setMoneyAvailable(thisUser.getMoneyAvailable() - unroundedTotal);
                                        srch.getSilver().setAvailableTickets(srch.getSilver().getAvailableTickets() - amountOfTickets);
                                        srch.getSilver().setRevenue(amountOfTickets);
                                        srch.getSilver().setSeatsSold(amountOfTickets);
                                        srch.setTotalTax(taxedAmnt);
                                        thisUser.setConcertsPurchase(amountOfTickets);
                                        myLogger.logInfo("User " + thisUser.getUsername() + " purchased " + amountOfTickets + " Silver tickets for event " + srch.getName());
                                    } else if (conf == 2) {
                                        System.out.println("\nPurchase cancelled\n");
                                    } else {
                                        System.out.println("\nIncorrect input\n");
                                    }
                                } else {
                                    System.out.println("\nERROR: insufficient funds or not enough tickets available for purchase!\n");
                                }
                                break;
                            case 4:
                                if (srch.getBronze().getAvailableTickets() > amountOfTickets && thisUser.getMoneyAvailable() > amountOfTickets * srch.getBronze().getPrice()) {
                                    subtotal = srch.getBronze().getPrice() * amountOfTickets;
                                    if(thisUser.getMembership()){
                                        discountAmnt = subtotal * 0.1;
                                        subtotal -= discountAmnt;
                                        System.out.printf("\nThank you for being a TicketMiner member, you saved $%.2f on this purchase!", discountAmnt);
                                    }
                                    taxedAmnt = subtotal * txSalesTax;
                                    unroundedTotal = subtotal + taxedAmnt;
                                    finalTotal = new BigDecimal(unroundedTotal).setScale(2, RoundingMode.HALF_UP);
                                    System.out.println("\nWould you like to confirm " + amountOfTickets + " Bronze tickets for " + finalTotal + "?\n1 for YES , 2 for NO\n");
                                    int conf = kb.nextInt();
                                    kb.nextLine();
                                    if (conf == 1) {
                                        InvoiceGenerator.generateInvoice(thisUser, srch, srch.getBronze(), amountOfTickets, "Bronze");
                                        thisUser.setMoneyAvailable(thisUser.getMoneyAvailable() - unroundedTotal);
                                        srch.getBronze().setAvailableTickets(srch.getBronze().getAvailableTickets() - amountOfTickets);
                                        srch.getBronze().setRevenue(amountOfTickets);
                                        srch.getBronze().setSeatsSold(amountOfTickets);
                                        srch.setTotalTax(taxedAmnt);
                                        thisUser.setConcertsPurchase(amountOfTickets);
                                        myLogger.logInfo("User " + thisUser.getUsername() + " purchased " + amountOfTickets + " Bronze tickets for event " + srch.getName());
                                    } else if (conf == 2) {
                                        System.out.println("\nPurchase cancelled\n");
                                    } else {
                                        System.out.println("\nIncorrect input\n");
                                    }
                                } else {
                                    System.out.println("\nERROR: insufficient funds or not enough tickets available for purchase!\n");
                                }
                                break;
                            case 5:
                                if (srch.getGa().getAvailableTickets() > amountOfTickets && thisUser.getMoneyAvailable() > amountOfTickets * srch.getGa().getPrice()) {
                                    subtotal = srch.getGa().getPrice() * amountOfTickets;
                                    if(thisUser.getMembership()){
                                        discountAmnt = subtotal * 0.1;
                                        subtotal -= discountAmnt;
                                        System.out.printf("\nThank you for being a TicketMiner member, you saved $%.2f on this purchase!", discountAmnt);
                                    }
                                    taxedAmnt = subtotal * txSalesTax;
                                    unroundedTotal = subtotal + taxedAmnt;
                                    finalTotal = new BigDecimal(unroundedTotal).setScale(2, RoundingMode.HALF_UP);
                                    System.out.println("\nWould you like to confirm " + amountOfTickets + " GA tickets for " + finalTotal +  "?\n 1 for YES , 2 for NO\n");
                                    int conf = kb.nextInt();
                                    kb.nextLine();
                                    if (conf == 1) {
                                        InvoiceGenerator.generateInvoice(thisUser, srch, srch.getGa(), amountOfTickets, "General Admission");
                                        thisUser.setMoneyAvailable(thisUser.getMoneyAvailable() - unroundedTotal);
                                        srch.getGa().setAvailableTickets(srch.getGa().getAvailableTickets() - amountOfTickets);
                                        srch.getGa().setRevenue(amountOfTickets);
                                        srch.getGa().setSeatsSold(amountOfTickets);
                                        srch.setTotalTax(taxedAmnt);
                                        thisUser.setConcertsPurchase(amountOfTickets);
                                        myLogger.logInfo("User " + thisUser.getUsername() + " purchased " + amountOfTickets + " GA tickets for event " + srch.getName());
                                    } else if (conf == 2) {
                                        System.out.println("\nPurchase cancelled\n");
                                    } else {
                                        System.out.println("\nIncorrect input\n");
                                    }
                                } else {
                                    System.out.println("\nERROR: insufficient funds or not enough tickets available for purchase!\n");
                                }
                                break;
                            default:
                                System.out.println("\nINVALID TICKET TYPE\n");
                        }
                        // Update event data and user data
                        srch.setRevenue();
                        srch.setSeatsSold();
                        srch.setActualProfit();
                        srch.setTotalDiscounted(discountAmnt);
                        thisUser.setMoneySaved(discountAmnt);
                    }
                } else {
                    purchasingFlag = false;
                }
            }
        }
    }
}
