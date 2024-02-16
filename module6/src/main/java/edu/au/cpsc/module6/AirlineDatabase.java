package edu.au.cpsc.module6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AirlineDatabase implements Serializable {
    public final ObservableList<ScheduledFlight> scheduledFlights;

    public AirlineDatabase() {
        scheduledFlights =  FXCollections.observableList((new ArrayList<>()));
    }

    public ObservableList<ScheduledFlight> getScheduledFlights() {
        return scheduledFlights;
    }

    public void addScheduledFlight(ScheduledFlight sf) {
        scheduledFlights.add(sf);
    }
//    public void removeScheduledFlight(ScheduledFlight sf) {
//        scheduledFlights.remove(sf);
//    }
//
//    public void updateScheduledFlight(ScheduledFlight sf) {
//        //we already reference the updated object
//    }
}
