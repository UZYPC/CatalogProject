//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ErdemInYaptigi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Controller;

public class Controller2 implements Initializable {
    @FXML
    private AnchorPane mainPage;
    @FXML
    private VBox typesVbox;
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> value;
    @FXML
    private Types types;
    @FXML
    private TextField attrValue;
    @FXML
    private TextField itemName;
    @FXML
    private TextField tagName;
    @FXML
    private TextArea itemDescription;
    @FXML
    private ListView<String> typesListView;
    @FXML
    private ListView<String> tagsListView;
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
    private TitledPane titledPane;
    @FXML
    Item item;
    @FXML
    Controller1 controller1;
    private TextField typeName;
    private int i =0;


    public void setController1(Controller1 controller1) {
        this.controller1 = controller1;
    }
    public void init(Controller1 controller1) {
        this.controller1 = controller1;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.value.setCellValueFactory(new PropertyValueFactory<Item,String>("attributeValues"));
        this.tableView.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                Item selectedItem = this.tableView.getSelectionModel().getSelectedItem();
                this.attrValue.setText(selectedItem.getAttributeValues());
            }
        });
    }
    public void addAttributeValue() {
        if(!this.attrValue.getText().isEmpty()) {
            String value = this.attrValue.getText();
            Item item = new Item(value);
            this.tableView.getItems().add(item);
            this.attrValue.clear();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
    }
    public void deleteAttributeValue() {
        Item item = this.tableView.getSelectionModel().getSelectedItem();
        this.tableView.getItems().remove(item);
    }
    public void editAttributeValue() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (attrValue.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute value");
            alert.showAndWait();
        }
        else {
            selectedItem.setAttributeValues(attrValue.getText());
        }
        tableView.refresh();
    }
    public void addItem() {
        if(!this.itemName.getText().isEmpty() && !this.itemDescription.getText().isEmpty() && !this.tagName.getText().isEmpty()) {
            String name = this.itemName.getText();
            String description = this.itemDescription.getText();
            String tag = this.tagName.getText();
            Types type = this.types;
            Item item = new Item(type,name, description,tag);
            this.itemName.clear();
            this.itemDescription.clear();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter item name and description");
            alert.showAndWait();
        }
    }


    public void saveTypeName() {
        if(!this.typeName.getText().isEmpty()) {
            String name = this.typeName.getText();
            Item item = new Item(name);
            this.typeName.clear();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter item name");
            alert.showAndWait();
        }
    }
    public void addTypeScreen(ActionEvent event) throws IOException {
       // ArrayList<Controller> items = new ArrayList<>();// on actionına akordiyona buton ekleme şeyi gelecek
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/yourcompanyname/yourapplicationname/layouts/nameOfYourFxmlFile.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Add your type.");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void newTagScreen() throws IOException {
        AnchorPane tagScreen = FXMLLoader.load(getClass().getResource("TagsPage.fxml"));
        mainPage.getChildren().removeAll();
        mainPage.getChildren().setAll(tagScreen);
    }

    public void printToTypeList(){
        String types = typeName.getText();
        typesListView.getItems().add(types);
    }

    public void addTagsToTheListView(){
        Tags tag = new Tags();
        tag.setTagName(tagName.getText());
        tagsListView.getItems().add(tag.getTagName());
    }

    public void addTypeTitledPanes(){
        i++;
        TitledPane titledPane = new TitledPane();
        titledPane.setText(""+i);
      //  titledPane.getContent().
        typesVbox.getChildren().addAll(titledPane);

    }
}
