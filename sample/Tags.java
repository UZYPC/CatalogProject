package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

import java.util.ArrayList;

public class Tags {


    private String tagName;
    private ArrayList<Items> tagsItems= new ArrayList<>();



    static ArrayList<Tags> tagsArrayList = new ArrayList<>();

    @FXML
    private TitledPane tagsTitledPane= new TitledPane();
    private ListView<String> tagsItemsListView= new ListView<>();



    public Tags(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }


    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ArrayList<Items> getTagsItems() {
        return tagsItems;
    }

    public void setTagsItems(ArrayList<Items> tagsItems) {
        this.tagsItems = tagsItems;
    }
    public ArrayList<Tags> getTagsArrayList() {
        return tagsArrayList;
    }

    public void setTagsArrayList(ArrayList<Tags> tagsArrayList) {
        this.tagsArrayList = tagsArrayList;
    }

    public TitledPane getTagsTitledPane() {
        return tagsTitledPane;
    }

    public void setTagsTitledPane(TitledPane tagsTitledPane) {
        this.tagsTitledPane = tagsTitledPane;
    }

    public ListView<String> getTagsItemsListView() {
        return tagsItemsListView;
    }

    public void setTagsItemsListView(ListView<String> tagsItemsListView) {
        this.tagsItemsListView = tagsItemsListView;
    }
}
