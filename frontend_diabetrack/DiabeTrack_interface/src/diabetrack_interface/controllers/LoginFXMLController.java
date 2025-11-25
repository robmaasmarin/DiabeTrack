/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Usuario;
import diabetrack_interface.session.CurrentUser;
import java.net.URI;
import java.net.URL;

import com.google.gson.Gson;
import java.io.IOException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class LoginFXMLController {

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

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailTextField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Rellena todos los campos");
            return;
        }

        try {
            // JSON para backend
            String jsonBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/auth/login"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {

                        if (response.statusCode() == 200) {

                            Gson gson = new Gson();
                            Usuario usuario = gson.fromJson(response.body(), Usuario.class);

                            // Guardamos sesión
                            CurrentUser.set(usuario);

                            // cambiar pantalla
                            Platform.runLater(() -> {
                                try {
                                    cambiarPantalla("/diabetrack_interface/fxml/NewDashboardFXML.fxml");
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });

                        } else {
                            Platform.runLater(()
                                    -> showAlert("Email o contraseña incorrectos")
                            );
                        }

                    })
                    .exceptionally(ex -> {
                        ex.printStackTrace();
                        Platform.runLater(() -> showAlert("Error al conectar con el servidor"));
                        return null;
                    });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ha ocurrido un error inesperado");
        }
    }

    private void showAlert(String message) {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private void cambiarPantalla(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
