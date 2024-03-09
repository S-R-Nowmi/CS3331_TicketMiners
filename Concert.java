public class Concert extends Event{

    public Concert() {
    }
    
    public Concert(int id, String name, String date, String time, Venue venue, Vip vip, Gold gold, Silver silver, Bronze bronze, GeneralAdmission ga){
        super(id, name, date, time, venue, vip, gold, silver, bronze, ga);
    }
}
