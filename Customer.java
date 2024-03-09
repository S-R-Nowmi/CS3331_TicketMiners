public class Customer {

    int id;
    String firstName;
    String lastName;
    double moneyAvailable;
    int concertsPurchase;
    boolean membership;
    String username;
    String password;
    double moneySaved = 0.0;

    public Customer() {}

    public Customer(int id, String firstName, String lastName, double moneyAvailable, int concertsPurchase, boolean membership, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneyAvailable = moneyAvailable;
        this.concertsPurchase = concertsPurchase;
        this.membership = membership;
        this.username = username;
        this.password = password;
    }

    public void setMoneySaved(double moneySaved){
        this.moneySaved += moneySaved;
    }

    public double getMoneySaved(){
        return this.moneySaved;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoneyAvailable() {
        return this.moneyAvailable;
    }

    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    public int getConcertsPurchase() {
        return this.concertsPurchase;
    }

    public void setConcertsPurchase(int concertsPurchase) {
        this.concertsPurchase += concertsPurchase;
    }

    public boolean isMembership() {
        return this.membership;
    }

    public boolean getMembership() {
        return this.membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
