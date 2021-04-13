package view;

import viewmodel.RegisterViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterViewController extends ViewController
{
  @FXML private TextField usernameField;
  @FXML private PasswordField passwordField;
  @FXML private Label errorLabel;
  private RegisterViewModel viewModel;

  @Override protected void init() throws Exception
  {
    viewModel = getViewModelFactory().getRegisterViewModel();
    usernameField.textProperty().bindBidirectional(viewModel.getUsername());
    passwordField.textProperty().bindBidirectional(viewModel.getPassword());
    errorLabel.textProperty().bind(viewModel.getError());
  }

  @Override public void reset(){
    getViewModelFactory().getRegisterViewModel().clear();
  }

  @FXML
  private void onBack(ActionEvent actionEvent)
  {
    getViewHandler().openView(View.LOGIN);
  }

  @FXML
  private void onRegister(ActionEvent actionEvent) throws Exception
  {
    if (viewModel.register())
    {
      getViewHandler().openView(View.LOGIN);
    }
  }
}
