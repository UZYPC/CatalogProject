package sample;

public class HelpManual {
    private String text = "Welcome to the Catalog Program! Where you can create types ( such as Books, CDs, DVDs etc.), and stores your items within types you created before. \n" +
            "\n" +
            "Created types have type attributes and every item user creates has item attribute values related to items type. User can also tag their items to keep track of their items without respect to their types. The user can delete types, edit their attributes and delete attributes. The user can do the same things for created items except deleting attribute values (since items' attribute values are related to items type, this would cause a problem!). The user can search any type, item and tag. The user also can filter items with respect to their tags.\n" +
            "\n" +
            "\n" +
            "How to create a type?:\n" +
            "Click to the create type tab, or click to the Create menu from the menu bar, and select \"Create Type\" option. Once the user is in the page, user must enter a type name, and attribute names to the type. Every type must have at least one attribute name!. Add attribute names by clicking \"+\" icon next to the text field. Add attribute names one by one, or separating attributes with \",\"s. The user can delete attribute names by clicking \"-\" icon next to the text field. Once everything is done, click \"Create Type\" button and the type is created.\n" +
            "\n" +
            "How to delete/edit a type?\n" +
            "Click to the edit/delete type tab, or click to the Edit menu from the menu bar, and select \"Edit Type\" option. On the left side of the page, there is a list of existing types. The user can delete existing type by clicking the type on the list and clicking \"Delete Type\" button. On the right side of the page, the user can edit/delete attributes from type selected from the selection box. Once the type is selected, the list will show attribute names of the type. The user can edit an attribute by selecting an attribute from the list, then entering a new attribute name to the text field and clicking to the \"edit\" button. To delete an attribute, the user must select an attribute from the list,then click \"Delete Attribute\" button.\n" +
            "\n" +
            "\n" +
            "How to create an item?\n" +
            "Click to the create item tab, or click to the Create menu from the menu bar, and select \"Create Item\" option. On the left side of the page,first select a type, then enter a name for the item. Then attribute values by clicking \"+\" icon next to the text field. The user can delete attribute names by clicking \"-\" icon next to the text field. Once everything is done, click \"Create Item\" button and the item is created.\n" +
            "\n" +
            "How to create a tag?\n" +
            "\n" +
            "On the same page of creating items, there is a text field with a prompt text \"Create Tag\". Enter the name of the tag, then click \"+\" icon. \n" +
            "\n" +
            "How to delete a tag?\n" +
            "Below the creating tag area, there is a selection box. Select an existing tag, then click \"delete\" button.\n" +
            "\n" +
            "How to tag/untag an item?\n" +
            "\n" +
            "On the right side of the create item page, there is a selection box and a list of all created items. Select a tag from the selection box and select an item from the list, then click \"Tag Item\" to tag, \"Untag Item\" to untag an item.\n" +
            "\n" +
            "How to edit/delete an item?\n" +
            "Click to the edit/delete item tab, or click to the Edit menu from the menu bar, and select \"Edit Item\" option. Select the type of the from the selection box which is in the middle of the page. After selecting a type, on the left side of the page there is a list of selected types items. To delete an item, select an item from the list, then click \"Delete Item\" button. On the right side of the page, there is a selecting box containing items for selected type. After selecting an item from that box, the list below the selection box will list items attribute values. To edit an attribute value, \n" +
            "enter new attribute name to the text field and click to the \"edit\" button.\n" +
            "\n" +
            "How to print?\n"+
            "In menubar select print. Then choose print option\n"+
            "\n" +
            "How to search?\n" +
            "In the main page, type to the text field, and click magnifying glass icon to search. In the list below, you can see the results.\n" +
            "\n" +
            "How to filter items?\n" +
            "In the main page, select a tag from the selection box. In the list below, items with selected tag will be listed. You can select another tag and see items of that tag. If you click clear, list will be cleared.\n"+
            "\n" +
            "How to see item details?\n"+
            "Click to item from filter item list to see details of selected item.\n ";
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
