/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Alimento;
import diabetrack_interface.models.SeleccionAlimento;
import diabetrack_interface.utils.Navigator;

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
import javafx.beans.binding.Bindings;
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
    @FXML
    private Button btnVolver;

    /**
     * Initializes the controller class.
     */
    // lista observable con alimentos disponibles que traemos desde backend
    private final ObservableList<Alimento> alimentos = FXCollections.observableArrayList();
    // lista observable con alimentos que el user ha seleccionado para el cálculo
    private final ObservableList<SeleccionAlimento> seleccion = FXCollections.observableArrayList();
    // cliente API para consumir API de backend
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // inicializar tabla - configuración de columnas
        colNombre.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getAlimento().getNombre()));
        colCantidad.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCantidad()));
        colCarbs.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getCarbs()));
        
        // botón para volver al dashboard
        btnVolver.setOnAction(e -> Navigator.goToDashboard(btnVolver));
        // aplicación css a tabla
        tableSeleccion.getStyleClass().add("table-view");
            
        // conexión de la tabla con lista
        tableSeleccion.setItems(seleccion);

        // listeners botones
        btnAgregar.setOnAction(e -> onAgregar());
        btnCalcularBolo.setOnAction(e -> onCalcularBolo());

        // cargar alimentos desde backend
        cargarAlimentosDesdeBackend();
        
        // quito el mensaje por defecto de la tabla
        tableSeleccion.setPlaceholder(new Region());
        // ocultar tabla mientras no tenga productos
        tableSeleccion.visibleProperty().bind(Bindings.isEmpty(seleccion).not());
    }
    // solicitamos lista de alimentos disponibles
    private void cargarAlimentosDesdeBackend() {
        String url = "http://localhost:8080/api/alimentos"; // o el endpoint combinado que prefieras

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .build();
        // hacemos petición asíncrona al servidor
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::procesarRespuestaAlimentos)
                .exceptionally(ex -> {
                    ex.printStackTrace();
                    return null;
                });
    }
    // procesamos json recibido - conversión en alimento
    private void procesarRespuestaAlimentos(String json) {
        try {
            JSONArray arr = new JSONArray(json);
            List<Alimento> list = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                // ajuste ante posibles diferencias en nombres desde backend
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
    // añadimos alimento seleccionado a tabla
    private void onAgregar() {
        Alimento seleccionado = comboAlimentos.getValue();
        if (seleccionado == null) {
            showAlert("Selecciona un alimento.");
            return;
        }
        double cantidad;
        // validación cantidad
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
    // cálculo totales carbs
    private void actualizarTotales() {
        double totalCarbs = seleccion.stream().mapToDouble(SeleccionAlimento::getCarbs).sum();
        labelTotalCarbs.setText(String.format(Locale.US, "%.1f", totalCarbs));
    }
    // cálculo dosis
    private void onCalcularBolo() {

    // Total de carbohidratos ya calculado en actualizarTotales()
    double totalCarbs = seleccion.stream()
            .mapToDouble(SeleccionAlimento::getCarbs)
            .sum();

    // Glucosa actual
    double glucosaAntes;
    try {
        glucosaAntes = Double.parseDouble(txtGlucosaActual.getText());
    } catch (Exception e) {
        showAlert("Introduce una glucosa válida.");
        return;
    }

    // Ratio insulina-hidratos (por defecto 10 si vacío)
    double ratio;
    try {
        String txt = txtInsulinRatio.getText();
        ratio = (txt == null || txt.isBlank()) ? 10.0 : Double.parseDouble(txt);

        if (ratio <= 0) throw new NumberFormatException();

    } catch (NumberFormatException e) {
        showAlert("Introduce un ratio válido (ej. 10).");
        return;
    }

    // cálculo unificado
    double bolo = calcularBoloCompleto(totalCarbs, glucosaAntes, ratio);

    labelDosis.setText(String.format(Locale.US, "%.1f U", bolo));
}


    private void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert a = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
            a.initOwner(Navigator.getStageFrom(topBar));
            a.showAndWait();
        });
    }
    
    private double calcularBoloCompleto(double totalCarbs, double glucosaAntes, double ratioInsulina) {

    double glucosaObjetivo = 100.0;   
    double factorCorreccion = 50.0;   // 1 unidad baja 50 mg/dL

    // bolo por carbohidratos
    double boloCarbs = totalCarbs / ratioInsulina;

    // corrección por glucosa
    double boloCorreccion = (glucosaAntes - glucosaObjetivo) / factorCorreccion;

    double boloTotal = boloCarbs + boloCorreccion;

    if (boloTotal < 0) {
        boloTotal = 0;
    }

    // redondeo a 0.5 unidades
    boloTotal = Math.round(boloTotal * 2.0) / 2.0;

    return boloTotal;
}

}
