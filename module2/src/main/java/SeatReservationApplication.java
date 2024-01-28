package edu.au.cpsc.Module2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.time.LocalDate;

import java.io.IOException;

public class SeatReservationApplication extends Application {

    private SeatReservation seatReservation;
    private BorderPane root;
    private GridPane gridPane;
    private HBox buttonBox;
    // Input Controls
    private TextField flightDesignatorField;
    private DatePicker flightDateDatePicker;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField numberOfPassengersField;
    private CheckBox flyingWithInfantCheckBox;

    @Override
    public void start(Stage stage) throws IOException {
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("ABC123");
        seatReservation.setFlightDate(LocalDate.of(2024, 1, 22));
        seatReservation.setFirstName("John");
        seatReservation.setLastName("Doe");
        seatReservation.setNumberOfPassengers(1);
        seatReservation.makeNotFlyingWithInfant();

        root = new BorderPane();
        gridPane = new GridPane();
        buttonBox = new HBox();

        // Labels
        gridPane.add(new Label("Flight Designator:"), 0, 0);
        gridPane.add(new Label("Flight Date:"), 0, 1);
        gridPane.add(new Label("First Name:"), 0, 2);
        gridPane.add(new Label("Last Name:"), 0, 3);
        gridPane.add(new Label("Number of Passengers:"), 0, 4);
        gridPane.add(new Label("Flying with Infant:"), 0, 5);

        // Input Controls
        flightDesignatorField = new TextField();
        flightDateDatePicker = new DatePicker();
        firstNameField = new TextField();
        lastNameField = new TextField();
        numberOfPassengersField = new TextField("1");
        numberOfPassengersField.setEditable(false);
        flyingWithInfantCheckBox = new CheckBox();

        gridPane.add(flightDesignatorField, 1, 0);
        gridPane.add(flightDateDatePicker, 1, 1);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(numberOfPassengersField, 1, 4);
        gridPane.add(flyingWithInfantCheckBox, 1, 5);

        // Call updateUI to display initial values
        updateUI();

        flyingWithInfantCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (flyingWithInfantCheckBox.isSelected()) {
                    numberOfPassengersField.setText("2");
                } else {
                    numberOfPassengersField.setText("1");
                }
            }
        });

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
            try {
                // Populate seatReservation instance variable
                seatReservation.setFlightDesignator(flightDesignatorField.getText());
                seatReservation.setFlightDate(LocalDate.parse(flightDateDatePicker.getValue().toString()));
                seatReservation.setFirstName(firstNameField.getText());
                seatReservation.setLastName(lastNameField.getText());
                seatReservation.setNumberOfPassengers(Integer.parseInt(numberOfPassengersField.getText()));
                seatReservation.setFlyingWithInfant(flyingWithInfantCheckBox.isSelected());

                // Display seatReservation on the console
                displayInputValues(
                        seatReservation.getFlightDesignator(),
                        seatReservation.getFlightDate().toString(),
                        seatReservation.getFirstName(),
                        seatReservation.getLastName(),
                        String.valueOf(seatReservation.getNumberOfPassengers()),
                        seatReservation.isFlyingWithInfant());
                // Exit the application using Platform.exit()
                Platform.exit();
            } catch (IllegalArgumentException ex) {
                // Display error message on console
                System.out.println("Error: " + ex.getMessage());
            }
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
        // Convert String to LocalDate
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