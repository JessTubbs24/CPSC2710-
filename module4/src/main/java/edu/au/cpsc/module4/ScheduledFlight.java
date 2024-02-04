package edu.au.cpsc.module4;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static javafx.application.Application.launch;

public class ScheduledFlight {
    public ScheduledFlight() {
    }
        private String flightDesignator;
        private String departureAirportIdent;
        private LocalTime departureTime;
        private String arrivalAirportIdent;
        private LocalTime arrivalTime;
        private Set<String> daysOfWeek;

        // Constructor
        public ScheduledFlight(String flightDesignator, String departureAirportIdent, LocalTime departureTime,
                               String arrivalAirportIdent, LocalTime arrivalTime, Set<String> daysOfWeek) {
            this.flightDesignator = flightDesignator;
            this.departureAirportIdent = departureAirportIdent;
            this.departureTime = departureTime;
            this.arrivalAirportIdent = arrivalAirportIdent;
            this.arrivalTime = arrivalTime;
            this.daysOfWeek = (daysOfWeek != null) ? new HashSet<>(daysOfWeek) : new HashSet<>();
        }

        public String getFlightDesignator() {
            return flightDesignator;
        }

        public void setFlightDesignator(String flightDesignator) {
            if (flightDesignator == null) {
                throw new IllegalArgumentException("Flight designator cannot be null.");
            }
            this.flightDesignator = flightDesignator;
        }

        public String getDepartureAirportIdent() {
            return departureAirportIdent;
        }

        public void setDepartureAirportIdent(String departureAirportIdent) {
            if (departureAirportIdent == null) {
                throw new IllegalArgumentException("Departure airport ident cannot be null.");
            }
            this.departureAirportIdent = departureAirportIdent;
        }

        public LocalTime getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(LocalTime departureTime) {
            if (departureTime == null) {
                throw new IllegalArgumentException("Departure time cannot be null.");
            }
            this.departureTime = departureTime;
        }

        public String getArrivalAirportIdent() {
            return arrivalAirportIdent;
        }

        public void setArrivalAirportIdent(String arrivalAirportIdent) {
            if (arrivalAirportIdent == null) {
                throw new IllegalArgumentException("Arrival airport ident cannot be null.");
            }
            this.arrivalAirportIdent = arrivalAirportIdent;
        }

        public LocalTime getArrivalTime() {
            return arrivalTime;
        }


        public void setArrivalTime(LocalTime arrivalTime) {
            if (arrivalTime == null) {
                throw new IllegalArgumentException("Arrival time cannot be null.");
            }
            this.arrivalTime = arrivalTime;
        }

        public Set<String> getDaysOfWeek() {
            return new HashSet<>(daysOfWeek);
        }

        public void setDaysOfWeek(Set<String> daysOfWeek) {
            if (daysOfWeek == null) {
                throw new IllegalArgumentException("Days of week cannot be null.");
            }
            this.daysOfWeek = new HashSet<>(daysOfWeek);
        }

        public static void main(String[] args) {
            launch();
        }
    }
