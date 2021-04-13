package view;

import viewmodel.SimpleMessagesViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ChatViewController extends ViewController {
    @FXML private TableColumn<SimpleMessagesViewModel, String> name;
    @FXML private TableColumn<SimpleMessagesViewModel, String> message;
    @FXML private TableView<SimpleMessagesViewModel> chatTextArea;
    @FXML private TextField textToBeSent;

    @Override protected void init() {
        name.setCellValueFactory(cellData -> cellData.getValue().getUsr());
        message.setCellValueFactory(cellData -> cellData.getValue().getMsg());
        chatTextArea.setItems(getViewModelFactory().getChatViewModel().getList());
        textToBeSent.textProperty().bindBidirectional(getViewModelFactory().getChatViewModel().getMsg());
    }

    @Override public void reset(){
        getViewModelFactory().getChatViewModel().clear();
    }

    @FXML
    private void onLog(){
        getViewHandler().openView(View.LOG);
    }

    @FXML
    private void onLogout(){
        getViewHandler().openView(View.LOGIN);
    }

    @FXML
    private void onSend(ActionEvent actionEvent) {
        if (textToBeSent.textProperty().getValue() != null){
            try {
                getViewModelFactory().getChatViewModel().addMessage();
            } catch (Exception e) {
//            error.setValue(e.getMessage()):
            }
        }
    }
}
