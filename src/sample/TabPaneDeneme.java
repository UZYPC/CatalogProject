package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.ToDoubleBiFunction;

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
    private TableView<Items> itemsAttrTableView;
    @FXML
    private TableColumn<Items, String> itemAttrValue;
    @FXML
    private TableColumn<Types, String> typeAttrName;
    @FXML
    private Types types;
    @FXML
    private TextField enterItemsAttrValue;
    @FXML
    private TextField itemName;
    @FXML
    private ChoiceBox<String> tagNameChoiceBox;//TextField tagName da olabilir
    @FXML
    private ChoiceBox<String> typeNameChoiceBox;
    @FXML
    private VBox typeTitledPaneVbox;
    @FXML
    private VBox tagTitledPaneVbox;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private Button addValue;
    @FXML
    private Button deleteValue;
    @FXML
    private Button editValue;
    @FXML
    private Button save;
    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane typesTitledPane;
    @FXML
    private TitledPane tagsTitledPane;
//    @FXML
//    Item item;


    /**Type Page Components**/
    @FXML
    private Tab typeTab;
    @FXML
    private TextField typesTextField;
    @FXML
    private TextField attrNamesEntryTextField;
    @FXML
    private TableView<Types> attrNameTableView;
    @FXML
    private TableColumn<Types, String> attrNameTableColumn;

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
        this.itemAttrValue.setCellValueFactory(new PropertyValueFactory<Items,String>("attributeValues"));
        this.itemsAttrTableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                Items selectedItem = this.itemsAttrTableView.getSelectionModel().getSelectedItem();
                this.itemAttrValue.setText(selectedItem.getAttributeValues());
            }
        });
    }
    public void addAttributeValue() {
        if(!this.enterItemsAttrValue.getText().isEmpty()) {
            String value = this.enterItemsAttrValue.getText();
            Items item = new Items(value);
            this.itemsAttrTableView.getItems().add(item);
            this.enterItemsAttrValue.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
    }
    public void deleteAttributeValue() {
        Items item = this.itemsAttrTableView.getSelectionModel().getSelectedItem();
        this.itemsAttrTableView.getItems().remove(item);
    }
    public void editAttributeValue() {
        Items selectedItem = itemsAttrTableView.getSelectionModel().getSelectedItem();
        if (enterItemsAttrValue.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
        else {
            selectedItem.setAttributeValues(enterItemsAttrValue.getText());
        }
        itemsAttrTableView.refresh();
    }




    /** Type Page Methods **/
    public void typeAktarma(){
       // typesListView.getItems().addAll(typesTextField.getText());

        TitledPane titledPane = new TitledPane();
        ListView<Items> listView = new ListView<>();
        titledPane.setText(typesTextField.getText());
        typeTitledPaneVbox.getChildren().addAll(titledPane);
        typeNameChoiceBox.getItems().addAll(typesTextField.getText());

        typesTextField.clear();

        //TODO: bu methodda bir type objesi oluşturmamız gerek ve oluşturulan tüm typeların bulunduğu bir yere gitmeli



    }
//    @Override
    // TODO type kısmında table viewe eklenmemesinin sebebi burda.
////    public void initialize(URL url, ResourceBundle resourceBundle) {
////        this.attrNameTableColumn.setCellValueFactory(new PropertyValueFactory<Types, String>("attributesNames"));
////        this.attrNameTableView.setOnMouseClicked(event -> {
////                    if (event.getClickCount() == 2) {
////                        Types selectedItem = this.attrNameTableView.getSelectionModel().getSelectedItem();
////                        this.attrNamesEntryTextField.setText(selectedItem.getTypeAttributesNames());
////                    }
////                }
////        );
////    }
    public void addAttributeNameToAttrNameTableView(){
        if (!attrNamesEntryTextField.getText().isEmpty()){
            String attrNames = attrNamesEntryTextField.getText();
            Types type = new Types(attrNames);
            attrNameTableView.getItems().add(type);
            attrNamesEntryTextField.clear();



        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute name");
            alert.showAndWait();
        }
    }


    public void deleteAttributeNameFromAttrNameTableView(){
        this.attrNameTableView.getItems().remove(this.attrNameTableView.getSelectionModel().getSelectedItem());
    }

    public void editAttributeNameFromAttrNameTableView(){
        Types selectedItem = attrNameTableView.getSelectionModel().getSelectedItem();
        selectedItem.setTypeAttributesNames(attrNamesEntryTextField.getText());
        this.attrNameTableView.refresh();

    }

    /** Tag Page Methods **/
    public void tagAktarma(){
        TitledPane titledPane = new TitledPane();
        ListView<Items> listView = new ListView<>();
        Tags tag = new Tags(tagsTextField.getText());
        titledPane.setText(tagsTextField.getText());
        tagTitledPaneVbox.getChildren().addAll(titledPane);
        tagNameChoiceBox.getItems().addAll(tag.getTagName());
        tagsTextField.clear();


        // TODO:  bu methodda bir tag objesi oluşturmamız gerek ve oluşturulan tüm tagların bulunduğu bir yere gitmeli

    }




    /** deneme methodları **/

    public void createItem(){
        String itemsName =itemName.getText();
        String tagsName = tagNameChoiceBox.getSelectionModel().getSelectedItem();
        String typesName = typeNameChoiceBox.getSelectionModel().getSelectedItem();
        Tags itemsTag;
        Types itemsType;
        for(int i =0;i<Tags.tagsArrayList.size();i++){
            if(Tags.tagsArrayList.get(i).getTagName().equals(tagsName)){
                itemsTag = Tags.tagsArrayList.get(i);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please select a tag");
                alert.showAndWait();

            }
        }
        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(typesName)){
                itemsType = Types.typesArrayList.get(i);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please select a type");
                alert.showAndWait();
            }
        }

//        Items item = new Items(itemsTag, itemsType, itemsName, "attrs");
//        Types types = new Types(typesName,"attrs");
//        Tags tags = new Tags(tagsName);
//        types.getTypesItems().add(item);
//        tags.getTagsItems().add(item);

        // TODO: yukarıdaki gibi bir item oluşturup type ve tag arraylistine eklemek gerek;


    }

}
