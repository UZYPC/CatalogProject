package sample;

public class Items {

    private Tags itemTag;
    private Types itemType;
    private String itemName;
    private String attributeValues;
    public Items(String attributeValues) {
        this.attributeValues = attributeValues;
    }

    public Items(Types itemType, String itemName, String attributeValues) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.attributeValues = attributeValues;
    }
    public Items(Tags itemTag, Types itemType, String itemName, String attributeValues) {
        this.itemTag = itemTag;
        this.itemType = itemType;
        this.itemName = itemName;
        this.attributeValues = attributeValues;
    }



    public Tags getItemTag() {
        return itemTag;
    }

    public void setItemTag(Tags itemTag) {
        this.itemTag = itemTag;
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


}
