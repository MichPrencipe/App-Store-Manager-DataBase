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

public class AuthorController implements Initializable {

    @FXML
    private Pane rootAuthor;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField idApp;

    @FXML
    private TextField idSupporto;

    @FXML
    private TextField idRequisito;

    @FXML
    private TextField numeroDownload;

    @FXML
    private TextField verificata;

    @FXML
    private TextField dataVerifica;

    @FXML
    private TextField pagamentiInterni;

    @FXML
    private TextField pagamento;

    @FXML
    private TextField costo;

    @FXML
    private TextField nomeApp;

    @FXML
    private TextField dataRilascio;

    @FXML
    private TextField descrizione;

    @FXML
    private TextField idVersione;

    @FXML
    private TextField idAppVersione;

    @FXML
    private TextField numeroVersione;

    @FXML
    private TextField dataPubblicazione;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField idAppApplicazione;

    @FXML
    private TextField idVersioneBug;

    private AppStoreManagerModel model = new AppStoreManagerModelImpl();
    private static final String ERROR = "INPUT ERROR";
    private String idAutore;

    public void setIdAutore(String idAutore) {
        this.idAutore = idAutore;
    }

    @FXML
    void pressAggiorna(ActionEvent event) {
        int rs = this.model.updateQuery(3, Arrays.asList(username.getText(), password.getText(), idAutore));
        if (rs == -1) {
            textArea.setText(ERROR);
        } else {
            textArea.setText("Righe modificate: " + rs);
        }
    }

    @FXML
    void pressEtaMedia(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(3, Arrays.asList(idAppApplicazione.getText()));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @FXML
    void pressGuadagni(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(1, Arrays.asList(idAppApplicazione.getText()));
        try {
            textArea.setText(resultSetToString(rs));
        } catch (Exception e) {
            textArea.setText(ERROR);
        }
    }

    @FXML
    void pressIndietro(ActionEvent event) throws IOException {
        this.rootAuthor.getChildren().clear();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.LOGIN_AUTORE.getPath()));
        this.rootAuthor.getChildren().add(root);
    }

    @FXML
    void pressInserisciApp(ActionEvent event) {
        int rs = this.model.updateQuery(1,
                Arrays.asList(idApp.getText(), numeroDownload.getText(), idSupporto.getText(), idRequisito.getText(),
                        verificata.getText(), dataVerifica.getText(), pagamentiInterni.getText(), pagamento.getText(),
                        costo.getText(), nomeApp.getText(), dataRilascio.getText(), descrizione.getText(), idAutore));
        System.out.println(Arrays.asList(idApp.getText(), numeroDownload.getText(), idSupporto.getText(), idRequisito.getText(),
                verificata.getText(), dataVerifica.getText(), pagamentiInterni.getText(), pagamento.getText(),
                costo.getText(), nomeApp.getText(), dataRilascio.getText(), descrizione.getText(), idAutore));
        if (rs == -1) {
            textArea.setText(ERROR);
        } else {
            textArea.setText("Righe modificate: " + rs);
        }
    }

    @FXML
    void pressInserisciVersione(ActionEvent event) {
        int rs = this.model.updateQuery(2, Arrays.asList(idVersione.getText(), idAppVersione.getText(),
                numeroVersione.getText(), dataPubblicazione.getText()));
        if (rs == -1) {
            textArea.setText(ERROR);
        } else {
            textArea.setText("Righe modificate: " + rs);
        }
    }

    @FXML
    void pressVisualizza(ActionEvent event) {
        ResultSet rs = this.model.executeQuery(2, Arrays.asList(idVersioneBug.getText()));
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
