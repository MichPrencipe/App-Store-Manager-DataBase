package appStoreManager.controllerScene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import launcher.FXMLPath;

public class MainController {

    @FXML
    private Pane root;

    @FXML
    void pressAuthor(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.LOGIN_AUTORE.getPath()));
        this.root.getChildren().add(root);
    }

    @FXML
    void pressUser(ActionEvent event) throws IOException {
        this.root.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.LOGIN_UTENTE.getPath()));
        this.root.getChildren().add(root);
    }

}
