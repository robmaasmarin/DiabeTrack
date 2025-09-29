/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private ImageView logoDBT;

    @FXML
    private Label bienvenidoLabel;

    @FXML
    private Label labelWelcome2;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label forgotpassLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Label newUserLabel;
    @FXML
    private Label signupLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLogin(javafx.event.ActionEvent event) {
        try {
            //temporal!!! cargamos pantalla principal al hacer clic en el boton
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/diabetrack_interface/fxml/NewDashboardFXML.fxml"));
            Parent dashboardRoot = loader.load();

            Scene dashboardScene = new Scene(dashboardRoot, 1200, 800);

            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("DiabeTrack - Dashboard");
            dashboardStage.setScene(dashboardScene);

            // (Opcional) icono
            dashboardStage.getIcons().add(
                    new javafx.scene.image.Image(getClass().getResourceAsStream("/diabetrack_interface/resources/images/moradotodo.png"))
            );

            dashboardStage.show();

            // cerrar el stage del login
            Stage loginStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            loginStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
