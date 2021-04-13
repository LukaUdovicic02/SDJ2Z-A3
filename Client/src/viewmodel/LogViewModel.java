package viewmodel;

import model.Message;
import model.Model;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogViewModel implements PropertyChangeListener {
    private Model model;
    private ObservableList<String> logText;
    private UserInformation userInformation;

    public LogViewModel(Model model, UserInformation userInformation){
        this.model = model;
        this.model.addListener(this);
        this.userInformation = userInformation;
        logText = FXCollections.observableArrayList();
    }

    public ObservableList<String> getLogTextArea() {
        return logText;
    }

    public void clear(){

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("Log")) {
                logText.add((String)evt.getNewValue());
            }
            else if (evt.getPropertyName().equals("Message") && !((Message)evt.getNewValue()).getUsr().equals(userInformation.getUser())){
                model.addLog(((Message) evt.getNewValue()).toString());
            }
        });
    }
}
