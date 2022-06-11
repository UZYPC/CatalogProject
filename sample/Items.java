package sample;

import javafx.scene.control.ListView;

import java.util.ArrayList;

public class Items {

    private ArrayList<Tags> itemTag = new ArrayList<>();
    private Types itemType;
    private String itemName;
    private String attributeValues;

    static ArrayList<Items> itemsArrayList = new ArrayList<>();

    private ArrayList<String> itemsAttributeValueList= new ArrayList<>();

    private ListView<String> itemTypeListView= new ListView<>();
    private ListView<String> itemTagListView= new ListView<>();

    public Items(ArrayList<Tags> itemTag , Types itemType, String itemName) {
        this.itemTag=itemTag;
        this.itemType = itemType;
        this.itemName = itemName;
    }//this is the one with tag

    public Items(Types itemType, String itemName) {
        this.itemType = itemType;
        this.itemName = itemName;
    }// this is the one without tag








    public Items(String attributeValues) {
        this.attributeValues = attributeValues;
    }

    public Items(Types itemType, String itemName, String attributeValues) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.attributeValues = attributeValues;
    }


    public Items(String itemName, ArrayList<String> itemsAttributeValueList) {
        this.itemName = itemName;
        this.itemsAttributeValueList = itemsAttributeValueList;
    }

    public Items(Types itemType, String itemName, ArrayList<String> itemsAttributeValueList) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemsAttributeValueList = itemsAttributeValueList;
    }


    public Types getItemType() {
        return itemType;
    }

    public void setItemType(Types itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(String attributeValues) {
        this.attributeValues = attributeValues;
    }


    public ListView<String> getItemTypeListView() {
        return itemTypeListView;
    }

    public void setItemTypeListView(ListView<String> itemTypeListView) {
        this.itemTypeListView = itemTypeListView;
    }

    public ListView<String> getItemTagListView() {
        return itemTagListView;
    }

    public void setItemTagListView(ListView<String> itemTagListView) {
        this.itemTagListView = itemTagListView;
    }
    public ArrayList<String> getItemsAttributeValueList() {
        return itemsAttributeValueList;
    }

    public void setItemsAttributeValueList(ArrayList<String> itemsAttributeValueList) {
        this.itemsAttributeValueList = itemsAttributeValueList;
    }

    public ArrayList<Tags> getItemTag() {
        return itemTag;
    }

    public void setItemTag(ArrayList<Tags> itemTag) {
        this.itemTag = itemTag;
    }
}
