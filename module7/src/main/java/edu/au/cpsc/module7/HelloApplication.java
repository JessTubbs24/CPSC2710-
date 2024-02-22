package edu.au.cpsc.module7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Task Manager!");
        stage.setScene(scene);
        stage.show();
    }
    public static void openAddEditTaskWindow(TaskModel task, HelloController controller) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("create-new-view.fxml"));
            Parent root = loader.load();
            CreateNewController addEditTaskController = loader.getController();
            addEditTaskController.setMainController(controller);
            addEditTaskController.setTask(task);
            stage.setScene(new Scene(root));
            stage.setTitle(task == null ? "Add Task" : "Edit Task");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}