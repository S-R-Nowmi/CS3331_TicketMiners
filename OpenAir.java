class OpenAir extends Venue{
    private boolean hasFireworks;
    private double fireworksPrice;


    public OpenAir() {
    }

    public OpenAir(String name, double capacity, double reservationPrice, Boolean hasFireworks) {
        super(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
     }

    public OpenAir(String name, double capacity, double reservationPrice, Boolean hasFireworks, double fireworksPrice){
        super(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
        this.fireworksPrice = fireworksPrice;
    }  

    public void setOpenAirAttributesNoFireworks(String name, double capacity, double reservationPrice , Boolean hasFireworks){
        super.setAttributes(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
    }

    public void setOpenAirAttributesFireworks(String name, double capacity, double reservationPrice , Boolean hasFireworks , double fireworksPrice){
        super.setAttributes(name, capacity, reservationPrice);
        this.hasFireworks = hasFireworks;
        this.fireworksPrice = fireworksPrice;
    }     
}