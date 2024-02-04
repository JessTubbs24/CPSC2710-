package edu.au.cpsc.module4;

import java.util.ArrayList;
import java.util.List;

public class AirlineDatabase {
    private List<ScheduledFlight> scheduledFlights;
    public AirlineDatabase() {
        scheduledFlights = new ArrayList<>();
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }

    public void addScheduledFlight(ScheduledFlight sf) {
        scheduledFlights.add(sf);
    }

    public void removeScheduledFlight(ScheduledFlight sf) {
        scheduledFlights.remove(sf);
    }

    public void updateScheduledFlight(ScheduledFlight sf) {
        //we already reference the updated object
    }
}
