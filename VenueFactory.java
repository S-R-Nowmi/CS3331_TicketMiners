public class VenueFactory {

    public VenueFactory() {
    }

    public Venue createVenue(String venueType){
        if(venueType.equalsIgnoreCase("Stadium")){
            return new Stadium();
        } else if(venueType.equalsIgnoreCase("Arena")){
            return new Arena();
        } else if(venueType.equalsIgnoreCase("Auditorium")){
            return new Auditorium();
        } else {
            return new OpenAir();
        }
    }
}
