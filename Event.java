public abstract class Event{
    private int id;
    private String name;
    private String date;
    private String time;
    private Venue venue;
    private Tickets tickets;
    private Vip vip;
    private Gold gold;
    private Silver silver;
    private Bronze bronze;
    private GeneralAdmission ga;
    private double totalTax = 0;
    private double revenue = 0;
    private int seatsSold = 0;
    private double expectedProfit = 0;
    private double actualProfit = 0;
    private double totalDiscounted = 0;


    public Event() {}

    public Event(int id, String name, String date, String time, Venue venue, Vip vip, Gold gold, Silver silver, Bronze bronze, GeneralAdmission ga) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.vip = vip;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.ga = ga;
        this.expectedProfit = (getVip().getAvailableTickets() * getVip().getPrice()) + (getGold().getAvailableTickets() * getGold().getPrice()) + (getSilver().getAvailableTickets() * getSilver().getPrice()) + (getBronze().getAvailableTickets() * getBronze().getPrice()) + (getGa().getAvailableTickets() * getGa().getPrice()) - venue.getReservationPrice();
        this.actualProfit = 0 - venue.getReservationPrice();
    }

    public void setTotalDiscounted(double discount){
        this.totalDiscounted += discount;
    }

    public double getTotalDiscounted(){
        return this.totalDiscounted;
    }

    public double getTotalTax(){
        return this.totalTax;
    }

    public void setTotalTax(double tax){
        this.totalTax += tax;
    }

    public double getActualProfit(){
        return this.actualProfit;
    }

    public void setActualProfit(){
        this.actualProfit += (getVip().getRevenue() + getGold().getRevenue() + getSilver().getRevenue() + getBronze().getRevenue() + getGa().getRevenue());
    }

    public double getExpectedProfit(){
        return this.expectedProfit;
    }

    public int getSeatsSold(){
        return this.seatsSold;
    }

    public void setSeatsSold(){
        this.seatsSold = (vip.getSeatsSold() + gold.getSeatsSold() + silver.getSeatsSold() + bronze.getSeatsSold() + ga.getSeatsSold());
    }

    public double getRevenue(){
        return this.revenue;
    }

    public void setRevenue(){
        this.revenue = (vip.getRevenue() + gold.getRevenue() + silver.getRevenue() + bronze.getRevenue() + ga.getRevenue());
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Venue getVenue() {
        return this.venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Tickets getTickets() {
        return this.tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

    public Vip getVip() {
        return this.vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public Gold getGold() {
        return this.gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public Silver getSilver() {
        return this.silver;
    }

    public void setSilver(Silver silver) {
        this.silver = silver;
    }

    public Bronze getBronze() {
        return this.bronze;
    }

    public void setBronze(Bronze bronze) {
        this.bronze = bronze;
    }

    public GeneralAdmission getGa() {
        return this.ga;
    }

    public void setGa(GeneralAdmission ga) {
        this.ga = ga;
    }



    @Override
    public String toString() {
        return 
            "\n=======================================================" + 
            "\nID = " + getId() +
            "\nName = " + getName() +
            "\nDate = " + getDate() +
            "\nTime = " + getTime() +
            "\nVenue Name =" + getVenue().toString() +
            "\nVIP Price =" + getVip().toString() +
            "\nGold Price =" + getGold().toString() +
            "\nSilver Price =" + getSilver().toString() +
            "\nBronze = Price" + getBronze().toString() +
            "\nGA = Price" + getGa().toString();
    }

    public String adminToString(){
        return 
            "\n=======================================================" + 
            "\nID = " + getId() +
            "\nName = " + getName() +
            "\nDate = " + getDate() +
            "\nTime = " + getTime() +
            "\nVenue Name = " + getVenue().toString() +
            "\nCapacity = " + getVenue().getCapacity() + 
            "\nTotal Seats Sold = " + getSeatsSold() +
            "\nVIP Seats Sold = " + getVip().getSeatsSold() +
            "\nGold Seats Sold = " + getGold().getSeatsSold() +
            "\nSilver Seats Sold = " + getSilver().getSeatsSold() +
            "\nBronze Seats Sold = " + getBronze().getSeatsSold() +
            "\nGA Seats Sold = " + getGa().getSeatsSold() +
            "\nVIP Revenue = " + getVip().getRevenue() +
            "\nGold Revenue = " + getGold().getRevenue() +
            "\nSilver Revenue = " + getSilver().getRevenue()+
            "\nBronze Revenue = " + getBronze().getRevenue() +
            "\nGA Revenue = " + getGa().getRevenue() + 
            "\nTotal Revenue for all Tickets = " + (getVip().getRevenue() + getGold().getRevenue() + getSilver().getRevenue() + getBronze().getRevenue() + getGa().getRevenue()) +
            "\nExpected Profit = "  + getExpectedProfit() +
            "\nActual Profit = " + getActualProfit() +
            "\nTotal Tax Generated = " + getTotalTax() + 
            "\nTotal Money Saved from Discounts = " + getTotalDiscounted();
    }
}