package edu.au.cpsc.module6.uimodel;

import javafx.beans.property.SimpleStringProperty;

public class FlightScheduleModel {

    private final SimpleStringProperty flightDesignator;
    private final SimpleStringProperty departureAirportIdent;
    private final SimpleStringProperty arrivalAirportIdent;

    public FlightScheduleModel() {
        flightDesignator = new SimpleStringProperty();
        departureAirportIdent = new SimpleStringProperty();
        arrivalAirportIdent = new SimpleStringProperty();
    }

    public SimpleStringProperty flightDesignatorProperty() {
        return flightDesignator;
    }

    public SimpleStringProperty departureAirportIdentProperty() {
        return departureAirportIdent;
    }

    public SimpleStringProperty arrivalAirportIdentProperty() {
        return arrivalAirportIdent;
    }

    public java.lang.String getFlightDesignator() {
        return flightDesignator.get();
    }
    public void setFlightDesignator(java.lang.String flightDesignator) {
        flightDesignatorProperty().set(flightDesignator);
    }
    public java.lang.String getDepartureAirportIdent() {
        return departureAirportIdent.get();
    }
    public void setDepartureAirportIdent(java.lang.String departureAirportIdent) {
        departureAirportIdentProperty().set(departureAirportIdent);
    }
    public java.lang.String getArrivalAirportIdent() {
        return arrivalAirportIdent.get();
    }
}
