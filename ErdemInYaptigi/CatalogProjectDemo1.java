package ErdemInYaptigi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CatalogProjectDemo1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CatalogProjectDemo1.class.getResource("Scene1.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load(),800, 600);

        FXMLLoader typeLoader = new FXMLLoader(CatalogProjectDemo1.class.getResource("Scene2.fxml"));
        Scene scene2 = new Scene(typeLoader.load(),800,600);

        Controller1 controller = fxmlLoader.getController();
        Controller2 typeController = typeLoader.getController();

        controller.setController2(typeController);
        typeController.setController1(controller);




        stage.setTitle("THE CATALOG PROJECT");
        stage.setScene(scene2);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}