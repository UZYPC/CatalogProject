package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.SecureRandom;

public class Types {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private javafx.scene.control.ListView<String> typesListView;
    @FXML private javafx.scene.control.Button typeAddButton;
    @FXML private javafx.scene.control.Button goBack;
    @FXML private javafx.scene.control.TextField typeName;
    @FXML private javafx.scene.control.Button typeNameOK;

    public void cancel(){
        Platform.exit();
    }



    public void goBack() throws IOException {

        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.close();
    }

//    public void newTypeAdded(){
//        Stage stage = (Stage) typeAddButton.getScene().getWindow();
////        accordion.getExpandedPane().getContent().add;
//
//
//
//    }

    public void addType(){

        typeAddButton.setOnAction(e -> {
            try {
                addTypeButtonAction();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


    }

    public void addTypeButtonAction() throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("MainPage.fxml"));
        root= loader.load();
        Products products = loader.getController();
        products.addTypesIntoListView(typeName.getText());

        typeName.clear();

        stage= (Stage) typeAddButton.getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage stage1= (Stage) typeAddButton.getScene().getWindow();
        stage1.close();


    }



}
