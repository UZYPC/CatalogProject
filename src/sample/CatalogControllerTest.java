package sample;

import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;

class CatalogControllerTest extends ApplicationTest {

    final String tagItemsList = "#tagItemsListView";

    @Before
    public void setUpClass() throws Exception{
        ApplicationTest.launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

    @After
    public void afterEachTest() throws TimeoutException{
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }


    @Test
    public void afterItemAddingIsItInTheList(){
        CatalogController catalogController = new CatalogController();
//        catalogController.itemName.getText();
//        String itemName = catalogController.itemName.getText();
        clickOn("Create Item");

        FxAssert.verifyThat(tagItemsList,(ListView listview)->{
            String text =listview.getItems().toString();
                       return text.contains("asd");
        });



    }



}