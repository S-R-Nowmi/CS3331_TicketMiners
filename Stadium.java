public class Stadium extends Venue{
    private boolean hasFireworks;
    private double fireworksPrice;

    public Stadium() {
    }

    public Stadium(String name, double capacity, double reservationPrice, Boolean hasFireworks) {
        super(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
     }

    public Stadium(String name, double capacity, double reservationPrice, Boolean hasFireworks, double fireworksPrice){
        super(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
        this.fireworksPrice = fireworksPrice;
    } 

    public void setStadiumAttributesNoFireworks(String name, double capacity, double reservationPrice , Boolean hasFireworks){
        super.setAttributes(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
    }

    public void setStadiumAttributesFireworks(String name, double capacity, double reservationPrice , Boolean hasFireworks , double fireworksPrice){
        super.setAttributes(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
        this.fireworksPrice = fireworksPrice;
    }             
}