import java.time.LocalDate;

public class SeatReservation {
    private String flightDesignator;
    private LocalDate flightDate;
    private String firstName;
    private String lastName;
    private int numberOfPassengers;
    private int numberOfBags;
    private boolean flyingWithInfant;

    public String getFlightDesignator() {
        return flightDesignator;
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
        return flightDate;
    }

    public void setFlightDate(java.time.LocalDate date) {
        this.flightDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public boolean isFlyingWithInfant() {
        return this.flyingWithInfant;
    }
    public void setFlyingWithInfant(boolean input) {
        this.flyingWithInfant = input;
    }

    public void makeFlyingWithInfant() {
        this.flyingWithInfant = true;
    }

    public void makeNotFlyingWithInfant() {
        this.flyingWithInfant = false;
    }

    @Override
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator=" + (flightDesignator != null ? flightDesignator : "null") +
                ", flightDate=" + (flightDate != null ? flightDate.toString() : "null") +
                ", firstName=" + (firstName != null ? firstName : "null") +
                ", lastName=" + (lastName != null ? lastName : "null") +
                ", numberOfBags=" + numberOfBags +
                ", flyingWithInfant=" + flyingWithInfant +
                '}';
    }
}