package edu.au.cpsc.module6;

import edu.au.cpsc.module6.AirlineDatabase;
import edu.au.cpsc.module6.AirlineDatabaseIO;
import edu.au.cpsc.module6.uimodel.FlightScheduleModel;
import javafx.collections.FXCollections;
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

    public ToggleButton mondayButton, sundayButton, wednesdayButton, tuesdayButton, fridayButton, thursdayButton, saturdayButton;
    public HBox daysOfTheWeek;
    public Button updateButton, newButton, deleteButton;
    @FXML
    private TextField flightDesignatorTextField, departureAirportIdentTextField, arrivalAirportIdentTextField;
    @FXML
    private TableView<ScheduledFlight> flightTableView;
    @FXML
    private TableColumn<ScheduledFlight, String>  flightDesignatorColumn, departureAirportIdentColumn, arrivalAirportIdentColumn, daysOfWeekColumn;
    private AirlineDatabase adb;
    private FlightScheduleModel model;
    public void initialize() throws IOException, ClassNotFoundException {

        model = new FlightScheduleModel();
        flightDesignatorTextField.textProperty().bindBidirectional(model.flightDesignatorProperty());
        departureAirportIdentTextField.textProperty().bindBidirectional(model.departureAirportIdentProperty());
        arrivalAirportIdentTextField.textProperty().bindBidirectional(model.arrivalAirportIdentProperty());
        bindButtonEnable();

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

    private void bindVisualEnable() {
        if(!model.fdValidProperty().getValue()){
            flightDesignatorTextField.styleProperty().bind(model.fdValidProperty().asString().map(s -> "-fx-border-color: red;"));
        }
        else{
            flightDesignatorTextField.styleProperty().bind(model.fdValidProperty().asString().map(s -> "-fx-border-color: green;"));
        }
        if(!model.daiValidProperty().getValue()){
            departureAirportIdentTextField.styleProperty().bind(model.daiValidProperty().asString().map(s -> "-fx-border-color: red;"));
        }
        else{
            departureAirportIdentTextField.styleProperty().bind(model.daiValidProperty().asString().map(s -> "-fx-border-color: green;"));
        }
        System.out.println("value is: " + model.aaiValidProperty().getValue());
        if(!model.aaiValidProperty().getValue()){
            arrivalAirportIdentTextField.styleProperty().bind(model.aaiValidProperty().asString().map(s -> "-fx-border-color: red;"));
        }
        else{
            arrivalAirportIdentTextField.styleProperty().bind(model.aaiValidProperty().asString().map(s -> "-fx-border-color: green;"));
        }


    }

    private void bindButtonEnable() {
        daysOfTheWeek.accessibleTextProperty().bindBidirectional(model.daysOfWeekProperty());
        updateButton.disableProperty().bind(getModel().modifiedProperty().not());
        newButton.disableProperty().bind(getModel().newProperty());
        deleteButton.disableProperty().bind(getModel().modifiedProperty().or(getModel().newProperty()));
    }

    public FlightScheduleModel getModel() { return model; }

    private void tableSelectionChanged() {
        ScheduledFlight selectedScheduledFlight = flightTableView.getSelectionModel().getSelectedItem();
        if (selectedScheduledFlight == null) {
            model.setFlightDesignator("");
            model.setDepartureAirportIdent("");
            model.setArrivalAirportIdent("");
            model.setModified(false);
            model.setNew(true);
            return;
        }
        model.setFlightDesignator(selectedScheduledFlight.getFlightDesignator());
        model.setDepartureAirportIdent(selectedScheduledFlight.getDepartureAirportIdent());
        model.setArrivalAirportIdent(selectedScheduledFlight.getArrivalAirportIdent());
        HashSet<String> set2 = selectedScheduledFlight.getDaysOfWeek();
        model.setModified(false);
        model.setNew(false);

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

        if (!model.isValid()) {
            bindVisualEnable();

            return;
        }
        bindVisualEnable();

        ScheduledFlight selectedScheduledFlight = flightTableView.getSelectionModel().getSelectedItem();
        if (selectedScheduledFlight != null) {
            // Update the selected flight with information from text fields
            selectedScheduledFlight.setFlightDesignator(model.getFlightDesignator());
            selectedScheduledFlight.setDepartureAirportIdent(model.getDepartureAirportIdent());
            selectedScheduledFlight.setArrivalAirportIdent(model.getArrivalAirportIdent());
            HashSet<String> set2 = new HashSet<String> ();

            ObservableList<Node> buttons = daysOfTheWeek.getChildren();
            buttons.forEach((button) -> {
                ToggleButton tb = (ToggleButton) button;
                if(tb.isSelected()){
                    set2.add(tb.getText());
                }
            });
            selectedScheduledFlight.setDaysOfWeek(set2);
            flightTableView.refresh();
            File generatedfile= new File("airline.dat");
            try (FileOutputStream fileOutStream = new FileOutputStream(generatedfile)) {
                AirlineDatabaseIO.save(adb, fileOutStream);

            }
        } else {
            bindVisualEnable();
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
        } else {
            bindVisualEnable();
        }
    }

    @FXML
    protected void newButtonAction() throws IOException, ClassNotFoundException {
        if (!model.isValid()) {
            bindVisualEnable();
            return;
        }
        bindVisualEnable();
        HashSet<String> set2 = new HashSet<String>();
            ObservableList<Node> buttons = daysOfTheWeek.getChildren();
            buttons.forEach((button) -> {
                ToggleButton tb = (ToggleButton) button;
                if (tb.isSelected()) {
                    set2.add(tb.getText());
                }
            });
            ScheduledFlight newFlight = new ScheduledFlight(
                    model.getFlightDesignator(),
                    model.getDepartureAirportIdent(),
                    LocalTime.MIDNIGHT,
                    model.getArrivalAirportIdent(),
                    LocalTime.NOON,
                    set2
            );

            adb.addScheduledFlight(newFlight);
            System.out.println(adb.getScheduledFlights().size());
            File generatedfile = new File("airline.dat");
            try (FileOutputStream fileOutStream = new FileOutputStream(generatedfile)) {
                AirlineDatabaseIO.save(adb, fileOutStream);

            }
            flightTableView.getSelectionModel().selectLast();
        flightTableView.scrollTo(flightTableView.getSelectionModel().getSelectedItem());
        //} else {
       // }
    }

}