package ErdemInYaptigi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TagsController {
    @FXML private AnchorPane tagsPage;
    @FXML private Button saveButton;


    public void saveTagButton() throws IOException {
        AnchorPane mainPage = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        tagsPage.getChildren().removeAll();
        tagsPage.getChildren().setAll(mainPage);


    }
}
