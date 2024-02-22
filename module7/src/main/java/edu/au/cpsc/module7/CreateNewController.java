package edu.au.cpsc.module7;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateNewController {
    public Button doneButton;
    private TaskManagerController mainController;
    private TaskModel task;

    @FXML
    private TextField taskTextField;

    @FXML
    private ComboBox<TaskModel.State> statusComboBox;

    @FXML
    private TextArea descriptionTextArea;

    public void setMainController(TaskManagerController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void initialize() {
        statusComboBox.getItems().addAll(TaskModel.State.values());
        statusComboBox.setValue(TaskModel.State.TODO);
    }

    public void setTask(TaskModel task) {
        this.task = task;
        if (task != null) {
            taskTextField.setText(task.getSummary());
            statusComboBox.setValue(task.getState());
            descriptionTextArea.setText(task.getDescription());
        }
        else{
            doneButton.setText("Create");
        }
    }


    @FXML
    private void saveTask() {
        if (task == null) {
            // Creating a new task
            String summary = taskTextField.getText();
            TaskModel.State state = statusComboBox.getValue();
            String description = descriptionTextArea.getText();
            TaskModel newTask = new TaskModel(summary, state, description);
            mainController.addTask(newTask);
        } else {
            // Editing an existing task
            task.setSummary(taskTextField.getText());
            task.setState(statusComboBox.getValue());
            task.setDescription(descriptionTextArea.getText());
        }
        mainController.refreshListViews(task);
        closeWindow();
    }

    @FXML
    private void closeWindow() {
        taskTextField.getScene().getWindow().hide();
    }

}
