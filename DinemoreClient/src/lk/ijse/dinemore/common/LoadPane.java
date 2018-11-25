package lk.ijse.dinemore.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoadPane {
    public static void loadPane(Class cls,String path , AnchorPane pane){
        try {
            AnchorPane root = FXMLLoader.load(cls.getResource(path));
            pane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
