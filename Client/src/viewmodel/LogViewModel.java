package viewmodel;

import model.Message;
import model.Model;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class LogViewModel implements LocalListener<String, Message> {
    private Model model;
    private ObservableList<String> logText;
    private UserInformation userInformation;

    public LogViewModel(Model model, UserInformation userInformation){
        this.model = model;
        this.model.addListener(this,"Log","Message");
        this.userInformation = userInformation;
        logText = FXCollections.observableArrayList();
    }

    public ObservableList<String> getLogTextArea() {
        return logText;
    }

    public void clear(){

    }

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        Platform.runLater(() -> {
            if (event.getPropertyName().equals("Log")) {
                logText.add(event.getValue1());
            }
        });
    }
}
