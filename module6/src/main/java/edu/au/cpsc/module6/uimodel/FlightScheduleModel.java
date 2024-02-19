package edu.au.cpsc.module6.uimodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FlightScheduleModel {

    private final StringProperty flightDesignator;
    private final StringProperty departureAirportIdent;
    private final StringProperty arrivalAirportIdent;
    private final StringProperty daysOfWeek;
    private final BooleanProperty modifiedProperty;
    private final BooleanProperty newProperty;
    private BooleanProperty fdValid = new SimpleBooleanProperty(true);
    private BooleanProperty daiValid = new SimpleBooleanProperty(true);
    private BooleanProperty aaiValid = new SimpleBooleanProperty(true);

    public FlightScheduleModel() {
        flightDesignator = new SimpleStringProperty();
        flightDesignator.setValue("");
        departureAirportIdent = new SimpleStringProperty();
        departureAirportIdent.setValue("");
        arrivalAirportIdent = new SimpleStringProperty();
        arrivalAirportIdent.setValue("");
        daysOfWeek = new SimpleStringProperty();
        modifiedProperty = new SimpleBooleanProperty();
        newProperty = new SimpleBooleanProperty();
        flightDesignator.addListener(((observable, oldValue, newValue) -> setModified(true)));
        departureAirportIdent.addListener(((observable, oldValue, newValue) -> setModified(true)));
        arrivalAirportIdent.addListener(((observable, oldValue, newValue) -> setModified(true)));
        daysOfWeek.addListener(((observable, oldValue, newValue) -> setModified(true)));
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
    public BooleanProperty modifiedProperty() { return modifiedProperty; }
    public BooleanProperty newProperty() { return newProperty; }

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
    public boolean isModified() { return modifiedProperty.get(); }
    public void setModified(boolean m) { modifiedProperty.set(m); }
    public boolean isNew() { return newProperty.get(); }
    public void setNew(boolean n) { newProperty.set(n); }
    public BooleanProperty fdValidProperty() { return fdValid; }
    public BooleanProperty daiValidProperty() { return daiValid; }
    public BooleanProperty aaiValidProperty() { return aaiValid; }
    public boolean isValid() {
        if (flightDesignator.getValue().isEmpty()) {
            fdValid.set(false);
        } else {
            fdValid.set(true);
        }

        if (departureAirportIdent.getValue().isEmpty()) {
            daiValid.set(false);
        } else {
            daiValid.set(true);
        }

        if (arrivalAirportIdent.getValue().isEmpty()) {
            aaiValid.set(false);
        } else {
            aaiValid.set(true);
        }
        return fdValid.getValue() && daiValid.getValue() && aaiValid.getValue();
    }

}
