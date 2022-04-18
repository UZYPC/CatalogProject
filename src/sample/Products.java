package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller;
import java.io.IOException;
import java.util.ArrayList;

public class Products {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML private javafx.scene.control.ListView<String> typesListView;
    @FXML private javafx.scene.control.ListView<String> itemsListView;
    @FXML private javafx.scene.control.Button itemAddButton;
    @FXML private javafx.scene.control.Accordion accordion;
    @FXML private javafx.scene.control.TextField typeName;
    @FXML private javafx.scene.control.TextField itemName;
    @FXML private javafx.scene.control.MenuItem newTypeMenuItem;
    @FXML private javafx.scene.control.MenuBar menuBar;
    @FXML private javafx.scene.control.ChoiceBox<String> choiceBox;
    @FXML private javafx.scene.control.MenuButton menuButton;


    public void cancel(){
        Platform.exit();
    }

    public void AddTypeScreen(ActionEvent event) throws IOException {
        ArrayList<Controller> items = new ArrayList<>();// on actionına akordiyona buton ekleme şeyi gelecek
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/yourcompanyname/yourapplicationname/layouts/nameOfYourFxmlFile.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("addtype.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Add your type.");
        stage.setScene(new Scene(root));
        stage.show();

    }




//    public void newTypeAdded(){
//        Stage stage = (Stage) typeAddButton.getScene().getWindow();
////        accordion.getExpandedPane().getContent().add;
//
//
//
//    }


    public void addItem(){

        itemAddButton.setOnAction(e->addItemButtonAction());
    }


    public void addItemButtonAction(){
        itemsListView.getItems().add(itemName.getText());
        itemName.clear();
    }

    public void addTypesIntoListView(String types){
        typesListView.getItems().add(types);

    }

    public void setChoiceBox(){
        choiceBox.getItems().addAll("Favourites","Missing","Borrowed");
    }

    public void selectType(){
    //    System.out.println(menuButton.getItems().get(0));
    }


}
