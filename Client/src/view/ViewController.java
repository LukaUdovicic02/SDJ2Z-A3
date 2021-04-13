package view;

import viewmodel.ViewModelFactory;
import javafx.scene.layout.Region;

public abstract class ViewController {
    private Region root;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    protected abstract void init() throws Exception;

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root){
        this.root = root;
        this.viewModelFactory = viewModelFactory;
        this.viewHandler = viewHandler;
        //listener?
    }
    public void reset(){

    }
    public Region getRoot(){
        return root;
    }
    public ViewModelFactory getViewModelFactory(){
        return viewModelFactory;
    }
    public ViewHandler getViewHandler(){
        return viewHandler;
    }

}
