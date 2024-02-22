package edu.au.cpsc.module7;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloController {
    @FXML
    private ListView<TaskModel> todoListView;

    @FXML
    private ListView<TaskModel> doingListView;

    @FXML
    private ListView<TaskModel> doneListView;
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<TaskModel> tableView;
    @FXML
    private TableColumn<TaskModel, String> summaryColumn, descriptionColumn;
    @FXML
    private TableColumn<TaskModel, TaskModel.State> stateColumn;
    private ObservableList<TaskModel> todoTasks = FXCollections.observableArrayList();
    private ObservableList<TaskModel> doingTasks = FXCollections.observableArrayList();
    private ObservableList<TaskModel> doneTasks = FXCollections.observableArrayList();
    public void initialize() {

        initializeListView(todoListView);
        initializeListView(doingListView);
        initializeListView(doneListView);
        // Add sample tasks
        // Bind observable lists to list views
        todoListView.setItems(todoTasks);
        doingListView.setItems(doingTasks);
        doneListView.setItems(doneTasks);
        // Add drag-and-drop functionality
        // Add drag-and-drop functionality to list views
        addDragAndDropFunctionality(todoListView);
        addDragAndDropFunctionality(doingListView);
        addDragAndDropFunctionality(doneListView);
    }
    private void initializeListView(ListView<TaskModel> listView) {
        listView.setCellFactory(param -> createTaskCardCellFactory(listView));
    }
    public void setTasks(ObservableList<TaskModel> tasks) {
        // Clear existing tasks
        todoTasks.clear();
        doingTasks.clear();
        doneTasks.clear();

        // Add tasks to appropriate lists based on their state
        for (TaskModel task : tasks) {
            switch (task.getState()) {
                case TODO:
                    todoTasks.add(task);
                    break;
                case DOING:
                    doingTasks.add(task);
                    break;
                case DONE:
                    doneTasks.add(task);
                    break;
            }
        }

        // Listen for changes in tasks and update lists accordingly
        tasks.addListener((ListChangeListener<TaskModel>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().forEach(task -> {
                        switch (task.getState()) {
                            case TODO:
                                todoTasks.add(task);
                                break;
                            case DOING:
                                doingTasks.add(task);
                                break;
                            case DONE:
                                doneTasks.add(task);
                                break;
                        }
                    });
                }
                if (c.wasRemoved()) {
                    c.getRemoved().forEach(task -> {
                        todoTasks.remove(task);
                        doingTasks.remove(task);
                        doneTasks.remove(task);
                    });
                }
            }
        });
        }
    public void addTask(TaskModel task) {
            switch (task.getState()) {
                case TODO:
                    todoTasks.add(task);
                    break;
                case DOING:
                    doingTasks.add(task);
                    break;
                case DONE:
                    doneTasks.add(task);
                    break;
                default:
                    // Handle unknown state or provide default behavior
                    break;
            }
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
                    switch (selectedTask.getState()) {
                        case TODO:
                            todoTasks.remove(selectedTask);
                            break;
                        case DOING:
                            doingTasks.remove(selectedTask);
                            break;
                        case DONE:
                            doneTasks.remove(selectedTask);
                            break;
                        default:
                            // Handle unknown state or provide default behavior
                            break;
                    }
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
    private TaskModel draggedTask; // Class variable to store the dragged task

    private void addDragAndDropFunctionality(ListView<TaskModel> listView) {
        listView.setOnDragDetected(event -> {
            if (listView.getSelectionModel().getSelectedItem() != null) {
                draggedTask = listView.getSelectionModel().getSelectedItem();
                Dragboard dragboard = listView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(Integer.toString(listView.getSelectionModel().getSelectedIndex()));
                dragboard.setContent(content);
                event.consume();
            }
        });

        listView.setOnDragOver(event -> {
            if (event.getGestureSource() != listView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        listView.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString() && draggedTask != null) {
                // Determine the source list based on the current state of the dragged task
                ObservableList<TaskModel> sourceList = getSourceListForTask(draggedTask);
                if (sourceList != null) {
                    sourceList.remove(draggedTask); // Remove the dragged task from the source list
                    draggedTask.setState(getStateForListView(listView)); // Update the state based on the destination list
                    listView.getItems().add(draggedTask); // Add the dragged task to the destination list
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
    private ObservableList<TaskModel> getSourceListForTask(TaskModel task) {
        switch (task.getState()) {
            case TODO:
                return todoTasks;
            case DOING:
                return doingTasks;
            case DONE:
                return doneTasks;
            default:
                return null; // Handle unknown state or provide default behavior
        }
    }
    private TaskModel.State getStateForListView(ListView<TaskModel> listView) {
        if (listView == todoListView) {
            return TaskModel.State.TODO;
        } else if (listView == doingListView) {
            return TaskModel.State.DOING;
        } else if (listView == doneListView) {
            return TaskModel.State.DONE;
        } else {
            // Handle unknown state or provide default behavior
            return null;
        }
    }
    private ListCell<TaskModel> createTaskCardCellFactory(ListView<TaskModel> listView) {
        return new ListCell<TaskModel>() {
            private final VBox card;

            {
                card = new VBox();
                card.getStyleClass().add("task-card");
            }

            @Override
            protected void updateItem(TaskModel task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setGraphic(null);
                } else {
                    // Load the FXML for the task card
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("taskCard.fxml"));
                    try {
                        card.getChildren().clear();
                        card.getChildren().add(loader.load());
                        TaskCardController controller = loader.getController();
                        controller.setTask(task);
                    } catch (IOException e) {
                        e.printStackTrace(); // Handle the exception appropriately
                    }
                    setGraphic(card);
                }
            }
        };
    }
}