package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.SimpleMessagesViewModel;


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
            getViewModelFactory().getChatViewModel().addMessage();
        }
    }
}