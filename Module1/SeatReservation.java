public class SeatReservation {
    private String flightDesignator;
    private java.time.LocalDate flightDate;
    private String firstName;
    private String lastName;

    public String getflightDesignator() {
        return null;
    }

    public void setFlightDesignator(String fd) {
        // Check the number of characters
        int length = fd.length();
        if (length < 4 || length > 6) {
            throw new IllegalArgumentException("Flight designator must have 4 to 6 characters");
        }

        // If the length is valid, set the flight designator
        this.flightDesignator = fd;
    }

    public java.time.LocalDate getFlightDate() {
        return null;
    }

    public void setFlightDate(java.time.LocalDate date) {

    }

    public String getFirstName() {
        return null;
    }

    public void setFirstName(String fn) {

    }

    public String getLastName() {
        return null;
    }

    public void setLastName(String ln) {

    }

    public String toString() {
        return "SeatReservation{" +
                "flightDesignator=" + (flightDesignator != null ? flightDesignator : "null") +
                ", flightDate=" + (flightDate != null ? flightDate.toString() : "null") +
                ", firstName=" + (firstName != null ? firstName : "null") +
                ", lastName=" + (lastName != null ? lastName : "null") +
                '}';
    }

}
