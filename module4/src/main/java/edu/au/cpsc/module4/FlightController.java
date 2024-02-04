package edu.au.cpsc.module4;

import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FlightController {

    @FXML
    private TextField flightDesignatorTextField, departureAirportIdentTextField, arrivalAirportIdentTextField;

    @FXML
    private TableView<ScheduledFlight> flightTableView;

    @FXML
    private TableColumn<ScheduledFlight, String>  flightDesignatorColumn, departureAirportIdentColumn, arrivalAirportIdentColumn, daysOfWeekColumn;

    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalAirportIdentColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<>("daysOfWeek"));

        try {
            //InputStream inputStream = new FileInputStream("file.dat");
            AirlineDatabase adb = AirlineDatabaseIO.load(null);
            SortedList<ScheduledFlight> sortedList = new SortedList<> (
                    FXCollections.observableList(adb.getScheduledFlights()));
            flightTableView.setItems(sortedList);
            sortedList.comparatorProperty().bind(flightTableView.comparatorProperty());
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
    }

    public void onStoreButtonClick(ActionEvent actionEvent) {
    }
}