//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ErdemInYaptigi;

import java.util.ArrayList;

public class Types {
    private String typeName;
    private String attributesNames;
    ArrayList<Item> items = new ArrayList<>();

    public Types(){

    }

    public Types(String attributesNames) {
        this.attributesNames = attributesNames;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAttributesNames() {
        return this.attributesNames;
    }

    public void setAttributesNames(String attributesNames) {
        this.attributesNames = attributesNames;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void removeItem(int index) {
        this.items.remove(index);
    }

    public void removeAllItems() {
        this.items.clear();
    }

    public Item getItem(int index) {
        return this.items.get(index);
    }

}
