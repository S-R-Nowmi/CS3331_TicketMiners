import java.util.*;

public class Login {
    
    private Map<Integer,Event> eventsDict;
    private Map<Integer , Customer> customerDict;

    public Login(){
        //hardcoded to always read original file provided, resetting all event and customer values
        CreateStoreEvents eventDictCreator = new CreateStoreEvents("EventListPA4.csv");
        CreateStoreCustomers custDictCreator = new CreateStoreCustomers("CustomerListPA4.csv");
        this.customerDict = custDictCreator.createEventDict();
        this.eventsDict = eventDictCreator.createEventDict();
    }    

    public Login(Map<Integer,Customer> customerDict , Map<Integer,Event> eventsDict){
        //constructor for passing pre-loaded dictionaries
        this.customerDict = customerDict;
        this.eventsDict = eventsDict;
    }

    //initial menu encountered, will call other methods based on input, or terminate the program
    public void Run(){
        Scanner kb = new Scanner(System.in);
        boolean programRunning = true;
        while(programRunning){
            try{
            System.out.println("\nAre you a customer or administrator?\n1 for Customer, 2 for Administrator, 0 to exit\n");
            int input = kb.nextInt();
            kb.nextLine();
            switch (input) {
                case 1:
                    CustLogin();
                    break;
                case 2:
                    AdminLogin();
                    break;
                case 0:
                    System.out.println("\nExiting program\n");
                    programRunning = false; //set this flag to exit the program when user is done 
                    break;
                default:
                    System.out.println("\nInvalid input\n");
                    break;
            }    
        }catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid integer for the event ID\n");
            kb.nextLine();
        }
        }
        kb.close();
        myLogger.close();
        CsvFileCreator creator = new CsvFileCreator();
        //will create updated csv files at the very end of the program to enusure all values are correct
        creator.newEventsCsv(eventsDict);
        creator.newCustomerCsv(customerDict);
    }

    //menu if user logged in as administrator
    public void AdminLogin(){
        Scanner kb = new Scanner(System.in);
        Map<Integer,Event> eventsDict = this.eventsDict;
        boolean continueSearching = true;
        myLogger.logInfo("Administrator logged in");
        while(continueSearching){
            System.out.println("\nEnter A to inquire event by ID |OR| Enter B to inquire event by name |OR| Enter Q to quit\n");
            String inp = kb.nextLine();
            if(inp.equalsIgnoreCase("Q")){
                System.out.println("\nExiting program\n");
                myLogger.logInfo("Administrator logged out");
                continueSearching = false;
            }
            //administrator wants to search for event by ID
            if(inp.equalsIgnoreCase("A")){
                try {
                    System.out.println("\nEnter the ID of the event:\n");
                    int eventId = kb.nextInt();
                    kb.nextLine();  
                    Event event = eventsDict.get(eventId);
                    if (event != null) {
                        System.out.println("\nThe information related to the ID you entered: \n");
                        System.out.println(event.adminToString());
                        myLogger.logInfo("Administrator pulled up event " + eventId);
                    } else {
                        System.out.println("\nEvent not found with the given ID\n");
                        myLogger.logWarning("Event " + eventId + " not found");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a valid integer for the event ID\n");
                }
            //administrator wants to search for event by name
            } else if (inp.equalsIgnoreCase("B")) {
                System.out.println("\nEnter the name of the event:\n");
                String eventName = kb.nextLine();
                Event event = null;
                for (Event e : eventsDict.values()) {
                    if (e.getName().equalsIgnoreCase(eventName)) {
                        event = e;
                        break;
                    }
                }
                if (event != null) {
                    System.out.println("\nThe information related to the name you entered: \n");
                    System.out.println(event.adminToString());
                    myLogger.logInfo("Administrator pulled up event " + eventName);
                } else {
                    myLogger.logWarning(eventName + " not found");
                    System.out.println("\nEvent not found with the given name\n");
                }
            }
        }
    }

    //menu if user logged in as customer
    public void CustLogin(){
        boolean flag = true;
        Map<Integer,Customer> customerDict = this.customerDict;
        Scanner kb = new Scanner(System.in);
        while (flag){
            try{
            System.out.println("\nPlease enter your user ID: \nMust have no spaces and correct capitalization! \nOr enter 0 to EXIT\n");
            int userID = kb.nextInt();
            kb.nextLine();
            if(userID == 0){
                flag = false;
            }
            else if(customerDict.containsKey(userID)){
                Customer thisUser = customerDict.get(userID);
                System.out.println("\nPlease enter your first name: \nMust have no spaces and correct capitalization! \n");
                if(thisUser.getFirstName().equals(kb.nextLine())){
                    System.out.println("\nPlease enter your last name: \nMust have no spaces and correct capitalization! \n");
                    if(thisUser.getLastName().equals(kb.nextLine())){
                        System.out.println("\nPlease enter your username: \nMust have no spaces and correct capitalization! \n");
                        if(thisUser.getUsername().equals(kb.nextLine())){
                            System.out.println("\nPlease enter your password: \nMust have no spaces and correct capitalization! \n");
                            //this is what runs if the user logged in succesfully
                            if(thisUser.getPassword().equals(kb.nextLine())){
                                System.out.println("\nLOGGED IN SUCCESFULLY\n");
                                myLogger.logInfo("User: " + thisUser.getUsername() + " logged in succesfully");
                                PurchasingTickets purchaser = new PurchasingTickets(userID , eventsDict , customerDict);
                                purchaser.Purchase();
                                } else { 
                                    System.out.println("\nWRONG PASSWORD\n");
                                    myLogger.logWarning("User: " + thisUser.getUsername() + " entered the wrong password");
                                }
                            } else { 
                                System.out.println("\nUSERNAME NOT FOUND\n");
                                myLogger.logWarning("User: " + thisUser.getUsername() + " entered the wrong username");
                            }
                        } else {
                            System.out.println("\nINVALID LAST NAME\n");
                            myLogger.logWarning("User: " + thisUser.getUsername() + " entered the wrong last name");
                        } 
                    } else {
                        System.out.println("\nINVALID FIRST NAME\n");
                        myLogger.logWarning("User: " + thisUser.getUsername() + " entered the wrong first name");
                    }
                } else {System.out.println("\nINVALID USER ID\n");}
            } catch (Exception e){
                myLogger.logError("An ERROR occured " + e.getMessage());
                System.err.println("\nERROR: ENTER CORRECT INPUT\n");
                kb.nextLine();
            } 
        }
    }




}
