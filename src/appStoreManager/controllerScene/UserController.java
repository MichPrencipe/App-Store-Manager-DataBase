package appStoreManager.controllerScene;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import appStoreManager.model.AppStoreManagerModel;
import appStoreManager.model.AppStoreManagerModelImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import launcher.FXMLPath;

public class UserController implements Initializable {
    @FXML
    private Pane rootUser;

    @FXML
    private TextField idRecensione;

    @FXML
    private TextField idVersione;

    @FXML
    private TextField valutazione;

    @FXML
    private TextField dataInserimento;

    @FXML
    private TextArea descrizione;

    @FXML
    private TextField idAutore;

    @FXML
    private TextField idDispositivo;

    @FXML
    private TextField dataDownload;

    @FXML
    private TextField idCategoria;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextArea textArea;

    private AppStoreManagerModel model = new AppStoreManagerModelImpl();
    private static final String ERROR = "INPUT ERROR";
    private String idUtente;

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    @FXML
    void pressAggiorna(ActionEvent event) {
        int rs = this.model.updateQuery(4, Arrays.asList(username.getText(), password.getText(), idUtente));
        if (rs == -1) {
            textArea.setText(ERROR);
        } else {
            textArea.setText("Righe modificate: " + rs);
        }
    }

    @FXML
    void pressIndietro(ActionEvent event) throws IOException {
        this.rootUser.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.LOGIN_UTENTE.getPath()));
        this.rootUser.getChildren().add(root);
    }

    @FXML
    void pressInserisci(ActionEvent event) {
        int rs = this.model.updateQuery(5, Arrays.asList(idRecensione.getText(), idUtente, idVersione.getText(),
                valutazione.getText(), dataInserimento.getText(), descrizione.getText()));
        if (rs == -1) {
            textArea.setText(ERROR);
        } else {
            textArea.setText("Righe modificate: " + rs);
        }
    }

    @FXML
    void pressVisualizzaAcquisti(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(7, Arrays.asList(idUtente));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @FXML
    void pressVisualizzaApp(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(4, Arrays.asList(idAutore.getText()));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @FXML
    void pressVisualizzaAppScaricate(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(5, Arrays.asList(idDispositivo.getText(), dataDownload.getText()));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @FXML
    void pressVisualizzaCronologia(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(8, Arrays.asList(idUtente));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @FXML
    void pressVisualizzaTendenze(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(6, Arrays.asList(idCategoria.getText()));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private String resultSetToString(ResultSet resultSet) throws SQLException {
        String string = "";
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    string = string + (",  ");
                String columnValue = resultSet.getString(i);
                string = string + rsmd.getColumnName(i) + ": " + columnValue;
            }
            string = string + ("\n");
        }
        return string;
    }
}
