public abstract class Venue{
    private String name;
    private double capacity;
    private double reservationPrice;

    public Venue() {}

    public Venue(String name, double capacity, double reservationPrice) {
        this.name = name;
        this.capacity = capacity;
        this.reservationPrice = reservationPrice;
    }

    public void setAttributes(String name, double capacity, double reservationPrice){
        this.name = name;
        this.capacity = capacity;
        this.reservationPrice = reservationPrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getReservationPrice() {
        return this.reservationPrice;
    }

    public void setReservationPrice(double reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    @Override
    public String toString() {
        return 
            " " + getName() ;
    }

}