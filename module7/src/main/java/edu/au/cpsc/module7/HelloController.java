package edu.au.cpsc.module7;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<TaskModel> tableView;
    @FXML
    private TableColumn<TaskModel, String> summaryColumn, descriptionColumn;
    @FXML
    private TableColumn<TaskModel, TaskModel.State> stateColumn;
    private ObservableList<TaskModel> tasks = FXCollections.observableArrayList();

    public void initialize() {
        summaryColumn.setCellValueFactory(cellData -> cellData.getValue().summaryProperty());

        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());

        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

       // tableView.getColumns().addAll(summaryColumn, stateColumn, descriptionColumn);
        tableView.setItems(tasks);
        tableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                TaskModel selectedTask = tableView.getSelectionModel().getSelectedItem();
                if (selectedTask != null) {
                    editTask();
                }
            }
        });
    }

    public void addTask(TaskModel task) {
        tasks.add(task);
    }
    public void createTask(ActionEvent actionEvent) {
        HelloApplication.openAddEditTaskWindow(null, this);
    }

    public void deleteTask(ActionEvent actionEvent) {
        TaskModel selectedTask = tableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Task");
            alert.setContentText("Are you sure you want to delete the selected task?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    tasks.remove(selectedTask);
                }
            });
        } else {
            showAlert("Error", "No task selected for deletion.", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void editTaskButtonPress(ActionEvent actionEvent) {
        editTask();
    }
    private void editTask(){
        TaskModel selectedTask = tableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            // Open edit task window
            HelloApplication.openAddEditTaskWindow(selectedTask, this);
        } else {
            showAlert("Error", "No task selected for editing.", Alert.AlertType.ERROR);
        }
    }
}