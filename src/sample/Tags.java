package sample;

import java.util.ArrayList;

public class Tags {


    private String tagName;
    private ArrayList<Items> tagsItems;



    static ArrayList<Tags> tagsArrayList;


    public void addtagsList(Tags tag){
        tagsArrayList.add(tag);
    }




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
}
