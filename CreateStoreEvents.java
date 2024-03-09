import java.util.*;

public class CreateStoreEvents {
    private String path;

    public CreateStoreEvents() {
    }

    public CreateStoreEvents(String path) {
        this.path = path;
    }

    public Map<Integer , Event> createEventDict(){
        String path = this.path;
        Map<Integer, Event> eventsIdDict = new HashMap<>();
        Map<String, Venue> venuesDict = new HashMap<>();
        Set<Integer> eventsIdSet = new HashSet<>();
        Set<String> venueLocations = new HashSet<>();
        String eventType , eventName , venueType , venueName;
        double vipPrice , goldPrice , silverPrice , bronzePrice , gaPrice , unavailablePct , capacity , vipPct , goldPct , silverPct , bronzePct , gaPct , reservationPrice;
        int eventId;
        boolean fworks;
        VenueFactory myFactory = new VenueFactory();
        int idCol = -1 , eventTypeCol = -1 , eventNameCol = -1 , vipPriceCol = -1 , goldPriceCol = -1 , silverPriceCol = -1 , bronzePriceCol = -1 , gaPriceCol = -1 , vipPctCol = -1 , goldPctCol = -1 , silverPctCol = -1 , bronzePctCol = -1 , gaPctCol = -1 , venueNameCol = -1 , venueTypeCol = -1 , unavailablePctCol = -1 , reservationPriceCol = -1 , capacityCol = -1 , fworksPriceCol = -1 , fworksBoolCol = -1 , timeCol = -1 , dateCol = -1 , reservedPctCol = -1 ;

        ReadFiles myReader = new ReadFiles(path);
        String [][] myArr = myReader.gettingArray();

        //this block takes care of the columns being in any order
        for (int col = 0; col < myArr[0].length; col++){
            String columnHeader = myArr[0][col].toLowerCase();

            switch(columnHeader){
                case "event id":
                    idCol = col;
                    break;
                case "name":
                    eventNameCol = col;
                    break;
                case "gold price":
                    goldPriceCol = col;
                    break;
                case "vip price":
                    vipPriceCol = col;
                    break;
                case "event type":
                    eventTypeCol = col;
                    break;
                case "venue type":
                    venueTypeCol = col;
                    break;
                case "bronze pct":
                    bronzePctCol = col;
                    break;
                case "time":
                    timeCol = col;
                    break;
                case "general admission pct":
                    gaPctCol = col;
                    break;
                case "silver price":
                    silverPriceCol = col;
                    break;
                case "date":
                    dateCol = col;
                    break;
                case "bronze price":
                    bronzePriceCol = col;
                    break;
                case "fireworks cost":
                    fworksPriceCol = col;
                    break;
                case "general admission price":
                    gaPriceCol = col;
                    break;
                case "pct seats unavailable":
                    unavailablePctCol = col;
                    break;
                case "capacity":
                    capacityCol = col;
                    break;
                case "reserved extra pct":
                    reservedPctCol = col;
                    break;
                case "cost":
                    reservationPriceCol = col;
                    break;
                case "vip pct":
                    vipPctCol = col;
                    break;
                case "venue name":
                    venueNameCol = col;
                    break;
                case "gold pct":
                    goldPctCol = col;
                    break;
                case "fireworks planned":
                    fworksBoolCol = col;
                    break;
                case "silver pct":
                    silverPctCol = col;
                    break;
                default:
                    break;
            }
        }

        for (int row = 1; row < myArr.length; row++){
            eventId = Integer.parseInt(myArr[row][idCol]);
            eventType = myArr[row][eventTypeCol];
            eventName = myArr[row][eventNameCol];
            vipPrice = Double.parseDouble(myArr[row][vipPriceCol]);
            goldPrice = Double.parseDouble(myArr[row][goldPriceCol]);
            silverPrice = Double.parseDouble(myArr[row][silverPriceCol]);
            bronzePrice = Double.parseDouble(myArr[row][bronzePriceCol]);
            gaPrice = Double.parseDouble(myArr[row][gaPriceCol]);
            venueName = myArr[row][venueNameCol];
            venueType = myArr[row][venueTypeCol];
            unavailablePct = Double.parseDouble(myArr[row][unavailablePctCol]);
            capacity = Double.parseDouble(myArr[row][capacityCol]);
            vipPct = Double.parseDouble(myArr[row][vipPctCol]);
            goldPct = Double.parseDouble(myArr[row][goldPctCol]);
            silverPct = Double.parseDouble(myArr[row][silverPctCol]);
            bronzePct = Double.parseDouble(myArr[row][bronzePctCol]);
            gaPct = Double.parseDouble(myArr[row][gaPctCol]);
            reservationPrice = Double.parseDouble(myArr[row][reservationPriceCol]);

            if(!venueLocations.contains(venueName)){
                Venue newVenue = myFactory.createVenue(venueType);
                switch(venueType){
                    case "Stadium":
                        if(myArr[row][fworksBoolCol].equals("Yes")){
                            fworks = true;
                            ((Stadium) newVenue).setStadiumAttributesFireworks(venueName, capacity, reservationPrice, fworks, Double.parseDouble(myArr[row][fworksPriceCol]));
                            venueLocations.add(venueName);
                            venuesDict.put(venueName, newVenue);
                        }else{
                            fworks = false;
                            ((Stadium) newVenue).setStadiumAttributesNoFireworks(venueName, capacity, reservationPrice, fworks);
                            venueLocations.add(venueName);
                            venuesDict.put(venueName, newVenue);
                            venueLocations.add(venueName);
                            venuesDict.put(venueName, newVenue);
                        }
                        break;
                    case "Arena":
                        ((Arena) newVenue).setAttributes(venueName, capacity, reservationPrice);
                        venueLocations.add(venueName);
                        venuesDict.put(venueName, newVenue);
                        break;
                    case "Auditorium":
                        ((Auditorium) newVenue).setAttributes(venueName, capacity, reservationPrice);
                        venueLocations.add(venueName);
                        venuesDict.put(venueName, newVenue);
                        break;
                    case "Open Air":
                        if(myArr[row][fworksBoolCol].equals("Yes")){
                            fworks = true;
                            ((OpenAir) newVenue).setOpenAirAttributesFireworks(venueName, capacity, reservationPrice, fworks, Double.parseDouble(myArr[row][fworksPriceCol]));
                            venueLocations.add(venueName);
                            venuesDict.put(venueName, newVenue);
                        }else{
                            fworks = false;
                            ((OpenAir) newVenue).setOpenAirAttributesNoFireworks(venueName, capacity, reservationPrice, fworks);
                            venueLocations.add(venueName);
                            venuesDict.put(venueName, newVenue);
                        }
                        break;
                }
            }
            Vip vip = new Vip(vipPrice, unavailablePct, capacity, vipPct);
            Gold gold = new Gold(goldPrice, unavailablePct, capacity, goldPct);
            Silver silver = new Silver(silverPrice, unavailablePct, capacity, silverPct);
            Bronze bronze = new Bronze(bronzePrice, unavailablePct, capacity, bronzePct);
            GeneralAdmission ga = new GeneralAdmission(gaPrice, unavailablePct, capacity, gaPct);

            if(!eventsIdSet.contains(eventId)){
                switch(eventType){
                    case "Concert":
                        Concert newconcert = new Concert(eventId, eventName, myArr[row][dateCol] , myArr[row][timeCol] , venuesDict.get(venueName) , vip , gold , silver , bronze , ga);
                        eventsIdSet.add(eventId);
                        eventsIdDict.put(eventId , newconcert);
                        break;
                    case "Sport":
                        Sport newsport = new Sport(eventId, eventName, myArr[row][dateCol] , myArr[row][timeCol] , venuesDict.get(venueName) , vip , gold , silver , bronze , ga);
                        eventsIdSet.add(eventId);
                        eventsIdDict.put(eventId , newsport);
                        break;
                    case "Festival":
                        Festival newfestival = new Festival(eventId, eventName, myArr[row][dateCol] , myArr[row][timeCol] , venuesDict.get(venueName) , vip , gold , silver , bronze , ga);
                        eventsIdSet.add(eventId);
                        eventsIdDict.put(eventId , newfestival);
                        break;
                }
            }
        }
        return eventsIdDict;
}
}

