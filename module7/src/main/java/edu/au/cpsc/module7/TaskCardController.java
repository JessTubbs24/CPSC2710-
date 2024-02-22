package edu.au.cpsc.module7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskCardController {
    @FXML
    private Label summaryLabel;

    @FXML
    private Label stateLabel; // Newly added label for task state

    private TaskModel task;

    public void initialize() {
        updateUI();
    }

    public void setTask(TaskModel task) {
        this.task = task;
        updateUI();
    }

    private void updateUI() {
        if (task != null) {
            summaryLabel.setText(task.getSummary());
            stateLabel.setText(task.getState().toString()); // Set task state text
        }
    }
}