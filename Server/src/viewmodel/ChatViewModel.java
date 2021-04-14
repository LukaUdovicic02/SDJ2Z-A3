package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

public class ChatViewModel implements LocalListener<String, Message> {
    private Model model;
    private ObservableList<SimpleMessagesViewModel> list;
    private StringProperty usr, msg;
    private UserInformation userInformation;

    public ChatViewModel(Model model,UserInformation userInformation) {
        this.model = model;
        model.addListener(this,"Message");
        this.usr = new SimpleStringProperty();
        this.msg = new SimpleStringProperty();
        list = FXCollections.observableArrayList();
        this.userInformation = userInformation;
    }

    public void clear() {
        msg.setValue(null);
    }

    public StringProperty getMsg() {
        return msg;
    }

    public StringProperty getUsr() {
        return usr;
    }

    public ObservableList<SimpleMessagesViewModel> getList() {
        return list;
    }

    public void SendMessage(Message message) {
        list.add(new SimpleMessagesViewModel(message));
    }

    public void addMessage()
    {
        model.addMessage(createMessageObject(),"");
        msg.setValue(null);
    }

    public Message createMessageObject() {
        try {
            Message m = new Message(userInformation.getUser(), getMsg().get());
            return m;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void propertyChange(ObserverEvent<String, Message> event) {
        Platform.runLater(() ->
        {
//            if (event.getPropertyName().equals("Message")) {
//                SendMessage(event.getValue2());
//            }
            SendMessage(event.getValue2());
        });
    }
}
