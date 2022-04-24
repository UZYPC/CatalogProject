package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TabPaneDeneme implements Initializable {
    @FXML
    private TabPane tabPane;

    /**Main Page Components**/
    @FXML
    private Tab mainTab;


    /**Item Page Components**/
    @FXML
    private Tab itemTab;
    @FXML
    private TableView<Items> itemsAttrTableView;//kullanılmıyacak
    @FXML
    private TableColumn<Items, String> itemAttrValue;//kullanılmıyacak
    @FXML
    private TableColumn<Types,String> typeAttrNameColumn;//kullanılmıyacak
    @FXML
    private TextField enterItemsAttrValue;
    @FXML
    private TextField itemName;
    @FXML
    private ChoiceBox<String> tagNameChoiceBox;
    @FXML
    private ChoiceBox<String> typeNameChoiceBox;
    @FXML
    private VBox typeTitledPaneVbox;
    @FXML
    private VBox tagTitledPaneVbox;
    @FXML
    ListView<String> showTypeAttrValuesInItemPage= new ListView<>();
    @FXML
    ListView<String> itemsAttrValuesListView= new ListView<>();
    private int attrValueCount;

    /**Type Page Components**/
    @FXML
    private ComboBox<String> typeNameComboBox;
    @FXML
    private Tab typeTab;
    @FXML
    private TextField typesTextField;
    @FXML
    private TextField attrNamesEntryTextField;
    @FXML
    private TableView<Types> attrNameTableView;//kullanılmıyacak
    @FXML
    private TableColumn<Types,String> typeAttributesNames;//kullanılmıyacak
    @FXML
    ListView<String> typeAttrsListView= new ListView<>();

    /**Tag Page Components**/

    @FXML
    private Tab tagTab;
    @FXML
    private TextField tagsTextField;



    /**Main Page Methods**/
    public void goToNewTypePage(){
        tabPane.getSelectionModel().select(typeTab);
    }
    public void goToNewItemPage(){
        tabPane.getSelectionModel().select(itemTab);
    }
    public void goToNewTagPage(){
        tabPane.getSelectionModel().select(tagTab);
    }

    /** Item Page Methods **/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tagNameChoiceBox.getItems().add("");

    }
    public void addAttributeValue() {
        if(!this.enterItemsAttrValue.getText().isEmpty()) {

            Types type = new Types("","");
            String typeName = typeNameComboBox.getSelectionModel().getSelectedItem();

            for(int i =0;i<Types.typesArrayList.size();i++) {
                if (Types.typesArrayList.get(i).getTypeName().equals(typeName)) {
                    type = Types.typesArrayList.get(i);
                }
            }
            if(type.getTypeAttrNameCount()<=attrValueCount){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You can't add attribute values anymore!");
                alert.showAndWait();
            }
            else {
                String value = this.enterItemsAttrValue.getText();
                itemsAttrValuesListView.getItems().add(value);
                enterItemsAttrValue.clear();
                attrValueCount++;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
    }
    public void deleteAttributeValue() {
        itemsAttrValuesListView.getItems().remove(itemsAttrValuesListView.getSelectionModel().getSelectedItem());
    }
    public void editAttributeValue() {

    }
    public void createItem(){
        String itemsName =itemName.getText();
        if (itemsName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Item name cannot be empty");
            alert.showAndWait();
            return;
        }
        String tagsName = tagNameChoiceBox.getSelectionModel().getSelectedItem();
        String typesName = typeNameComboBox.getSelectionModel().getSelectedItem();
        Tags itemsTag= new Tags("");
        Types itemsType= new Types("","");
        for(int i =0;i<Tags.tagsArrayList.size();i++){
            if(Tags.tagsArrayList.get(i).getTagName().equals(tagsName)){
                itemsTag = Tags.tagsArrayList.get(i);
            }
        }
        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(typesName)){
                itemsType = Types.typesArrayList.get(i);
            }

        }
        if (itemsType.getTypeName().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select a type or create a type");
            alert.showAndWait();
            return;
        }
        if(!(itemsType.getTypeAttrNameCount()==attrValueCount)){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Type Attributes Values are not completed!");
            alert.showAndWait();
            return;
        }

        ArrayList<String> itemsAttrValueArrayList=new ArrayList<>(itemsAttrValuesListView.getItems());

        if(itemsTag.equals(new Tags(""))){
            Items item = new Items( itemsType, itemsName, itemsAttrValueArrayList);
            //TODO: attribute valueları observable list olarak almamız gerek. Items classında private observale list -
            //TODO: itemattrvalue variableı oluşturup constructor oluşturulacak.

            itemsType.getTypesItems().add(item);


            itemsType.getTypesTitledPane().setContent(item.getItemTypeListView());
            item.getItemTypeListView().getItems().add(item.getItemName());

            itemName.clear();

        }

        else {
            Items item = new Items(itemsTag, itemsType, itemsName, itemsAttrValueArrayList);


            itemsType.getTypesItems().add(item);
            itemsTag.getTagsItems().add(item);

            itemsTag.getTagsItemsListView().getItems().add(itemsName);
            itemsTag.getTagsTitledPane().setContent(itemsTag.getTagsItemsListView());

            itemsType.getTypesItemsListView().getItems().addAll(itemsName);
            itemsType.getTypesTitledPane().setContent(itemsType.getTypesItemsListView());
            itemName.clear();
        }



        itemsAttrValuesListView.getItems().clear();
        attrValueCount=0;


    }





    /** Type Page Methods **/

    public void createType(){
        if (typeAttrsListView.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute name");
            alert.showAndWait();
            return;
        }

        for (int i=0;i<Types.typesArrayList.size();i++){
            if (Types.typesArrayList.get(i).getTypeName().equals(typesTextField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("There is already a type with this name!");
                alert.showAndWait();
                typesTextField.clear();
                typeAttrsListView.getItems().clear();
                return;
            }
        }

        ArrayList<String> attrNamesOfThisType = new ArrayList<>(typeAttrsListView.getItems());
        Types type= new Types(typesTextField.getText(),attrNamesOfThisType);
        type.setTypeAttrNameCount(attrNamesOfThisType.size());
        type.getTypesTitledPane().setText(typesTextField.getText());
        typeTitledPaneVbox.getChildren().addAll(type.getTypesTitledPane());
        typeNameComboBox.getItems().addAll(typesTextField.getText());
        Types.typesArrayList.add(type);
        typesTextField.clear();
        typeAttrsListView.getItems().clear();
        tabPane.getSelectionModel().select(itemTab);

    }
    public void typeComboBoxOnAction() {
        attrValueCount=0;
        showTypeAttrValuesInItemPage.getItems().clear();
        itemsAttrValuesListView.getItems().clear();
        Types tempType = new Types("", "");
        for (int i = 0; i < Types.typesArrayList.size(); i++) {
            if (Types.typesArrayList.get(i).getTypeName().equals(typeNameComboBox.getSelectionModel().getSelectedItem())) {
                tempType = Types.typesArrayList.get(i);
            }
        }

        for (int i = 0; i < tempType.getTypeAttrNameArrayList().size(); i++) {
            showTypeAttrValuesInItemPage.getItems().add(tempType.getTypeAttrNameArrayList().get(i));
        }
    }
    public void addAttributeNameListView(){
        if (!attrNamesEntryTextField.getText().isEmpty()){
            String attrNames = attrNamesEntryTextField.getText();
            typeAttrsListView.getItems().add(attrNames);
            attrNamesEntryTextField.clear();
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Attribute cannot be empty");
            alert.showAndWait();
        }
    }



    public void deleteAttributeNameFromAttrNameListView(){
        typeAttrsListView.getItems().remove(typeAttrsListView.getSelectionModel().getSelectedItem());
    }


    /** Tag Page Methods **/
    public void createTag(){

        for (int i=0;i<Tags.tagsArrayList.size();i++){
            if (Tags.tagsArrayList.get(i).getTagName().equals(tagsTextField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("There is already a tag with this name!");
                alert.showAndWait();
                tagsTextField.clear();
                return;
            }
        }


        Tags tag = new Tags(tagsTextField.getText());
        tag.getTagsTitledPane().setText(tagsTextField.getText());
        tagTitledPaneVbox.getChildren().addAll(tag.getTagsTitledPane());
        tagNameChoiceBox.getItems().addAll(tagsTextField.getText());
        tagsTextField.clear();
        Tags.tagsArrayList.add(tag);



    }





    /** deneme methodları **/









}