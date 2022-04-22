package sample;

import java.util.ArrayList;

public class Types {
    private String typeName;
    private String typeAttributesNames;
    private ArrayList<Items> typesItems;

    static ArrayList<Types> typesArrayList;




    public  Types(String typeName,String typeAttributesNames){
        this.typeName=typeName;
        this.typeAttributesNames=typeAttributesNames;
    }
    public  Types(String typeAttributesNames){
        this.typeAttributesNames=typeAttributesNames;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeAttributesNames() {
        return typeAttributesNames;
    }

    public void setTypeAttributesNames(String typeAttributesNames) {
        this.typeAttributesNames = typeAttributesNames;
    }

    public ArrayList<Items> getTypesItems() {
        return typesItems;
    }

    public void setTypesItems(ArrayList<Items> typesItems) {
        this.typesItems = typesItems;
    }


    public ArrayList<Types> getTypesArrayList() {
        return typesArrayList;
    }

    public void setTypesArrayList(ArrayList<Types> typesArrayList) {
        this.typesArrayList = typesArrayList;
    }


}
