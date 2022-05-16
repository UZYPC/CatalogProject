package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.print.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;


import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CatalogController implements Initializable {
    @FXML
    private TabPane tabPane;
    @FXML
    private VBox mainVBox;

    /**Main Page Components**/
    @FXML
    private Tab mainTab;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> searchResults;
     ArrayList<String> searchArrayList = new ArrayList<>();
    @FXML
    private ComboBox<String> filterTagsComboBox;
    @FXML
    private ListView<String> filterResultsListView;



    /**Item Page Components**/
    @FXML
    private Tab itemTab;
    @FXML
    private TextField enterItemsAttrValue;
    @FXML
    private TextField itemName;
    @FXML
    private ComboBox<String> tagNameComboBox;
    @FXML
    private ComboBox<String> typeNameComboBox;
    @FXML
    private ComboBox<String> deleteTagComboBox;
    @FXML
    private VBox typeTitledPaneVbox;
    @FXML
    private VBox tagTitledPaneVbox;
    @FXML
    ListView<String> showTypeAttrValuesInItemPage= new ListView<>();
    @FXML
    ListView<String> itemsAttrValuesListView= new ListView<>();
    @FXML
    ListView<String> tagItemsListView= new ListView<>();
    private int attrValueCount;

    /**Type Page Components**/

    @FXML
    private Tab typeTab;
    @FXML
    private TextField typesTextField;
    @FXML
    private TextField attrNamesEntryTextField;
    @FXML
    ListView<String> typeAttrsListView= new ListView<>();


    /**Tag Page Components**/

    @FXML
    private Tab tagTab;
    @FXML
    private TextField tagsTextField;


    /** Edit/Delete Type Components **/
    @FXML
    private Tab editTypeTab;
    @FXML
    private ListView<String> deleteTypeListView = new ListView<>();
    @FXML
    private ListView<String> editDeleteTypeAttrNamesListView = new ListView<>();
    @FXML
    private ComboBox<String> selectTypeComboBox;
    @FXML
    private TextField editTypeAttrNamesTextField;


    /** Edit Delete/Item Components **/
    @FXML
    private Tab editItemTab;
    @FXML
    private ComboBox <String> editItemSelectedTypeComboBox;
    @FXML
    private ListView<String>  selectedTypesItemsListView;
    @FXML
    private ListView<String> selectedItemsAttrValuesListView;
    @FXML
    private ComboBox <String> selectItemComboBox;
    @FXML
    private TextField editItemsAttributeValueTextField;




    /** initialize method **/



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filterTagsComboBox.getItems().add("");
        readType();
        readItem();
        readTag();
        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    search();
                }
            }
        });


    }
    public void printItems(){
            PrinterJob printerJob =PrinterJob.createPrinterJob();
        Scene scene = Objects.requireNonNull(tagItemsListView.getScene(), "Missing Scene");
        if ( !printerJob.showPrintDialog(scene.getWindow()))
            return;

       boolean printed= printerJob.printPage(tagItemsListView);
        if (printed) {
            printerJob.endJob();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Print Failed!");
            alert.showAndWait();
            return;

        }

    }
    public void printTypes(){
        PrinterJob printerJob =PrinterJob.createPrinterJob();
        Scene scene = Objects.requireNonNull(deleteTypeListView.getScene(), "Missing Scene");
        if ( !printerJob.showPrintDialog(scene.getWindow()))
            return;

        boolean printed= printerJob.printPage(deleteTypeListView);
        if (printed) {
            printerJob.endJob();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Print Failed!");
            alert.showAndWait();
            return;

        }

    }


    public void closeProgram() throws IOException {
        // ask if they want to save or not
        //if they want to save then
        try {
            writeType();
            writeItem();
            writeTag();

            Platform.exit();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    HelpManual helpManual = new HelpManual();
    public void helpManual(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Help");
        alert.setContentText("WELCOME TO THE CATALOG PROGRAM!");
        TextArea area = new TextArea(helpManual.getText());
        area.setWrapText(true);
        area.setEditable(false);

        alert.getDialogPane().setExpandableContent(area);
        alert.showAndWait();
    }
    public void export(){
        StringBuilder txt = new StringBuilder();
        for (Types types : Types.typesArrayList){
            txt.append(types.getTypeName()).append("{\n");
            for (int i =0;i<types.getTypesItems().size();i++){
                txt.append("-- ").append(types.getTypesItems().get(i).getItemName());

                for(int x=0;x<Tags.tagsArrayList.size();x++){
                    for (int xx=0;xx<Tags.tagsArrayList.get(x).getTagsItems().size();xx++){
                        if (Tags.tagsArrayList.get(x).getTagsItems().get(xx).getItemName().equals(types.getTypesItems().get(i).getItemName())){
                            txt.append(" [Tag: ").append(Tags.tagsArrayList.get(x).getTagName()).append("] ");
                        }
                    }
                }

                for (int j =0;j<types.getTypeAttrNameArrayList().size();j++){
                    txt.append(" ( ").append(types.getTypeAttrNameArrayList().get(j)).append(": ").append(types.getTypesItems().get(i).getItemsAttributeValueList().get(j)).append(" )");
                }
                txt.append("\n");
            }
            txt.append("}\n\n");
        }
        FileChooser c = new FileChooser();
        FileChooser.ExtensionFilter ef = new FileChooser.ExtensionFilter("text file", "*.txt");
        c.getExtensionFilters().add(ef);
        File f = c.showSaveDialog(deleteTypeListView.getScene().getWindow());
        try {
            Files.writeString(Path.of(f.getPath()), txt);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("");
        }
    }


    /** Edit Delete/Item Methods**/

    public void editItemSelectedTypeComboBoxOnAction(){
        selectItemComboBox.getItems().clear();
        selectedTypesItemsListView.getItems().clear();

        Types temp = new Types("", "");
        for (int i = 0; i < Types.typesArrayList.size(); i++) {
            if (Types.typesArrayList.get(i).getTypeName().equals(
                    editItemSelectedTypeComboBox.getSelectionModel().getSelectedItem()))
                temp=Types.typesArrayList.get(i);
        }

        for (int i =0;i<temp.getTypesItems().size();i++){
            selectedTypesItemsListView.getItems().addAll(temp.getTypesItems().get(i).getItemName());
        }

        for (int i =0;i<temp.getTypesItems().size();i++){
            selectItemComboBox.getItems().addAll(temp.getTypesItems().get(i).getItemName());
        }
    }

    public void selectItemComboBoxOnAction(){
        selectedItemsAttrValuesListView.getItems().clear();


        Types temp= new Types("","");
        Items item = new Items("");
        for (int i = 0; i < Types.typesArrayList.size(); i++) {
            if (Types.typesArrayList.get(i).getTypeName().equals(
                    editItemSelectedTypeComboBox.getSelectionModel().getSelectedItem()))
                temp=Types.typesArrayList.get(i);
        }
        for (int i =0;i<temp.getTypesItems().size();i++){
            if(temp.getTypesItems().get(i).getItemName().equals(selectItemComboBox.getSelectionModel().getSelectedItem())){
                item=temp.getTypesItems().get(i);
            }
        }

        for (int i=0;i<item.getItemsAttributeValueList().size();i++){
            selectedItemsAttrValuesListView.getItems().addAll(item.getItemsAttributeValueList().get(i));
        }

    }
    public void deleteSelectedItem(){
        Types type=new Types("","");
        Items item ;

        selectedTypesItemsListView.getSelectionModel().getSelectedItem();

        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(editItemSelectedTypeComboBox.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
            }
        }

        for (int i =0;i<type.getTypesItems().size();i++){
            if(type.getTypesItems().get(i).getItemName().equals(selectedTypesItemsListView.getSelectionModel().getSelectedItem())){
                item=type.getTypesItems().get(i);
                type.getTypesItems().remove(item);
                type.getTypesItemsListView().getItems().remove(item.getItemName());
                selectedTypesItemsListView.getItems().remove(item.getItemName());
                selectItemComboBox.getItems().remove(item.getItemName());
                searchArrayList.remove("Item: "+item.getItemName());
                Items.itemsArrayList.remove(item);

            }


        }
    }


    public void deleteSelectedItemsAttrValue(){

        /**  Logically we don't need this method **/

        Types type=new Types("","");
        Items item = new Items("");



        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(editItemSelectedTypeComboBox.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
            }
        }

        for (int i =0;i<type.getTypesItems().size();i++){
            if(type.getTypesItems().get(i).getItemName().equals(selectItemComboBox.getSelectionModel().getSelectedItem())){
                item=type.getTypesItems().get(i);
            }
        }
        if(type.getTypeAttrNameArrayList().size()<=item.getItemsAttributeValueList().size()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You can't remove attributes anymore!");
            alert.showAndWait();
            return;

        }

        item.getItemsAttributeValueList().remove(selectedItemsAttrValuesListView.getSelectionModel().getSelectedItem());
        selectedItemsAttrValuesListView.getItems().remove(selectedItemsAttrValuesListView.getSelectionModel().getSelectedItem());



    }
    public void editSelectedItemsAttrValue(){


        Types type=new Types("","");
        Items item = new Items("");



        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(editItemSelectedTypeComboBox.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
            }
        }

        for (int i =0;i<type.getTypesItems().size();i++){
            if(type.getTypesItems().get(i).getItemName().equals(selectItemComboBox.getSelectionModel().getSelectedItem())){
                item=type.getTypesItems().get(i);
            }
        }
        item.getItemsAttributeValueList().set(selectedItemsAttrValuesListView.getSelectionModel().getSelectedIndex(),editItemsAttributeValueTextField.getText());
        selectedItemsAttrValuesListView.getItems().set(selectedItemsAttrValuesListView.getSelectionModel().getSelectedIndex(),editItemsAttributeValueTextField.getText());
    }

    /** Edit/Delete Type Methods **/


    public void deleteSelectedType(){
        Types type;
        deleteTypeListView.getSelectionModel().getSelectedItem();

        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(deleteTypeListView.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
                for (int j =0;j<type.getTypesItems().size();j++){
                    searchArrayList.remove("Item: "+type.getTypesItems().get(j).getItemName());
                    if(type.getTypesItems().get(j).getItemTag()!=null){
                        for (int x=0;x<type.getTypesItems().get(j).getItemTag().size();x++){
                            type.getTypesItems().get(j).getItemTag().get(x).getTagsItems().remove(Items.itemsArrayList.get(j));
                        }
                    }
                    tagItemsListView.getItems().remove(type.getTypesItems().get(j).getItemName());
                    Items.itemsArrayList.remove(type.getTypesItems().get(j));
                }

                typeTitledPaneVbox.getChildren().removeAll(type.getTypesTitledPane());
                typeNameComboBox.getItems().removeAll(type.getTypeName());
                deleteTypeListView.getItems().removeAll(type.getTypeName());
                selectTypeComboBox.getItems().removeAll(type.getTypeName());
                editItemSelectedTypeComboBox.getItems().removeAll(type.getTypeName());
                searchArrayList.remove("Type: "+type.getTypeName());
                Types.typesArrayList.remove(type);

            }
        }
    }

    public void selectTypeComboBoxOnAction(){
        editDeleteTypeAttrNamesListView.getItems().clear();

        Types type= new Types("","");
        selectTypeComboBox.getSelectionModel().getSelectedItem();
        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(selectTypeComboBox.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
            }
        }
        for (int i =0;i<type.getTypeAttrNameArrayList().size();i++){
            editDeleteTypeAttrNamesListView.getItems().add(type.getTypeAttrNameArrayList().get(i));
        }


    }

    public void deleteSelectedTypeAttrName(){
        Types type= new Types("","");
        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(selectTypeComboBox.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
            }
        }
        if(type.getTypeAttrNameArrayList().size()<=1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You can't remove attributes anymore!");
            alert.showAndWait();
            return;

        }
        int selectedIndex=editDeleteTypeAttrNamesListView.getSelectionModel().getSelectedIndex();
        type.getTypeAttrNameArrayList().remove(editDeleteTypeAttrNamesListView.getSelectionModel().getSelectedItem());
        editDeleteTypeAttrNamesListView.getItems().remove(editDeleteTypeAttrNamesListView.getSelectionModel().getSelectedItem());
        for (int i =0;i<type.getTypesItems().size();i++){
            type.getTypesItems().get(i).getItemsAttributeValueList().remove(selectedIndex);
        }
    }

    public void editSelectedTypesAttrNames(){
        Types type=new Types("","");

        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(selectTypeComboBox.getSelectionModel().getSelectedItem())){
                type=Types.typesArrayList.get(i);
            }
        }

        type.getTypeAttrNameArrayList().set(editDeleteTypeAttrNamesListView.getSelectionModel().getSelectedIndex(),editTypeAttrNamesTextField.getText());
        editDeleteTypeAttrNamesListView.getItems().set(editDeleteTypeAttrNamesListView.getSelectionModel().getSelectedIndex(),editTypeAttrNamesTextField.getText());
    }


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
    public void goToEditTypePage(){tabPane.getSelectionModel().select(editTypeTab);}
    public void goToEditItemPage(){tabPane.getSelectionModel().select(editItemTab);}



    public void filterItems() {
        Tags tag = new Tags("");


        for (int i = 0; i < Tags.tagsArrayList.size(); i++) {
            if (filterTagsComboBox.getSelectionModel().getSelectedItem().equals(Tags.tagsArrayList.get(i).getTagName())) {
                tag = Tags.tagsArrayList.get(i);

            }
        }
        if (tag.getTagSelectionCountForFiltering()>0){
            return;
        }
        tag.setTagSelectionCountForFiltering(1);
//        for (int i =0;i<filterResultsListView.getItems().size();i++){
//            for (int j =0;j<tag.getTagsItems().size();j++){
//                if(!(tag.getTagsItems().get(j).getItemName().equals(filterResultsListView.getItems().get(i)))){
//                    filterResultsListView.getItems().add(tag.getTagsItems().get(j).getItemName());
//                }
//            }
//        }
        for (int j = 0; j < tag.getTagsItems().size(); j++) {
            for (int i = 0; i < filterResultsListView.getItems().size(); i++) {
                if ((tag.getTagsItems().get(j).getItemName().equals(filterResultsListView.getItems().get(i)))) {
                    System.out.println();
                }
            }
            filterResultsListView.getItems().add(tag.getTagsItems().get(j).getItemName());
        }
    }
    public void clearFilteredList(){
        filterResultsListView.getItems().clear();
        for (Tags tags:Tags.tagsArrayList){
            tags.setTagSelectionCountForFiltering(0);
        }
    }

    public void search(){
        searchResults.getItems().clear();
        if (searchField.getText().equals("")){
            return;
        }
        searchResults.getItems().addAll(searchList(searchField.getText(),searchArrayList));
        if(searchResults.getItems().size()==0){
            searchResults.getItems().add("None Found.");
        }
    }


    public List<String> searchList(String searchWord, List<String> allWords){
        List<String> searchWordsArray = Arrays.asList(searchWord.trim().split(" "));

        return allWords.stream().filter(input-> {
            return searchWordsArray.stream().allMatch(word->input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }



    public void selectedItemInfo(){
       filterResultsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle(filterResultsListView.getSelectionModel().getSelectedItem());
               alert.setHeaderText(filterResultsListView.getSelectionModel().getSelectedItem());
               TextArea area = new TextArea(itemToString());
               area.setWrapText(true);
               area.setEditable(false);

               alert.getDialogPane().setExpandableContent(area);
               alert.setContentText(filterResultsListView.getSelectionModel().getSelectedItem()+ " Details:");
               alert.showAndWait();
           }

        });
    }
    public String itemToString(){
        Types type =new Types("");
        Items item = new Items("");
        for (Types types: Types.typesArrayList){
            for (int i =0;i<types.getTypesItems().size();i++){
                if (filterResultsListView.getSelectionModel().getSelectedItem().equals(types.getTypesItems().get(i).getItemName())){
                    type=types;
                    item=types.getTypesItems().get(i);
                }
            }
        }
        StringBuilder itemInfo= new StringBuilder();

        itemInfo.append("Item Type: ").append(type.getTypeName()).append("\n");
        itemInfo.append("--").append(item.getItemName()).append("\n");
        for(int x=0;x<Tags.tagsArrayList.size();x++){
            for (int xx=0;xx<Tags.tagsArrayList.get(x).getTagsItems().size();xx++){
                if (Tags.tagsArrayList.get(x).getTagsItems().get(xx).getItemName().equals(item.getItemName())){
                    itemInfo.append(" [Tag: ").append(Tags.tagsArrayList.get(x).getTagName()).append("] ");
                }
            }
        }itemInfo.append("\n");
        for (int i =0;i<type.getTypeAttrNameCount();i++){
            itemInfo.append(" ( ").append(type.getTypeAttrNameArrayList().get(i)).append(": ").append(item.getItemsAttributeValueList().get(i)).append(" )");

            if (i != type.getTypeAttrNameCount() - 1){
                itemInfo.append(", ").append("\n");
            }
        }




        return itemInfo.toString();
    }




    /** Item Page Methods **/


    public void addAttributeValue(){
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
        attrValueCount--;

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
    public void deleteTag(){
        Tags tag = new Tags("");
        for (int i =0;i<Tags.tagsArrayList.size();i++){
            if (deleteTagComboBox.getSelectionModel().getSelectedItem().equals(Tags.tagsArrayList.get(i).getTagName())){
                tag=Tags.tagsArrayList.get(i);
                Tags.tagsArrayList.remove(tag);
                tagTitledPaneVbox.getChildren().removeAll(tag.getTagsTitledPane());
                tagNameComboBox.getItems().removeAll(tag.getTagName());
                filterTagsComboBox.getItems().removeAll(tag.getTagName());
                deleteTagComboBox.getItems().removeAll(tag.getTagName());
            }
            else{
                return;
            }
        }
        searchArrayList.remove("Tag: "+tag.getTagName());
        Tags.tagsArrayList.remove(tag);


    }

    public void createItem(){
        Items item;
        String itemsName =itemName.getText();
        if (itemsName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Item name cannot be empty");
            alert.showAndWait();
            return;
        }
        String typesName = typeNameComboBox.getSelectionModel().getSelectedItem();
        Types itemsType= new Types("","");
        for(int i =0;i<Types.typesArrayList.size();i++){
            if(Types.typesArrayList.get(i).getTypeName().equals(typesName)){
                itemsType = Types.typesArrayList.get(i);
            }

        }
        for(int i =0;i<itemsType.getTypesItems().size();i++){
            if(itemsType.getTypesItems().get(i).getItemName().equals(itemsName)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("This item name was used before!");
                alert.showAndWait();
                itemsAttrValuesListView.getItems().clear();
                return;
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



            item = new Items( itemsType, itemsName);
            for(int i =0;i<itemsAttrValuesListView.getItems().size();i++){
                item.getItemsAttributeValueList().add(itemsAttrValuesListView.getItems().get(i));
            }
            Items.itemsArrayList.add(item);
            itemsType.getTypesItems().add(item);
            itemsType.getTypesTitledPane().setContent(itemsType.getTypesItemsListView());
           itemsType.getTypesItemsListView().getItems().add(item.getItemName());
            tagItemsListView.getItems().addAll(item.getItemName());
            searchArrayList.add("Item: "+itemsName);
            itemName.clear();



        itemsAttrValuesListView.getItems().clear();
        attrValueCount=0;

    }

    public void tagItem(){
        Tags tag = new Tags("");
        Items item = new Items("");
        if (tagNameComboBox.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please Select a tag!");
            alert.showAndWait();
            return;
        }

        for (int i = 0; i <Tags.tagsArrayList.size() ; i++) {
            if(tagNameComboBox.getSelectionModel().getSelectedItem().equals(Tags.tagsArrayList.get(i).getTagName())){

                tag=Tags.tagsArrayList.get(i);
            }

        }
        for (int i =0;i<Items.itemsArrayList.size();i++){
            if (tagItemsListView.getSelectionModel().getSelectedItem().equals(Items.itemsArrayList.get(i).getItemName())){
                item =Items.itemsArrayList.get(i);
            }
        }
        for (int i =0;i<tag.getTagsItems().size();i++){
            if ((tag.getTagsItems().get(i).equals(item))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("This tag already has this item!");
                alert.showAndWait();
                return;
            }
        }
        tag.getTagsItems().add(item);
        item.getItemTag().add(tag);
        tag.getTagsItemsListView().getItems().add(item.getItemName());
        tag.getTagsTitledPane().setContent(tag.getTagsItemsListView());
        clearFilteredList();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setContentText(item.getItemName()+ " has been tagged as "+tag.getTagName()+".");
        alert.showAndWait();


    }
    public void unTagItem(){
        Tags tag = new Tags("");
        Items item = new Items("");

        for (int i = 0; i <Tags.tagsArrayList.size() ; i++) {
            if(tagNameComboBox.getSelectionModel().getSelectedItem().equals(Tags.tagsArrayList.get(i).getTagName())){
                tag=Tags.tagsArrayList.get(i);
            }
        }
        for (int i =0;i<Items.itemsArrayList.size();i++){
            if (tagItemsListView.getSelectionModel().getSelectedItem().equals(Items.itemsArrayList.get(i).getItemName())){
                item =Items.itemsArrayList.get(i);
            }
        }
        for (int i =0;i<tag.getTagsItems().size();i++){
            if (!(tag.getTagsItems().get(i).equals(item))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("This item is not tagged as "+tag.getTagName()+"!");
                alert.showAndWait();
                return;
            }
        }
        tag.getTagsItems().remove(item);
        item.getItemTag().remove(tag);
        tag.getTagsItemsListView().getItems().remove(item.getItemName());
        tag.getTagsTitledPane().setContent(tag.getTagsItemsListView());


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setContentText(item.getItemName()+ " has been untagged.");
        alert.showAndWait();


    }



    /** Type Page Methods **/

    public void createType() {
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
        deleteTypeListView.getItems().add(typesTextField.getText());
        selectTypeComboBox.getItems().add(typesTextField.getText());
        editItemSelectedTypeComboBox.getItems().add(typesTextField.getText());
        Types.typesArrayList.add(type);
        searchArrayList.add("Type: "+typesTextField.getText());
        typesTextField.clear();
        typeAttrsListView.getItems().clear();
        tabPane.getSelectionModel().select(itemTab);

    }

    public void addAttributeNameListView(){
        if (!attrNamesEntryTextField.getText().isEmpty()){
            if (attrNamesEntryTextField.getText().contains(",")){
            String[] attrList = attrNamesEntryTextField.getText().split(",");
                for (String s : attrList) {
                    if (s.trim().equals("")){
                        continue;
                    }
                    typeAttrsListView.getItems().add(s.trim());
                    attrNamesEntryTextField.clear();
                }
            }else {
                String attrNames = attrNamesEntryTextField.getText();
                typeAttrsListView.getItems().add(attrNames);
                attrNamesEntryTextField.clear();
            }
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
        tagNameComboBox.getItems().addAll(tagsTextField.getText());
        filterTagsComboBox.getItems().addAll(tagsTextField.getText());
        deleteTagComboBox.getItems().add(tagsTextField.getText());
        searchArrayList.add("Tag: "+tagsTextField.getText());
        tagsTextField.clear();
        Tags.tagsArrayList.add(tag);
    }




    /** reader/writer methods**/
    public void writeType() throws IOException {

        FileWriter w = new FileWriter("typeInfo.txt", false);
        Formatter f = new Formatter(w);

        for (Types t : Types.typesArrayList) {
            f.format("%s, ", t.getTypeName());

            for (int i = 0; i < t.getTypeAttrNameArrayList().size(); i++) {
                f.format("%s", t.getTypeAttrNameArrayList().get(i));

                if (i != t.getTypeAttrNameArrayList().size() - 1) {
                    f.format(", ");
                }
            }
            f.format("%n");
        }
        f.close();
        w.close();
    }

    public void readType() {
        try {
            Scanner s = new Scanner(Paths.get("typeInfo.txt"));
            typeTitledPaneVbox.getChildren().clear();

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(",");

                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].trim();
                }

                ArrayList<String> typeAttrNames = new ArrayList<>(Arrays.asList(line).subList(1, line.length));
                Types type = new Types(line[0], typeAttrNames);
                Types.typesArrayList.add(type);
                type.setTypeAttrNameCount(typeAttrNames.size());
                typeNameComboBox.getItems().addAll(type.getTypeName());
                type.getTypesTitledPane().setText(type.getTypeName());
                typeTitledPaneVbox.getChildren().addAll(type.getTypesTitledPane());
                deleteTypeListView.getItems().add(type.getTypeName());
                selectTypeComboBox.getItems().add(type.getTypeName());
                editItemSelectedTypeComboBox.getItems().add(type.getTypeName());
                searchArrayList.add("Type: "+type.getTypeName());
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void writeType() throws IOException {
//        FileWriter w = new FileWriter("typeInfo.txt", false);
//        Formatter f = new Formatter(w);
//
//        for (Types t : Types.typesArrayList) {
//            f.format("%s, ", t.getTypeName());
//
//            for (int i = 0; i < t.getTypeAttrNameArrayList().size(); i++) {
//                f.format("%s", t.getTypeAttrNameArrayList().get(i));
//
//                if (i != t.getTypeAttrNameArrayList().size() - 1) {
//                    f.format(", ");
//                }
//            }
//            f.format("%n");
//        }
//        f.close();
//        w.close();
//    }

    public void writeTag() throws IOException {
        FileWriter w = new FileWriter("tagInfo.txt", false);
        Formatter f = new Formatter(w);

        for (Tags t : Tags.tagsArrayList) {
            f.format("%s ,",t.getTagName());

            for (int i = 0; i < t.getTagsItems().size(); i++) {
                f.format("%s", t.getTagsItems().get(i).getItemName());
                if (i != t.getTagsItems().size() - 1) {
                      f.format(", ");
                }
            }

            f.format("%n");
        }
        f.close();
        w.close();
    }

//    public void readType() {
//        try {
//            Scanner s = new Scanner(Paths.get("typeInfo.txt"));
//            typeTitledPaneVbox.getChildren().clear();
//
//            while (s.hasNextLine()) {
//                String[] line = s.nextLine().split(",");
//
//                for (int i = 0; i < line.length; i++) {
//                    line[i] = line[i].trim();
//                }
//
//                ArrayList<String> typeAttrNames = new ArrayList<>(Arrays.asList(line).subList(1, line.length));
//                Types type = new Types(line[0], typeAttrNames);
//                Types.typesArrayList.add(type);
//                type.setTypeAttrNameCount(typeAttrNames.size());
//                typeNameComboBox.getItems().addAll(type.getTypeName());
//                type.getTypesTitledPane().setText(type.getTypeName());
//                typeTitledPaneVbox.getChildren().addAll(type.getTypesTitledPane());
//                deleteTypeListView.getItems().add(type.getTypeName());
//                selectTypeComboBox.getItems().add(type.getTypeName());
//                editItemSelectedTypeComboBox.getItems().add(type.getTypeName());
//                searchArrayList.add("Type: "+type.getTypeName());
//            }
//            s.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void readTag() {
        try {
            Scanner s = new Scanner(Paths.get("tagInfo.txt"));
            tagTitledPaneVbox.getChildren().clear();

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(",");

                for (int i = 0; i < line.length; i++) {
                      line[i] = line[i].trim();
                  }

                ArrayList<String> tagItemsString = new ArrayList<>(Arrays.asList(line).subList(1, line.length));
                ArrayList<Items> tagItemsObject = new ArrayList<>();
                for (int i =0;i<tagItemsString.size();i++){
                    for (int j = 0; j < Items.itemsArrayList.size(); j++) {
                        if (tagItemsString.get(i).equals(Items.itemsArrayList.get(j).getItemName())){
                            tagItemsObject.add(Items.itemsArrayList.get(j));
                        }
                    }

                }

                Tags tag = new Tags(line[0],tagItemsObject);
                Tags.tagsArrayList.add(tag);
                for (String value : tagItemsString) {
                    tag.getTagsItemsListView().getItems().add(value);
                }
                tag.getTagsTitledPane().setContent(tag.getTagsItemsListView());
                tag.getTagsTitledPane().setText(tag.getTagName());
                tagTitledPaneVbox.getChildren().addAll(tag.getTagsTitledPane());
                tagNameComboBox.getItems().addAll(tag.getTagName());
                filterTagsComboBox.getItems().add(tag.getTagName());
                deleteTagComboBox.getItems().add(tag.getTagName());
                searchArrayList.add("Tag: "+tag.getTagName());
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeItem() throws IOException {
        FileWriter w = new FileWriter("itemInfo.txt", false);
        Formatter f = new Formatter(w);

        for (Items item : Items.itemsArrayList) {
            f.format("%s, ", item.getItemName());
            f.format("%s, ", item.getItemType().getTypeName());
            for (int i = 0; i < item.getItemsAttributeValueList().size(); i++) {
                f.format("%s", item.getItemsAttributeValueList().get(i));

                if (i != item.getItemsAttributeValueList().size() - 1) {
                    f.format(", ");
                }
            }
            f.format("%n");
        }
        f.close();
        w.close();
    }

    public void readItem(){
        try {
            Scanner s = new Scanner(Paths.get("itemInfo.txt"));


            for(int i =0;i<Types.typesArrayList.size();i++){
                Types.typesArrayList.get(i).getTypesItemsListView().getItems().clear();


            }

            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(",");

                for (int i = 0; i < line.length; i++) {
                    line[i] = line[i].trim();
                }

                Types itemsType = new Types("");
                for (int i =0;i<Types.typesArrayList.size();i++){
                    if (Types.typesArrayList.get(i).getTypeName().equals(line[1])){
                        itemsType =Types.typesArrayList.get(i);
                    }
                }
                        ArrayList<String> itemAttrValues = new ArrayList<>(Arrays.asList(line).subList(2, line.length));
                        Items item = new Items(line[0], itemAttrValues);
                        item.setItemType(itemsType);
                        Items.itemsArrayList.add(item);
                        itemsType.getTypesItems().add(item);
                        itemsType.getTypesItemsListView().getItems().add(item.getItemName());
                        itemsType.getTypesTitledPane().setContent(itemsType.getTypesItemsListView());
                        tagItemsListView.getItems().addAll(item.getItemName());
                        searchArrayList.add("Item: "+item.getItemName());




            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }































}