package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private TextField iadaCode;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField ident;
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
        System.out.println(ident.getText());
        Airport airport = airports.stream().filter(f -> f.getIdent().equals(ident.getText())).toList().getFirst();
        type.setText(airport.getType());
        name.setText((airport.getName()));
        elevation.setText(airport.getElevationFt().toString());
        country.setText(airport.getIsoCountry());
        region.setText(airport.getIsoRegion());
        municipality.setText(airport.getMunicipality());

    }
}