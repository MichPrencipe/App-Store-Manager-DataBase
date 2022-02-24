package launcher;

import java.io.IOException;
/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
*/

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

    private Stage primaryStage = new Stage();

    @Override
    public void start(final Stage primaryStage) throws IOException {
        this.primaryStage.setTitle("APP STORE MANAGER");
        this.primaryStage.setResizable(false);
        this.primaryStage.setHeight(600);
        this.primaryStage.setWidth(1000);
        showGame();
    }

    @FXML
    private void showGame() throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(FXMLPath.LAUNCHER.getPath())));
        root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
