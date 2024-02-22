package edu.au.cpsc.module7;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TaskModel {
    private SimpleStringProperty summary;
    private SimpleObjectProperty<State> state;
    private SimpleStringProperty description;

    public TaskModel(String summary, State state, String description) {
        this.summary = new SimpleStringProperty(summary);
        this.state = new SimpleObjectProperty<>(state);
        this.description = new SimpleStringProperty(description);
    }

    public String getSummary() {
        return summary.get();
    }

    public SimpleStringProperty summaryProperty() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary.set(summary);
    }

    public State getState() {
        return state.get();
    }

    public SimpleObjectProperty<State> stateProperty() {
        return state;
    }

    public void setState(State state) {
        this.state.set(state);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public enum State {
        TODO, DOING, DONE
    }
}

