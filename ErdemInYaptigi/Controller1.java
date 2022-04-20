//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ErdemInYaptigi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

  /** Type adding page **/
public class Controller1 implements Initializable {
    @FXML
    private Types types;
    @FXML
    private Item item;
    @FXML
    private TextField typeName;
    @FXML
    private Button addTypeName;
    @FXML
    private Button addAttributeName;
    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private Button save;
    @FXML
    private TextField attributeName;
    @FXML
    private TableView<Types> tableView;
    @FXML
    private TableColumn<Types, String> name;
    @FXML
    Controller2 controller2;


    public Controller1() {
        this.controller2 = new Controller2();
    }
    public void setController2(Controller2 controller2) {
        this.controller2 = controller2;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller2.init(this);
        this.name.setCellValueFactory(new PropertyValueFactory<Types, String>("attributesNames"));
        this.tableView.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                        Types selectedItem = this.tableView.getSelectionModel().getSelectedItem();
                        this.attributeName.setText(selectedItem.getAttributesNames());
                    }
                }
        );
    }
    public void addAttributeName(){
        if (!this.attributeName.getText().isEmpty()){
            String name = this.attributeName.getText();
            Types type = new Types(name);
            this.tableView.getItems().add(type);
            this.attributeName.clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter attribute name");
            alert.showAndWait();
        }
    }
    public void addTypeName(){
        if (!this.typeName.getText().isEmpty()){
            Types typeName = new Types(this.typeName.getText());
            this.tableView.getItems().add(typeName);
            this.typeName.clear();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter type name");
            alert.showAndWait();
        }
    }
    public void deleteAttributeName(){
        this.tableView.getItems().remove(this.tableView.getSelectionModel().getSelectedItem());
    }
    public void deleteTypeName(){
        this.tableView.getItems().remove(this.tableView.getSelectionModel().getSelectedItem());
    }
    public void editAttributeName(){
        Types selectedItem = tableView.getSelectionModel().getSelectedItem();
        selectedItem.setAttributesNames(attributeName.getText());
        this.tableView.refresh();

    }
    public void editTypeName(){
        Types selectedItem = tableView.getSelectionModel().getSelectedItem();
        selectedItem.setTypeName(typeName.getText());
        this.tableView.refresh();

    }
    public void saveTypeName(){
        this.controller2.saveTypeName();

    }

    public void printTypeNameToListView(){
        Types type = new Types();
        type.setTypeName(typeName.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        Controller2 controller2 = loader.getController();
       // controller2.printToTypeList(type);
    }

}
