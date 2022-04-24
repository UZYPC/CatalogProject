package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

import java.util.ArrayList;

public class Types {
    private String typeName;
    private String typeAttributesNames;
    private ArrayList<Items> typesItems = new ArrayList<>();
    private ObservableList<Types> typeAttributesNamesList = FXCollections.observableArrayList();
    private ListView<String> typesItemsListView = new ListView<>();
    private int typeAttrNameCount;



    static ArrayList<Types> typesArrayList = new ArrayList<>();
    private ArrayList<String> typeAttrNameArrayList= new ArrayList<>();

    public Types(String typeName, ArrayList<String> typeAttrNameArrayList) {
        this.typeName = typeName;
        this.typeAttrNameArrayList = typeAttrNameArrayList;
    }

    @FXML
    private TitledPane typesTitledPane = new TitledPane();

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
    public Types(String typeName, String typeAttributesNames) {
        this.typeName = typeName;
        this.typeAttributesNames = typeAttributesNames;
    }

    public Types(String typeAttributesNames) {
        this.typeAttributesNames = typeAttributesNames;
    }

    public Types(String typeName, ObservableList<Types> typeAttributesNamesList) {
        this.typeName = typeName;
        this.typeAttributesNamesList = typeAttributesNamesList;
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

    public ArrayList<String> getTypeAttrNameArrayList() {
        return typeAttrNameArrayList;
    }

    public void setTypeAttrNameArrayList(ArrayList<String> typeAttrNameArrayList) {
        this.typeAttrNameArrayList = typeAttrNameArrayList;
    }

    public int getTypeAttrNameCount() {
        return typeAttrNameCount;
    }

    public void setTypeAttrNameCount(int typeAttrNameCount) {
        this.typeAttrNameCount = typeAttrNameCount;
    }
}





