package edu.au.cpsc.module6;

import com.sun.javafx.charts.Legend;
import edu.au.cpsc.module6.AirlineDatabase;
import edu.au.cpsc.module6.AirlineDatabaseIO;
import edu.au.cpsc.module6.uimodel.FlightScheduleModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.io.*;
import java.time.LocalTime;
import java.util.HashSet;



public class FlightController {

    public ToggleButton mondayButton;
    public ToggleButton sundayButton;
    public ToggleButton wednesdayButton;
    public ToggleButton tuesdayButton;
    public ToggleButton fridayButton;
    public ToggleButton thursdayButton;
    public ToggleButton saturdayButton;
    public HBox daysOfTheWeek;
    @FXML
    private TextField flightDesignatorTextField, departureAirportIdentTextField, arrivalAirportIdentTextField;

    @FXML
    private TableView<ScheduledFlight> flightTableView;

    @FXML
    private TableColumn<ScheduledFlight, String>  flightDesignatorColumn, departureAirportIdentColumn, arrivalAirportIdentColumn, daysOfWeekColumn;
    private Legend.LegendItem arrivalTimeTextField;
    private AirlineDatabase adb;

    private FlightScheduleModel model;

    public void initialize() {
        model = new FlightScheduleModel();
        flightDesignatorTextField.textProperty().bindBidirectional(model.flightDesignatorProperty());
        departureAirportIdentTextField.textProperty().bindBidirectional(model.departureAirportIdentProperty());
        arrivalAirportIdentTextField.textProperty().bindBidirectional(model.arrivalAirportIdentProperty());

        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<>("daysOfWeek"));

        try {
            try (InputStream inputStream = new FileInputStream("airline.dat")) {
                this.adb = AirlineDatabaseIO.load(inputStream);

            }catch (Exception e){
                System.out.println(e);
                this.adb = AirlineDatabaseIO.load(null);
            }
            flightTableView.setItems(adb.getScheduledFlights());
           // sortedList.comparatorProperty().bind(flightTableView.comparatorProperty());
            flightTableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void tableSelectionChanged() {
        ScheduledFlight selectedScheduledFlight = flightTableView.getSelectionModel().getSelectedItem();
        if (selectedScheduledFlight == null) {
            flightDesignatorTextField.clear();
            departureAirportIdentTextField.clear();
            arrivalAirportIdentTextField.clear();
            return;
        }
        flightDesignatorTextField.setText(selectedScheduledFlight.getFlightDesignator());
        departureAirportIdentTextField.setText(selectedScheduledFlight.getDepartureAirportIdent());
        arrivalAirportIdentTextField.setText(selectedScheduledFlight.getArrivalAirportIdent());
        HashSet<String> set2 =selectedScheduledFlight.getDaysOfWeek();

        ObservableList<Node> buttons = daysOfTheWeek.getChildren();
        buttons.forEach((button) -> {
            ToggleButton tb = (ToggleButton) button;
            if(set2.contains(tb.getText())){
                tb.setSelected(true);
            }
            else{
                tb.setSelected(false);
            }
        });

    }

    @FXML
    protected void updateButtonAction() throws IOException, ClassNotFoundException {
        ScheduledFlight selectedScheduledFlight = flightTableView.getSelectionModel().getSelectedItem();
        if (selectedScheduledFlight != null) {
            // Update the selected flight with information from text fields
            selectedScheduledFlight.setFlightDesignator(flightDesignatorTextField.getText());
            selectedScheduledFlight.setDepartureAirportIdent(departureAirportIdentTextField.getText());
            selectedScheduledFlight.setArrivalAirportIdent(arrivalAirportIdentTextField.getText());
            HashSet<String> set2 = new HashSet<String> ();

            ObservableList<Node> buttons = daysOfTheWeek.getChildren();
            buttons.forEach((button) -> {
                ToggleButton tb = (ToggleButton) button;
                if(tb.isSelected()){
                    set2.add(tb.getText());
                }
            });
            selectedScheduledFlight.setDaysOfWeek(set2);// Optionally, update the table view to reflect the changes
            flightTableView.refresh();
            File generatedfile= new File("airline.dat");
            try (FileOutputStream fileOutStream = new FileOutputStream(generatedfile)) {
                AirlineDatabaseIO.save(adb, fileOutStream);

            }
        }
    }

    @FXML
    protected void deleteButtonAction() throws IOException, ClassNotFoundException {
        ScheduledFlight selectedScheduledFlight = flightTableView.getSelectionModel().getSelectedItem();
        if (selectedScheduledFlight != null) {
            // Remove the selected flight from the table view
            flightTableView.getItems().remove(selectedScheduledFlight);
            // Optionally, remove the selected flight from the data model as well
            File generatedfile= new File("airline.dat");
            try (FileOutputStream fileOutStream = new FileOutputStream(generatedfile)) {
                AirlineDatabaseIO.save(adb, fileOutStream);

            }
        }
    }

    @FXML
    protected void newButtonAction() throws IOException, ClassNotFoundException {
        HashSet<String> set2 = new HashSet<String>();
        ObservableList<Node> buttons = daysOfTheWeek.getChildren();
        buttons.forEach((button) -> {
            ToggleButton tb = (ToggleButton) button;
            if (tb.isSelected()) {
                set2.add(tb.getText());
            }
        });
        System.out.println(set2.toString());
        System.out.println(flightDesignatorTextField.getText());
        System.out.println(departureAirportIdentTextField.getText());
        System.out.println(arrivalAirportIdentTextField.getText());


        ScheduledFlight newFlight = new ScheduledFlight(
                flightDesignatorTextField.getText(),
                departureAirportIdentTextField.getText(),
                LocalTime.MIDNIGHT,
                arrivalAirportIdentTextField.getText(),
                LocalTime.NOON,
                set2
        );

        adb.addScheduledFlight(newFlight);
        System.out.println(adb.getScheduledFlights().size());
        File generatedfile = new File("airline.dat");
        try (FileOutputStream fileOutStream = new FileOutputStream(generatedfile)) {
            AirlineDatabaseIO.save(adb, fileOutStream);

        }

    }

}