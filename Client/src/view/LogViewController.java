package view;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class LogViewController extends ViewController {

    @FXML
    private ListView<String> logTextArea;
    @Override
    protected void init() {
        logTextArea.setItems(getViewModelFactory().getLogViewModel().getLogTextArea());
    }
    @Override public void reset(){
        getViewModelFactory().getLogViewModel().clear();
    }
    @FXML
    private void onChat(){
        getViewHandler().openView(View.CHAT);
    }
}
