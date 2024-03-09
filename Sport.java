public class Sport extends Event {

    public Sport() {
    }

    public Sport(int id, String name, String date, String time, Venue venue, Vip vip, Gold gold, Silver silver, Bronze bronze, GeneralAdmission ga){
        super(id, name, date, time, venue, vip, gold, silver, bronze, ga);
    }
}
