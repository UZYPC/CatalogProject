package sample;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CatalogControllerTest{


    @BeforeEach
    void setUp() throws IllegalStateException {
        Thread t =new Thread("") {
            public void run() {
                Application.launch(Main.class);
            }
        };
        t.start();

    }

    @AfterEach
    void tearDown() {
        Types.typesArrayList.clear();
        Tags.tagsArrayList.clear();
        Items.itemsArrayList.clear();


    }
    @Test
    void createType() {
        ArrayList<String> typeAttributes = new ArrayList<>();
        typeAttributes.add("Game Company");
        typeAttributes.add("Game Type");
        typeAttributes.add("Platform");
        Types type = new Types("Games",typeAttributes);
        type.setTypeAttrNameCount(typeAttributes.size());
        type.getTypesTitledPane().setText(type.getTypeName());
        VBox typeTitledPaneVbox = new VBox();
        typeTitledPaneVbox.getChildren().addAll(type.getTypesTitledPane());
        Types.typesArrayList.add(type);

        assertEquals(1,Types.typesArrayList.size());
        assertEquals("Games",Types.typesArrayList.get(0).getTypeName());
        assertEquals(3,Types.typesArrayList.get(0).getTypeAttrNameArrayList().size());
        assertEquals(1,typeTitledPaneVbox.getChildren().size());

    }
    @Test
    void createItem() {
        ArrayList<String> typeAttributes = new ArrayList<>();
        typeAttributes.add("Game Company");
        typeAttributes.add("Game Type");
        typeAttributes.add("Platform");
        Types type = new Types("Games",typeAttributes);
        Types.typesArrayList.add(type);
        ArrayList<String> itemAttributes = new ArrayList<>();
        itemAttributes.add("Ubisoft");
        itemAttributes.add("MMO");
        itemAttributes.add("PC");
        Items items = new Items(type,"Growtopia",itemAttributes);
        Items.itemsArrayList.add(items);
        type.getTypesItems().add(items);
        type.getTypesItemsListView().getItems().add(items.getItemName());


        assertEquals(1,Items.itemsArrayList.size());
        assertEquals(1,type.getTypesItems().size());
        assertEquals(1,Types.typesArrayList.get(0).getTypesItems().size());//to check also from the typeslist.
        assertEquals("Growtopia",type.getTypesItems().get(0).getItemName());
        assertEquals(type.getTypeAttrNameArrayList().size(),Types.typesArrayList.get(0).getTypesItems().get(0).getItemsAttributeValueList().size());
        assertEquals(1,type.getTypesItemsListView().getItems().size());
    }
    @Test
    void tagItem() {
        Items item = new Items(new Types(""),"Spiderman",new ArrayList<>());
        Tags tag = new Tags("Favourites");
        tag.getTagsItems().add(item);
        item.getItemTag().add(tag);
        tag.getTagsItemsListView().getItems().add(item.getItemName());
        assertTrue(tag.getTagsItems().contains(item));
        assertEquals(1,item.getItemTag().size());
        assertEquals(1,tag.getTagsItems().size());
        assertEquals(1,tag.getTagsItemsListView().getItems().size());
    }

    @Test
    void unTagItem() {
        Items item = new Items(new Types(""),"Spiderman",new ArrayList<>());
        Tags tag = new Tags("Favourites");
        tag.getTagsItems().add(item);
        item.getItemTag().add(tag);
        tag.getTagsItemsListView().getItems().add(item.getItemName());
        tag.getTagsItems().remove(item);
        item.getItemTag().remove(tag);
        tag.getTagsItemsListView().getItems().remove(item.getItemName());
        assertFalse(tag.getTagsItems().contains(item));
        assertEquals(0,item.getItemTag().size());
        assertEquals(0,tag.getTagsItems().size());
        assertEquals(0,tag.getTagsItemsListView().getItems().size());


    }
    @Test
    void deleteTag() {
        Tags tag = new Tags("Favourites");
        Tags.tagsArrayList.add(tag);//tag created

        Tags.tagsArrayList.remove(tag);

        assertFalse(Tags.tagsArrayList.contains(tag));
    }

    @Test
    void deleteSelectedItem() {
        Items items = new Items(new Types(""),"Dracula",new ArrayList<>());
        Items.itemsArrayList.add(items);

        Items.itemsArrayList.remove(items);

        assertFalse(Items.itemsArrayList.contains(items));
    }

    @Test
    void deleteSelectedItemsAttrValue() {
        ArrayList<String> typeAttributes = new ArrayList<>();
        typeAttributes.add("Bram Stoker");
        typeAttributes.add("2");
        typeAttributes.add("2003");
        Items items = new Items(new Types(""),"Dracula",typeAttributes);
        assertEquals(3,items.getItemsAttributeValueList().size());
        assertTrue(items.getItemsAttributeValueList().contains("2"));

        items.getItemsAttributeValueList().remove(1);

        assertEquals(2,items.getItemsAttributeValueList().size());
        assertFalse(items.getItemsAttributeValueList().contains("2"));


    }

    @Test
    void editSelectedItemsAttrValue() {
        ArrayList<String> itemAttributes = new ArrayList<>();
        itemAttributes.add("Bram Stoker");
        itemAttributes.add("2");
        itemAttributes.add("2003");
        Items items = new Items(new Types(""),"Dracula",itemAttributes);
        assertEquals(3,items.getItemsAttributeValueList().size());
        assertTrue(items.getItemsAttributeValueList().contains("2003"));

        items.getItemsAttributeValueList().set(2,"1994");

        assertEquals(3,items.getItemsAttributeValueList().size());
        assertTrue(items.getItemsAttributeValueList().contains("1994"));
        assertFalse(items.getItemsAttributeValueList().contains("2003"));

    }

    @Test
    void deleteSelectedType() {
        Types type = new Types("Movies",new ArrayList<>());
        Types.typesArrayList.add(type);

        Types.typesArrayList.remove(type);

        assertFalse(Types.typesArrayList.contains(type));
    }

    @Test
    void deleteSelectedTypeAttrName() {
        ArrayList<String> typeAttributes = new ArrayList<>();
        typeAttributes.add("Movie Full Name");
        typeAttributes.add("Genre");
        typeAttributes.add("Country");
        typeAttributes.add("Release Year");
        typeAttributes.add("Favourite Character");

        Types type =new Types("Movie",typeAttributes);
        assertEquals(5,type.getTypeAttrNameArrayList().size());
        assertTrue(type.getTypeAttrNameArrayList().contains("Genre"));

        type.getTypeAttrNameArrayList().remove(1);

        assertEquals(4,type.getTypeAttrNameArrayList().size());
        assertFalse(type.getTypeAttrNameArrayList().contains("Genre"));
    }

    @Test
    void editSelectedTypesAttrNames() {
        ArrayList<String> typeAttributes = new ArrayList<>();
        typeAttributes.add("Movie Full Name");
        typeAttributes.add("Genre");
        typeAttributes.add("Country");
        typeAttributes.add("Release Year");
        typeAttributes.add("Favourite Character");

        Types type =new Types("Movie",typeAttributes);
        assertEquals(5,type.getTypeAttrNameArrayList().size());
        assertTrue(type.getTypeAttrNameArrayList().contains("Favourite Character"));

        type.getTypeAttrNameArrayList().set(4,"Main Character");

        assertEquals(5,type.getTypeAttrNameArrayList().size());
        assertFalse(type.getTypeAttrNameArrayList().contains("Favourite Character"));
        assertTrue(type.getTypeAttrNameArrayList().contains("Main Character"));
    }

    @Test
    void addAttrNameToSelectedType() {
        ArrayList<String> typeAttributes = new ArrayList<>();
        typeAttributes.add("Movie Full Name");
        typeAttributes.add("Genre");
        typeAttributes.add("Country");
        typeAttributes.add("Release Year");
        typeAttributes.add("Main Character");

        Types type =new Types("Movie",typeAttributes);
        assertEquals(5,type.getTypeAttrNameArrayList().size());
        assertEquals(type.getTypeAttrNameArrayList().get(3), "Release Year");

        type.getTypeAttrNameArrayList().add(3,"Starring Actor/Actress");
        assertEquals(6,type.getTypeAttrNameArrayList().size());
        assertTrue(type.getTypeAttrNameArrayList().contains("Release Year"));
        assertNotEquals(type.getTypeAttrNameArrayList().get(3), "Release Year");
        assertEquals(type.getTypeAttrNameArrayList().get(3), "Starring Actor/Actress");
    }
}