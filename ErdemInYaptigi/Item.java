package ErdemInYaptigi;

import javafx.scene.Node;

import java.util.ArrayList;

public class Item extends Node {
    private Types type;
    private String attributeValues;
    private String tag;
    private String name;
    private String description;

    public Item(String attributeValues) {
        this.attributeValues = attributeValues;
    }

    public Item(Types type,String name,String tag, String description) {
        this.type=type;
        this.name = name;
        this.tag = tag;
        this.description = description;
    }

    public Item() {

    }

    public String getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(String attributeValues) {
        this.attributeValues = attributeValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
