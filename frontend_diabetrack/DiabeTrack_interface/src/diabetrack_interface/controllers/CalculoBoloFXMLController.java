/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Alimento;
import diabetrack_interface.models.SeleccionAlimento;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javafx.application.Platform;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class CalculoBoloFXMLController implements Initializable {

    @FXML
    private HBox topBar;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Region regionSeparar;
    @FXML
    private HBox comboHbox;
    @FXML
    private ComboBox<?> choiceLanguage;
    @FXML
    private ImageView profileImg;
    @FXML
    private VBox vBoxCalculo;
    @FXML
    private HBox hBoxGlucosa;
    @FXML
    private Label labelGlucosa;
    @FXML
    private TextField txtGlucosaActual;
    @FXML
    private ComboBox<Alimento> comboAlimentos;

    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnAgregar;
    @FXML
    private StackPane stackContact;
    @FXML
    private Label labelHelp;
    @FXML
    private Button btnContact;
    @FXML
    private TableView<SeleccionAlimento> tableSeleccion;
    @FXML
    private TableColumn<SeleccionAlimento, String> colNombre;
    @FXML
    private TableColumn<SeleccionAlimento, Double> colCantidad;
    @FXML
    private TableColumn<SeleccionAlimento, Double> colCarbs;

    @FXML
    private Label labelTotalCarbs;
    @FXML
    private TextField txtInsulinRatio;
    @FXML
    private Button btnCalcularBolo;
    @FXML
    private Label labelDosis;

    /**
     * Initializes the controller class.
     */
    private final ObservableList<Alimento> alimentos = FXCollections.observableArrayList();
    private final ObservableList<SeleccionAlimento> seleccion = FXCollections.observableArrayList();

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializar tabla
        colNombre.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getAlimento().getNombre()));
        colCantidad.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCantidad()));
        colCarbs.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCarbs()));
// inicializo la tabla con placeholder
    /*Label placeholder = new Label("Agrega alimentos para comenzar");
    placeholder.getStyleClass().add("placeholderLabel"); 
    tableSeleccion.setPlaceholder(placeholder);*/
    
    // Placeholder con imagen
    /*ImageView placeholderImage = new ImageView(new Image(getClass().getResourceAsStream("/diabetrack_interface/resources/images/calculoback.PNG")));
    placeholderImage.setFitWidth(150);
    placeholderImage.setFitHeight(150);
    placeholderImage.setPreserveRatio(true);*/

    tableSeleccion.getStyleClass().add("table-view");

    
    // conexión de la tabla con lista
    
        tableSeleccion.setItems(seleccion);

        // listeners botones
        btnAgregar.setOnAction(e -> onAgregar());
        btnCalcularBolo.setOnAction(e -> onCalcularBolo());

        // cargar alimentos desde backend
        cargarAlimentosDesdeBackend();
    }

    private void cargarAlimentosDesdeBackend() {
        String url = "http://localhost:8080/api/alimentos"; // o el endpoint combinado que prefieras

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();

        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::procesarRespuestaAlimentos)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }

    private void procesarRespuestaAlimentos(String json) {
        try {
            JSONArray arr = new JSONArray(json);
            List<Alimento> list = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                long id = o.has("idAlimento") ? o.getLong("idAlimento")
                        : o.has("id_alimento") ? o.getLong("id_alimento") : 0;
                String nombre = o.optString("nombre", "sin nombre");
                double carbs = o.has("carbohidratos") ? o.getDouble("carbohidratos") : 0.0;
                double racion = o.has("racion") ? o.getDouble("racion") : 100.0;
                list.add(new Alimento(id, nombre, carbs, racion));
            }
            Platform.runLater(() -> {
                alimentos.setAll(list);
                comboAlimentos.getItems().setAll(alimentos);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void onAgregar() {
        Alimento seleccionado = comboAlimentos.getValue();
        if (seleccionado == null) {
            showAlert("Selecciona un alimento.");
            return;
        }
        double cantidad;
        try {
            cantidad = Double.parseDouble(txtCantidad.getText());
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            showAlert("Introduce una cantidad válida en gramos.");
            return;
        }

        // añadir a la lista
        SeleccionAlimento s = new SeleccionAlimento(seleccionado, cantidad);
        seleccion.add(s);
        actualizarTotales();
        // limpiar cantidad (opcional)
        txtCantidad.clear();
    }

    private void actualizarTotales() {
        double totalCarbs = seleccion.stream().mapToDouble(SeleccionAlimento::getCarbs).sum();
        labelTotalCarbs.setText(String.format(Locale.US, "%.1f", totalCarbs));
    }

    private void onCalcularBolo() {
        double totalCarbs = seleccion.stream().mapToDouble(SeleccionAlimento::getCarbs).sum();
        double ratio;
        try {
            String txt = txtInsulinRatio.getText();
            ratio = txt == null || txt.isBlank() ? 10.0 : Double.parseDouble(txt); // default 10 g/UD
            if (ratio <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            showAlert("Introduce un ratio válido (g de CH por unidad de insulina). Ej: 10");
            return;
        }

        double dosis = totalCarbs / ratio;
        labelDosis.setText(String.format(Locale.US, "%.2f", dosis));
    }

    private void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
            a.showAndWait();
        });
    }
}
