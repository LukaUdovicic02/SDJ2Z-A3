package viewmodel;

import model.Model;

public class ViewModelFactory {
    private ChatViewModel chatViewModel;
    private LoginViewModel loginViewModel;
    private LogViewModel logViewModel;
    private RegisterViewModel registerViewModel;
    public ViewModelFactory(Model model){
        UserInformation userInformation = new UserInformation();
        this.chatViewModel = new ChatViewModel(model,userInformation);
        this.loginViewModel = new LoginViewModel(model,userInformation);
        this.logViewModel = new LogViewModel(model,userInformation);
        this.registerViewModel = new RegisterViewModel(model,userInformation);
    }

    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public LogViewModel getLogViewModel() {
        return logViewModel;
    }

    public RegisterViewModel getRegisterViewModel(){
        return registerViewModel;}
}
