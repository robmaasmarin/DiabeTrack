/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Usuario;
import diabetrack_interface.utils.Navigator;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class AdminUsuariosController {

    @FXML
    private TableView<Usuario> tablaUsuarios;
    @FXML
    private TableColumn<Usuario, Long> colId;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colEmail;
    @FXML
    private TableColumn<Usuario, Void> colAcciones;

    private final HttpClient http = HttpClient.newHttpClient();

    // iniciamos automáticamente el controlador 
    @FXML
    public void initialize() {

        configurarColumnas();
        cargarUsuarios();
        configurarColumnaAcciones();
    }

    // traemos lista de usuarios NO admin desde backend
    private void cargarUsuarios() {

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/usuarios/no-admin"))
                .GET()
                .build();

        http.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenAccept(resp -> {
                    System.out.println("RESPUESTA CRUD: " + resp.body());
                    System.out.println("STATUS CODE: " + resp.statusCode());

                    if (resp.statusCode() == 200) {
                        // conversión json en objetos usuario
                        JSONArray arr = new JSONArray(resp.body());
                        List<Usuario> lista = new ArrayList<>();

                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject o = arr.getJSONObject(i);

                            Usuario u = new Usuario();
                            u.setIdUsuario(o.getLong("idUsuario"));
                            u.setNombre(o.getString("nombre"));
                            u.setEmail(o.getString("email"));
                            lista.add(u);
                        }

                        Platform.runLater(() -> {
                            tablaUsuarios.setItems(FXCollections.observableArrayList(lista));
                        });
                    }
                });
    }

    // creación dinámica del botón en eliminar en cada columna
    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Eliminar");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                // no mostramos botón si la lista está vacía
                if (empty) {
                    setGraphic(null);
                    return;
                }

                btn.setOnAction(e -> eliminarUsuario(getTableView().getItems().get(getIndex())));
                setGraphic(btn);
            }
        });
    }

    private void eliminarUsuario(Usuario u) {

        //  alerta de confirmación
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.initOwner(Navigator.getStageFrom(tablaUsuarios));
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText("¿Seguro que quieres eliminar este usuario?");
        confirm.setContentText(
                "ID: " + u.getIdUsuario() + "\n"
                + "Nombre: " + u.getNombre() + "\n"
                + "Email: " + u.getEmail()
        );

        // espera espuesta del usuario
        var result = confirm.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;  // Cancelado
        }

        // si se confirma, envío de delete al backend
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/usuarios/" + u.getIdUsuario()))
                .DELETE()
                .build();

        http.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenAccept(resp -> {

                    Platform.runLater(() -> {

                        if (resp.statusCode() == 200 || resp.statusCode() == 204) {
                            tablaUsuarios.getItems().remove(u);

                            // mostramos alerta de éxito
                            Alert ok = new Alert(Alert.AlertType.INFORMATION);
                            ok.initOwner(Navigator.getStageFrom(tablaUsuarios));
                            ok.setTitle("Eliminado");
                            ok.setHeaderText(null);
                            ok.setContentText("El usuario ha sido eliminado correctamente.");
                            ok.show();
                        } else {
                            // error eliminación
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.initOwner(Navigator.getStageFrom(tablaUsuarios));
                            error.setTitle("Error");
                            error.setHeaderText("No se pudo eliminar el usuario");
                            error.setContentText("Código: " + resp.statusCode());
                            error.show();
                        }
                    });
                });
    }

    // asociación de columnas a los atributos del user
    private void configurarColumnas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    // botón para volver al dashboard
    @FXML
    private void volver(ActionEvent e) {
        Stage stage = Navigator.getStageFrom(tablaUsuarios);
        Navigator.fadeTo(tablaUsuarios, "/diabetrack_interface/fxml/NewDashboardFXML.fxml");
       
    }
}
