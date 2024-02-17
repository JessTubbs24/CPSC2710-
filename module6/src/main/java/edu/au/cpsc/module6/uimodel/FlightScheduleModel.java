package edu.au.cpsc.module6.uimodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FlightScheduleModel {

    private final StringProperty flightDesignator;
    private final StringProperty departureAirportIdent;
    private final StringProperty arrivalAirportIdent;
    private final StringProperty daysOfWeek;

    public FlightScheduleModel() {
        flightDesignator = new SimpleStringProperty();
        departureAirportIdent = new SimpleStringProperty();
        arrivalAirportIdent = new SimpleStringProperty();
        daysOfWeek = new SimpleStringProperty();
    }

    public StringProperty flightDesignatorProperty() {
        return flightDesignator;
    }

    public StringProperty departureAirportIdentProperty() {
        return departureAirportIdent;
    }

    public StringProperty arrivalAirportIdentProperty() {
        return arrivalAirportIdent;
    }
    public StringProperty daysOfWeekProperty() { return daysOfWeek; }

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

    public void setArrivalAirportIdent(java.lang.String arrivalAirportIdent) {
        arrivalAirportIdentProperty().set(arrivalAirportIdent);
    }

    public String getDaysOfWeek() { return daysOfWeek.get(); }

    public void setDaysOfWeek(java.lang.String daysOfWeek) {
        daysOfWeekProperty().set(daysOfWeek);
    }
}
