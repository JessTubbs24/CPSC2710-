package edu.au.cpsc.module7;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagerController {
    @FXML
    private ListView<TaskModel> todoListView;

    @FXML
    private ListView<TaskModel> doingListView;

    @FXML
    private ListView<TaskModel> doneListView;
    private ObservableList<TaskModel> todoTasks = FXCollections.observableArrayList();
    private ObservableList<TaskModel> doingTasks = FXCollections.observableArrayList();
    private ObservableList<TaskModel> doneTasks = FXCollections.observableArrayList();
    private TaskModel selectedTask;
    public void initialize() {

        initializeListView(todoListView);
        initializeListView(doingListView);
        initializeListView(doneListView);
        // Add sample tasks
        // Bind observable lists to list views
        todoListView.setItems(todoTasks);
        doingListView.setItems(doingTasks);
        doneListView.setItems(doneTasks);
        // Make the ListView not focusable
        todoListView.setFocusTraversable(false);
        doingListView.setFocusTraversable(false);
        doneListView.setFocusTraversable(false);
        // Add drag-and-drop functionality
        // Add drag-and-drop functionality to list views
        addDragAndDropFunctionality(todoListView);
        addDragAndDropFunctionality(doingListView);
        addDragAndDropFunctionality(doneListView);
        // Listen for selection changes in all list views
        todoListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedTask = newValue;
        });
        doingListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedTask = newValue;
        });
        doneListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedTask = newValue;
        });
    }
    private void initializeListView(ListView<TaskModel> listView) {
        listView.setCellFactory(param -> createTaskCardCellFactory(listView));
    }
    public void setTasks(ObservableList<TaskModel> tasks) {

        todoTasks.clear();
        doingTasks.clear();
        doneTasks.clear();


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

                    break;
            }
    }
    public void createTask(ActionEvent actionEvent) {
        TaskManagerApplication.openAddEditTaskWindow(null, this);
    }

    public void deleteTask(ActionEvent actionEvent) {
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

                            break;
                    }
                }
            });
            refreshListViews(selectedTask);
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
        if (selectedTask != null) {

            TaskManagerApplication.openAddEditTaskWindow(selectedTask, this);

        } else {
            showAlert("Error", "No task selected for editing.", Alert.AlertType.ERROR);
        }
    }

    private ObservableList<TaskModel> getListByState(TaskModel.State state) {
        switch (state) {
            case TODO:
                return todoTasks;
            case DOING:
                return doingTasks;
            case DONE:
                return doneTasks;
            default:
                return null;
        }
    }
    public void refreshListViews(TaskModel editedTask) {

        if (editedTask != null) {

            ObservableList<TaskModel> originalList = null;
            if (todoTasks.contains(editedTask)) {
                originalList = todoTasks;
            } else if (doingTasks.contains(editedTask)) {
                originalList = doingTasks;
            } else if (doneTasks.contains(editedTask)) {
                originalList = doneTasks;
            }


            if (originalList != null && originalList != getListByState(editedTask.getState())) {
                originalList.remove(editedTask);

                getListByState(editedTask.getState()).add(editedTask);
            }


            todoListView.refresh();
            doingListView.refresh();
            doneListView.refresh();
        }
    }
    private TaskModel draggedTask;

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
                    // Select the dropped item in the ListView
                    listView.getSelectionModel().select(draggedTask);
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
                return null;
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
                        e.printStackTrace();
                    }
                    setGraphic(card);
                }
            }
        };
    }

    public void showShortcuts(ActionEvent actionEvent) {
        TaskManagerApplication.openShortCutsWindow();
    }
}