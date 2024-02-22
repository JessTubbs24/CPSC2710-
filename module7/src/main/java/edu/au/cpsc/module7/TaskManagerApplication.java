package edu.au.cpsc.module7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("task-manager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Task Manager!");
        stage.setScene(scene);
        stage.show();
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
    public static void main(String[] args) {
        launch();
    }
}