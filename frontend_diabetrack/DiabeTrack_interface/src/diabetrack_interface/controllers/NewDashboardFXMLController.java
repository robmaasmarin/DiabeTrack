/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Idioma;
import diabetrack_interface.models.Usuario;
import diabetrack_interface.session.CurrentUser;

import diabetrack_interface.utils.Navigator;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class NewDashboardFXMLController implements Initializable {

    @FXML
    private HBox topBar;

    @FXML
    private ImageView imgLogo;

    @FXML
    private Region regionSeparar;

    @FXML
    private ImageView profileImg;
    @FXML
    private VBox VBoxContainerCentro;

    @FXML
    private LineChart<String, Number> graficaChart;

    @FXML
    private GridPane gridActions;

    @FXML
    private VBox vBoxRegistro;

    @FXML
    private ImageView imgRegister;

    @FXML
    private Label labelRegister;

    @FXML
    private VBox vBoxCalculator;

    @FXML
    private ImageView imgCalculator;

    @FXML
    private Label labelBolo;

    @FXML
    private VBox vBoxInformes;

    @FXML
    private ImageView imgInformes;

    @FXML
    private Label labelInformes;

    @FXML
    private VBox vBoxProfile;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label labelProfile;
    @FXML
    private VBox vBoxLatestEntrie;

    @FXML
    private Label labelHelp, labelGraficaVacia;

    @FXML
    private Button btnContact;
    @FXML
    private Button logOut, btnElegirFoto;
    @FXML
    private Button btnAdminPanel;

    private File fotoSeleccionada = null;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //gráfica oculta por defecto
        graficaChart.setVisible(false);
        labelGraficaVacia.setVisible(false);
        // estiramos imagen barra superior al maximizar
        //logoHeader.fitWidthProperty().bind(stackContainer.widthProperty());
        //hacemos los links vbox de las opciones cliqueables

        // dimensiones foto perfil
        profileImg.setPreserveRatio(true);
        profileImg.setFitWidth(80);
        profileImg.setFitHeight(80);
        profileImg.setSmooth(true);
        profileImg.setCache(true);
        //hacemos que la imagen de perfil sea circular
        Circle perfilClip = new Circle(40, 40, 40);
        profileImg.setClip(perfilClip);

        vBoxRegistro.setOnMouseEntered(e -> {
            vBoxRegistro.setCursor(Cursor.HAND);

            vBoxRegistro.setScaleX(1.1);
            vBoxRegistro.setScaleY(1.1);
        });
        vBoxRegistro.setOnMouseExited(e -> {
            vBoxRegistro.setCursor(Cursor.DEFAULT);
            vBoxRegistro.setScaleX(1.0); // vuelve al tamaño normal
            vBoxRegistro.setScaleY(1.0);
        });
        vBoxRegistro.setOnMouseClicked(e -> {
            Stage stage = Navigator.getStageFrom(vBoxRegistro);
            Navigator.fadeTo(vBoxRegistro, "/diabetrack_interface/fxml/RegistrarEntradaFXML.fxml");
            //centramos pantalla en monitor
            Navigator.centerOnScreen(stage);

        });
        //acceso a cálculo de bolo
        vBoxCalculator.setOnMouseEntered(e -> {
            vBoxCalculator.setCursor(Cursor.HAND);

            vBoxCalculator.setScaleX(1.1);
            vBoxCalculator.setScaleY(1.1);
        });
        vBoxCalculator.setOnMouseExited(e -> {
            vBoxCalculator.setCursor(Cursor.DEFAULT);
            vBoxCalculator.setScaleX(1.0); // vuelve al tamaño normal
            vBoxCalculator.setScaleY(1.0);
        });
        vBoxCalculator.setOnMouseClicked(e -> {
            Stage stage = Navigator.getStageFrom(vBoxCalculator);
            Navigator.fadeTo(vBoxCalculator, "/diabetrack_interface/fxml/CalculoBoloFXML.fxml");
            Navigator.centerOnScreen(stage);
        });

        //acceso a informes
        vBoxInformes.setOnMouseEntered(e -> {
            vBoxInformes.setCursor(Cursor.HAND);

            vBoxInformes.setScaleX(1.1);
            vBoxInformes.setScaleY(1.1);
        });
        vBoxInformes.setOnMouseExited(e -> {
            vBoxInformes.setCursor(Cursor.DEFAULT);
            vBoxInformes.setScaleX(1.0); // vuelve al tamaño normal
            vBoxInformes.setScaleY(1.0);
        });
        vBoxInformes.setOnMouseClicked(e -> {
            Stage stage = Navigator.getStageFrom(vBoxInformes);
            Navigator.fadeTo(vBoxInformes, "/diabetrack_interface/fxml/InformesFXML.fxml");
            Navigator.centerOnScreen(stage);
        });

        // acceso al perfil
        vBoxProfile.setOnMouseEntered(e -> {
            vBoxProfile.setCursor(Cursor.HAND);

            vBoxProfile.setScaleX(1.1);
            vBoxProfile.setScaleY(1.1);
        });
        vBoxProfile.setOnMouseExited(e -> {
            vBoxProfile.setCursor(Cursor.DEFAULT);
            vBoxProfile.setScaleX(1.0); // vuelve al tamaño normal
            vBoxProfile.setScaleY(1.0);
        });
        vBoxProfile.setOnMouseClicked(e -> {
            Stage stage = Navigator.getStageFrom(vBoxProfile);
            Navigator.fadeTo(vBoxProfile, "/diabetrack_interface/fxml/PerfilUsuarioFXML.fxml");
            Navigator.centerOnScreen(stage);
        });

        cargarFotoUsuario();
        // cargar gráfica niveles de azúcar
        cargarGrafica();
        // botón funciones de admin
        Usuario u = CurrentUser.get();
        System.out.println("Foto perfil en el FRONT: " + u.getFotoPerfil());

        if (u.getRol().getNombre().equals("admin")) {
            btnAdminPanel.setVisible(true);

        } else {
            btnAdminPanel.setVisible(false);
        }
        System.out.println("Clase de CurrentUser: " + CurrentUser.get().getClass());

        btnAdminPanel.setOnMouseClicked(e -> {
            Stage stage = Navigator.getStageFrom(btnAdminPanel);
            Navigator.fadeTo(btnAdminPanel,"/diabetrack_interface/fxml/AdminUsuariosFXML.fxml");
            Navigator.centerOnScreen(stage);
        });
        //botón cierre sesión
        logOut.setOnAction(e -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(Navigator.getStageFrom(topBar));
            alert.setTitle("Cerrar sesión");
            alert.setHeaderText("Confirmación requerida");
            alert.setContentText("¿Seguro que deseas cerrar sesión?");

            ButtonType btnSi = new ButtonType("Sí");
            ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(btnSi, btnNo);

            // Mostrar diálogo y esperar respuesta
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == btnSi) {
                // limpiar usuario
                CurrentUser.clear();

                // volver al login
                Stage stage = Navigator.getStageFrom(vBoxRegistro);
                Navigator.fadeTo(vBoxRegistro, "/diabetrack_interface/fxml/LoginFXML.fxml");
                Navigator.centerOnScreen(stage);

            }
        });

    }

    // método para cargar imagen de perfil
    private void cargarFotoUsuario() {
        try {
            Usuario u = CurrentUser.get();
            if (u == null) {
                return;
            }

            if (u.getFotoPerfil() == null || u.getFotoPerfil().isEmpty()) {
                // Foto por defecto
                Image defaultImg = new Image(
                        getClass().getResourceAsStream("/diabetrack_interface/img/defaultuser.png")
                );
                profileImg.setImage(defaultImg);
                return;
            }

            Long id = u.getIdUsuario();
            String url = "http://localhost:8080/api/usuarios/usuario/" + id + "/foto";

            Image foto = new Image(url, true);

            profileImg.setImage(foto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarGrafica() {

        long idUsuario = CurrentUser.get().getIdUsuario();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/registros-comidas/usuario/" + idUsuario + "/ultimos5"))
                .GET()
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 200) {
                        procesarGrafica(response.body());
                    }
                })
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    private void procesarGrafica(String json) {
        try {
            JSONArray arr = new JSONArray(json);

            // en caso de usuarios nuevos sin registros
            if (arr.length() == 0) {
                Platform.runLater(() -> {
                    labelGraficaVacia.setVisible(true);
                    graficaChart.setVisible(false);
                });
                return;
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Glucosa (mg/dL)");

            for (int i = 0; i < arr.length(); i++) {
                JSONObject r = arr.getJSONObject(i);

                String fecha = r.optString("fechaHora", "sin fecha");
                double glucosa = r.optDouble("glucosaAntes", 0);

                // fecha recibida: 2025-11-29T08:50:33
                // formateo de hora
                String original = r.optString("fechaHora", "");

                LocalDateTime dt = LocalDateTime.parse(original);
                String etiqueta = dt.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));

                series.getData().add(new XYChart.Data<>(etiqueta, glucosa));
            }

            Platform.runLater(() -> {
                labelGraficaVacia.setVisible(false);
                graficaChart.setVisible(true);
                graficaChart.getData().clear();
                graficaChart.getData().add(series);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
