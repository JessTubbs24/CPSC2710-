package edu.au.cpsc.module6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class FlightScheduleApplication extends Application {
    Scene scene;
    private javafx.stage.Screen Screen;
    int screenWidth = (int) javafx.stage.Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) javafx.stage.Screen.getPrimary().getBounds().getHeight();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FlightScheduleApplication.class.getResource("flight-table-view.fxml"));
        // Responsive Design
        int sceneWidth = 0;
        int sceneHeight = 0;
        if (screenWidth <= 800 && screenHeight <= 600) {
            sceneWidth = 600;
            sceneHeight = 350;
        } else if (screenWidth <= 1280 && screenHeight <= 768) {
            sceneWidth = 800;
            sceneHeight = 450;
        } else if (screenWidth <= 1920 && screenHeight <= 1080) {
            sceneWidth = 1000;
            sceneHeight = 650;
        }


        scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
        stage.setTitle("Flight Designator");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}