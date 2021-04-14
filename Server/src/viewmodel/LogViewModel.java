package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class LogViewModel implements LocalListener<String, Message> {
    private Model model;
    private ObservableList<String> logText;

    public LogViewModel(Model model) {
        this.model = model;
        this.model.addListener(this,"Log");
        logText = FXCollections.observableArrayList();
    }

    public ObservableList<String> getLogTextArea() {
        return logText;
    }

    public void clear() {
        //
    }

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        Platform.runLater(() -> {
            logText.add(event.getValue1());
        });
    }
}
