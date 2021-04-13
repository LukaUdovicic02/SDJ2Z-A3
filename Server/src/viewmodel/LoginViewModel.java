package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel {
    private Model model;
    private StringProperty username, password, error;
    private UserInformation user;

    public LoginViewModel(Model model, UserInformation userInformation){
        this.model = model;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        error = new SimpleStringProperty();
        user = userInformation;
    }

    public void clear(){
        username.setValue(null);
        password.setValue(null);
        error.setValue(null);
    }

    public boolean logIn(){
        boolean result = false;
        try{
            result = model.login(username.get(), password.get());
            user.setUser(username.get());
        }
        catch (Exception e){
            error.setValue(e.getMessage());
        }
        return result;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public StringProperty getPassword()
    {
        return password;
    }

    public StringProperty getError(){return error;}
}
