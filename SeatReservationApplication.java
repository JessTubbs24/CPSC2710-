package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SeatReservationApplication extends Application {

    private SeatReservation seatReservation;

    @Override
    public void start(Stage stage) throws IOException {
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("ABC123");
        seatReservation.setFlightDate("2024-01-22");
        seatReservation.setFirstName("John");
        seatReservation.setLastName("Doe");
        seatReservation.setNumberOfPassengers(1);
        seatReservation.setFlyingWithInfant(false);

        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        HBox buttonBox = new HBox();

        // Labels
        gridPane.add(new Label("Flight Designator:"), 0, 0);
        gridPane.add(new Label("Flight Date:"), 0, 1);
        gridPane.add(new Label("First Name:"), 0, 2);
        gridPane.add(new Label("Last Name:"), 0, 3);
        gridPane.add(new Label("Number of Passengers:"), 0, 4);
        gridPane.add(new Label("Flying with Infant:"), 0, 5);

        // Input Controls
        TextField flightDesignatorField = new TextField();
        DatePicker flightDateDatePicker = new DatePicker();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField numberOfPassengersField = new TextField("1");
        numberOfPassengersField.setEditable(false);
        CheckBox flyingWithInfantCheckBox = new CheckBox();

        gridPane.add(flightDesignatorField, 1, 0);
        gridPane.add(flightDateDatePicker, 1, 1);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(numberOfPassengersField, 1, 4);
        gridPane.add(flyingWithInfantCheckBox, 1, 5);

        // Cancel and Save Buttons
        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");

        buttonBox.getChildren().addAll(cancelButton, saveButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new javafx.geometry.Insets(10));
        buttonBox.setAlignment(javafx.geometry.Pos.TOP_RIGHT);

        // Set action for Cancel button
        cancelButton.setOnAction(e -> {
            // Display message on console
            System.out.println("Cancel clicked");
            // Exit the application using Platform.exit()
            Platform.exit();
        });

        // Set action for Save button
        saveButton.setOnAction(e -> {
            displayInputValues(
                    flightDesignatorField.getText(),
                    flightDateDatePicker.getValue().toString(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    numberOfPassengersField.getText(),
                    flyingWithInfantCheckBox.isSelected());
            stage.close();
        });

        // Set up the layout
        root.setCenter(gridPane);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Seat Reservation Editor");
        stage.setScene(scene);
        stage.show();
    }

    private void updateUI() {
        // Update controls with seatReservation values
        flightDesignatorField.setText(seatReservation.getFlightDesignator());
        flightDateDatePicker.setValue(seatReservation.getFlightDate());
        firstNameField.setText(seatReservation.getFirstName());
        lastNameField.setText(seatReservation.getLastName());
        numberOfPassengersField.setText(String.valueOf(seatReservation.getNumberOfPassengers()));
        flyingWithInfantCheckBox.setSelected(seatReservation.isFlyingWithInfant());
    }

    private void displayInputValues(String flightDesignator, String flightDate, String firstName, String lastName,
            String numberOfPassengers, boolean flyingWithInfant) {
        // Placeholder logic to display input values
        System.out.println("Flight Designator: " + flightDesignator);
        System.out.println("Flight Date: " + flightDate);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Number of Passengers: " + numberOfPassengers);
        System.out.println("Flying with Infant: " + flyingWithInfant);
    }

    public static void main(String[] args) {
        launch(args);
    }
}