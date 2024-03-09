import java.util.*;

public class CreateStoreCustomers {
    private String path;

    public CreateStoreCustomers() {
    }

    public CreateStoreCustomers(String path) {
        this.path = path;
    }

    public Map<Integer , Customer> createEventDict(){
        String path = this.path;
        Map<String, Customer> firstNameDict = new HashMap<>();
        Map<Integer, Customer> customerDict = new HashMap<>();
        int customerId , concertsPurchased; 
        String firstName , lastName , username , password;
        double balance;
        boolean membership;
        ReadFiles myReader = new ReadFiles(path);
        String [][] myCustArr = myReader.gettingArray();
        int idCol = -1 , moneyCol = -1 , lastNameCol = -1 , firstNameCol = -1 , passwordCol = -1 , ticketsPurchasedCol = -1 , memberCol = -1 , usernameCol = -1;

        //this block takes care of the columns being in any order
        for (int col = 0; col < myCustArr[0].length; col++){
            String columnHeader = myCustArr[0][col].toLowerCase();
            switch (columnHeader) {
                case "money available":
                    moneyCol = col;
                    break;
                case "last name":
                    lastNameCol = col;
                    break;
                case "first name":
                    firstNameCol = col;
                    break;
                case "id":
                    idCol = col;
                    break;
                case "password":
                    passwordCol = col;
                    break;
                case "username":
                    usernameCol = col;
                    break;
                case "tickets purchased":
                    ticketsPurchasedCol = col;
                    break;
                case "ticketminer membership":
                    memberCol = col;
                    break;
                default:
                    break;
            }
        }

        for (int row = 1; row < myCustArr.length; row++){
            customerId = Integer.parseInt(myCustArr[row][idCol]);
            firstName = myCustArr[row][firstNameCol];
            lastName = myCustArr[row][lastNameCol];
            balance = Double.parseDouble(myCustArr[row][moneyCol]);
            concertsPurchased = Integer.parseInt(myCustArr[row][ticketsPurchasedCol]);
            membership = Boolean.parseBoolean(myCustArr[row][memberCol]);
            username = myCustArr[row][usernameCol];
            password = myCustArr[row][passwordCol];

            Customer customer = new Customer(customerId, firstName, lastName, balance, concertsPurchased, membership, username, password);
            customerDict.put(customerId , customer);
            firstNameDict.put(firstName , customer);
        }

        return customerDict;
    }
}

