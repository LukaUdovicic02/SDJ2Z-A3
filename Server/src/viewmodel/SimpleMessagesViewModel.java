package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Message;

public class SimpleMessagesViewModel {
    private StringProperty usr;
    private StringProperty msg;

    public SimpleMessagesViewModel(Message message)
    {
        usr = new SimpleStringProperty(message.getUsr());
        msg = new SimpleStringProperty(message.getMsg());
    }


    public StringProperty getUsr()
    {
        return usr;
    }

    public StringProperty getMsg()
    {
        return msg;
    }

}
