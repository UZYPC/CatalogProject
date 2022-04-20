package ErdemInYaptigi;

import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;

public class Tags {

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    private String tagName;
    private ArrayList<Item> tagItems;

    public ArrayList<Item> getTagItems() {
        return tagItems;
    }

    public void setTagItems(ArrayList<Item> tagItems) {
        this.tagItems = tagItems;
    }
}
