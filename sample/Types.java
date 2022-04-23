

package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

import java.util.ArrayList;

public class Types {
    private String typeName;
    private String typeAttributesNames; // private ObservableList<String> typeAttributesNames;
    private ArrayList<Items> typesItems = new ArrayList<>();

    private ListView<String> typesItemsListView= new ListView<>();


    static ArrayList<Types> typesArrayList = new ArrayList<>();
    private ObservableList<Types> typeAttributesNamesList;


    @FXML
    private TitledPane typesTitledPane= new TitledPane();

    public ListView<String> getTypesItemsListView() {
        return typesItemsListView;
    }

    public void setTypesItemsListView(ListView<String> typesItemsListView) {
        this.typesItemsListView = typesItemsListView;
    }


    public TitledPane getTypesTitledPane() {
        return typesTitledPane;
    }

    public void setTypesTitledPane(TitledPane typesTitledPane) {
        this.typesTitledPane = typesTitledPane;
    }




    //    public  Types(String typeName,ObservableList<Types> typeAttributesNamesList){
//        this.typeName=typeName;
//        this.typeAttributesNamesList=typeAttributesNamesList;
//    }
    public  Types(String typeName,String typeAttributesNames){
        this.typeName=typeName;
        this.typeAttributesNames=typeAttributesNames;
    }
    public  Types(String typeAttributesNames){
        this.typeAttributesNames=typeAttributesNames;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeAttributesNames() {
        return typeAttributesNames;
    }

    public void setTypeAttributesNames(String typeAttributesNames) {
        this.typeAttributesNames = typeAttributesNames;
    }

    public ArrayList<Items> getTypesItems() {
        return typesItems;
    }

    public void setTypesItems(ArrayList<Items> typesItems) {
        this.typesItems = typesItems;
    }


    public ArrayList<Types> getTypesArrayList() {
        return typesArrayList;
    }

    public void setTypesArrayList(ArrayList<Types> typesArrayList) {
        this.typesArrayList = typesArrayList;
    }


    public ObservableList<Types> getTypeAttributesNamesList() {
        return typeAttributesNamesList;
    }

    public void setTypeAttributesNamesList(ObservableList<Types> typeAttributesNamesList) {
        this.typeAttributesNamesList = typeAttributesNamesList;
    }
}