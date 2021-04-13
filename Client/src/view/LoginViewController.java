package view;

import viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController extends ViewController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    private LoginViewModel viewModel;

    @Override protected void init() throws Exception
    {
        viewModel = super.getViewModelFactory().getLoginViewModel();
        usernameField.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getUsername());
        passwordField.textProperty().bindBidirectional(getViewModelFactory().getLoginViewModel().getPassword());
        errorLabel.textProperty().bind(getViewModelFactory().getLoginViewModel().getError());
    }

    @Override public void reset(){
        getViewModelFactory().getLoginViewModel().clear();
    }

    @FXML
    private void onLogin() throws Exception
    {
        if(viewModel.logIn())
        {
            getViewHandler().openView(View.CHAT);
        }
    }

    @FXML
    private void onRegister(ActionEvent actionEvent)
    {
        getViewHandler().openView(View.REGISTER);
    }
}
