/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Usuario;
import diabetrack_interface.session.CurrentUser;
import diabetrack_interface.utils.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URI;
import java.net.http.*;
import java.time.LocalDate;
import javafx.application.Platform;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class PerfilUsuarioController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker dateNacimiento;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtAltura;
    @FXML
    private TextField txtTipoInsulina;
    @FXML
    private TextField txtMarcaInsulina;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVolver;

// cliente HTTP reutilizable
    private final HttpClient http = HttpClient.newHttpClient();

    @FXML
    public void initialize() {
        // carga datos user logado
        cargarDatos();
        // guardar cambios en perfil
        btnGuardar.setOnAction(e -> guardarCambios());
        // botón regreso al dashboard
        btnVolver.setOnAction(e -> Navigator.goToDashboard(btnVolver));

    }

    private void cargarDatos() {
        Usuario u = CurrentUser.get();

        txtNombre.setText(u.getNombre());
        txtApellido.setText(u.getApellido());
        txtEmail.setText(u.getEmail());
        //conversión a localdate
        dateNacimiento.setValue(LocalDate.parse(u.getFecha_nacimiento()));
        txtPeso.setText(String.valueOf(u.getPeso()));
        txtAltura.setText(String.valueOf(u.getAltura()));
        txtTipoInsulina.setText(u.getTipo_insulina());
        txtMarcaInsulina.setText(u.getMarca_insulina());
    }

    // método para enviar modificaciones hechas al backend
    private void guardarCambios() {
        try {
            long id = CurrentUser.get().getIdUsuario();
            // conversión en JSON de datos modificados
            String json = """
            {
                "peso": %s,
                "altura": %s,
                "tipo_insulina": "%s",
                "marca_insulina": "%s"
            }
            """.formatted(
                    txtPeso.getText(),
                    txtAltura.getText(),
                    txtTipoInsulina.getText(),
                    txtMarcaInsulina.getText()
            );

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/usuarios/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            http.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(resp -> {

                        Platform.runLater(() -> {

                            if (resp.statusCode() == 200) {
                                // actualizamos sesión local
                                Usuario updated = CurrentUser.get();
                                updated.setPeso(Double.valueOf(txtPeso.getText()));
                                updated.setAltura(Double.valueOf(txtAltura.getText()));
                                updated.setTipo_insulina(txtTipoInsulina.getText());
                                updated.setMarca_insulina(txtMarcaInsulina.getText());

                                mostrarAlerta("Éxito", "Perfil actualizado correctamente.");

                                // volvemos al dashboard
                                Navigator.goToDashboard(btnGuardar);

                            } else {
                                mostrarAlerta("Error", "No se pudieron guardar los cambios.");
                            }

                        });

                    });

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error inesperado al guardar.");
        }
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(Navigator.getStageFrom(txtNombre));
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
