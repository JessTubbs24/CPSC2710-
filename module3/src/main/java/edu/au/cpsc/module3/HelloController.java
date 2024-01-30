package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class HelloController {

    private List<Airport> airports;
    @FXML
    private TextField type;
    @FXML
    private TextField municipality;
    @FXML
    private TextField region;
    @FXML
    private TextField elevation;
    @FXML
    private TextField name;
    @FXML
    private TextField country;
    @FXML
    private TextField localCode;
    @FXML
    private TextField iataCode;
    @FXML
    private TextField ident;
    @FXML
    private WebView weatherWebView;
    @FXML
    public void initialize(){
        try {
            airports = Airport.readAll();
        }catch(Exception e){
            System.out.println(("failed to read airports csv" + e.toString()));
        }
    }
    @FXML
    protected void onHelloButtonClick() {
        String searchText = getFirstNonEmptyField();

        if (searchText != null) {
            Airport airport = airports.stream()
                    .filter(f -> f.getIdent().equals(searchText)
                            || f.getIataCode().equals(searchText)
                            || f.getLocalCode().equals(searchText))
                    .findFirst().orElse(null);

            if (airport != null) {
                updateFields(airport);
                updateWeatherWebView(airport.getLatitude(), airport.getLongitude());
            }
        }
    }

    // Event handler for the Enter key press on ident TextField
    @FXML
    protected void onIdentEnterPressed() {
        searchAndUpdateFields(ident.getText());
    }

    // Event handler for the Enter key press on iataCode TextField
    @FXML
    protected void onIataCodeEnterPressed() {
        searchAndUpdateFields(iataCode.getText());
    }

    // Event handler for the Enter key press on localCode TextField
    @FXML
    protected void onLocalCodeEnterPressed() {
        searchAndUpdateFields(localCode.getText());
    }

    private void searchAndUpdateFields(String searchText) {
        Airport airport = airports.stream().filter(f -> f.getIdent().equals(searchText)
                || f.getIataCode().equals(searchText)
                || f.getLocalCode().equals(searchText)).findFirst().orElse(null);

        if (airport != null) {
            updateFields(airport);
            updateWeatherWebView(airport.getLatitude(), airport.getLongitude());
        }
    }

    private void updateFields(Airport airport) {
        localCode.setText(airport.getLocalCode());
        iataCode.setText(airport.getIataCode());
        ident.setText(airport.getIdent());
        type.setText(airport.getType());
        name.setText(airport.getName());
        elevation.setText(airport.getElevationFt().toString());
        country.setText(airport.getIsoCountry());
        region.setText(airport.getIsoRegion());
        municipality.setText(airport.getMunicipality());

    }

    private String getFirstNonEmptyField() {
        if (!ident.getText().isEmpty()) {
            return ident.getText();
        } else if (!iataCode.getText().isEmpty()) {
            return iataCode.getText();
        } else if (!localCode.getText().isEmpty()) {
            return localCode.getText();
        } else {
            return null; // No non-empty field found
        }
    }

    @FXML
    private void updateWeatherWebView(BigDecimal latitude, BigDecimal longitude) {
        WebEngine webEngine = weatherWebView.getEngine();
        String url = "https://www.windy.com/?" + latitude + "," + longitude + ",12";
        webEngine.load(url);
        System.out.println(url + " was generated.");
    }

}