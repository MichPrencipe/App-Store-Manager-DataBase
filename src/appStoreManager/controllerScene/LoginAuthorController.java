package appStoreManager.controllerScene;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import appStoreManager.model.AppStoreManagerModel;
import appStoreManager.model.AppStoreManagerModelImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import launcher.FXMLPath;

public class LoginAuthorController {

    @FXML
    TextField id;

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Pane rootPane;

    private AppStoreManagerModel model = new AppStoreManagerModelImpl();

    @FXML
    void pressEnter(ActionEvent event) throws IOException, SQLException {
        ResultSet r = this.model.loginAuthorQuery(Arrays.asList(id.getText(), username.getText(), password.getText()));
        if (r.next()) {
            Parent root;
            this.rootPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPath.AUTHOR.getPath()));
            root = loader.load();
            AuthorController controller = loader.getController();
            controller.setIdAutore(id.getText());
            this.rootPane.getChildren().add(root);
        } else {
            System.out.println("Invalid Credentials");
        }
    }

    @FXML
    void pressIndietro(ActionEvent event) throws IOException {
        this.rootPane.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.LAUNCHER.getPath()));
        this.rootPane.getChildren().add(root);
    }

}
