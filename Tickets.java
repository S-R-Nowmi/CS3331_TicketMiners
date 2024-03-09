public abstract class Tickets{

    private double price;
    private double unavailablePct;
    private double capacity;
    private double pctOfTickets;
    private double availableTickets;
    private double revenue = 0;
    private int seatsSold = 0;

    
    public Tickets() {
    }


    public Tickets(double price, double unavailablePct, double capacity, double pctOfTickets) {
        this.price = price;
        this.unavailablePct = unavailablePct;
        this.capacity = capacity;
        this.pctOfTickets = pctOfTickets;
        calculateAvailableTickets(capacity , unavailablePct , pctOfTickets);
    }
    
    public void calculateAvailableTickets(double capacity , double unavailablePct , double pctOfTickets){
        double adjustedCapacity = capacity * (1 -  unavailablePct / 100);
        this.availableTickets =  adjustedCapacity * (pctOfTickets / 100); 
    }

    public int getSeatsSold(){
        return this.seatsSold;
    }

    public void setSeatsSold(int amountOfTickets){
        this.seatsSold += amountOfTickets;
    }

    public double getRevenue(){
        return this.revenue;
    }

    public void setRevenue(int amountOfTickets){
        this.revenue += amountOfTickets * this.price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getUnavailablePct() {
        return this.unavailablePct;
    }

    public void setUnavailablePct(double unavailablePct) {
        this.unavailablePct = unavailablePct;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getPctOfTickets() {
        return this.pctOfTickets;
    }

    public void setPctOfTickets(double pctOfTickets) {
        this.pctOfTickets = pctOfTickets;
    }

    public double getAvailableTickets() {
        return this.availableTickets;
    }

    public void setAvailableTickets(double availableTickets) {
        this.availableTickets = availableTickets;
    }

    @Override
    public String toString() {
        return " " + getPrice() ;
    }

}