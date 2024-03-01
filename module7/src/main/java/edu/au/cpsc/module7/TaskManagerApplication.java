package edu.au.cpsc.module7;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;

import java.io.IOException;

public class TaskManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MenuBar menuBar = new MenuBar();
        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("task-manager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Task Manager!");
        stage.setScene(scene);
        stage.show();

        scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
                () {

            @Override
            public void handle(KeyEvent t) {
                if(t.getCode()== KeyCode.ESCAPE)
                {
                    Stage sb = (Stage)scene.getWindow();
                    sb.close();
                }
            }
        });
    }
    public static void openAddEditTaskWindow(TaskModel task, TaskManagerController controller) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(TaskManagerApplication.class.getResource("create-new-view.fxml"));
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

    public static void openShortCutsWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(TaskManagerApplication.class.getResource("shortcuts.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Shortcuts Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
        }
    }
    public static void main(String[] args) {
        launch();
    }
}