/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;


import diabetrack_interface.models.Alimento;
import diabetrack_interface.models.RegistroAlimentoItem;
import diabetrack_interface.models.SeleccionAlimento;
import diabetrack_interface.session.CurrentUser;
import diabetrack_interface.utils.Navigator;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class RegistrarEntradaFXMLController implements Initializable {
    @FXML
private TextField txtGlucosa;

@FXML
private ComboBox<Alimento> comboAlimentos;

@FXML
private TextField txtCantidad;

@FXML
private Button btnAñadirAlimento;

@FXML
private TableView<RegistroAlimentoItem> tablaAlimentos;

@FXML
private TableColumn<RegistroAlimentoItem, String> colNombre;

@FXML
private TableColumn<?, ?> colCantidad;

@FXML
private TableColumn<?, ?> colCarbs;

@FXML
private Label labelCarbsTotales;

@FXML
private Label labelBolo;

@FXML
private Button btnGuardar;
@FXML
private ListView<String> listaUltimos;

@FXML
    private Button btnVolver;
@FXML
private HBox topBar;

@FXML
private Region regionSeparar;

@FXML
private HBox comboHbox;

@FXML
private ImageView imgLogo;

@FXML
private ImageView profileImg;

@FXML
private StackPane stackContact;


    /**
     * Initializes the controller class.
     */
 private final HttpClient httpClient = HttpClient.newHttpClient();
 private final ObservableList<Alimento> alimentos = FXCollections.observableArrayList();
    private final ObservableList<SeleccionAlimento> seleccion = FXCollections.observableArrayList();
   private ObservableList<RegistroAlimentoItem> lista = FXCollections.observableArrayList();
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("ListaUltimos = " + listaUltimos);

            cargarUltimos5();

        
        colNombre.setCellValueFactory(data -> 
    new SimpleStringProperty(data.getValue().getNombre()));

colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
colCarbs.setCellValueFactory(new PropertyValueFactory<>("carbohidratosTotales"));


        tablaAlimentos.setItems(lista);

        // Cargar alimentos desde backend
        cargarAlimentosDesdeBackend();

        btnAñadirAlimento.setOnAction(e -> añadirAlimento());
        btnGuardar.setOnAction(e -> guardarRegistro());
        btnVolver.setOnAction(e -> Navigator.goToDashboard(btnVolver));
    }

    private void cargarAlimentosDesdeBackend() {
        String url = "http://localhost:8080/api/alimentos"; 

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
    //System.out.println("JSON recibido: " + json);
        try {
            JSONArray arr = new JSONArray(json);
            List<Alimento> list = new ArrayList<>();
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                long id = o.has("idAlimento") ? o.getLong("idAlimento")
                        : o.has("id_alimento") ? o.getLong("id_alimento") : 0;
                String nombre = o.optString("nombre", "sin nombre");
                
                double carbs = o.has("carbohidratos") ? o.getDouble("carbohidratos") : 0.0;
                //System.out.println("carbs parseados: " + carbs);
                double racion = o.has("racion") ? o.getDouble("racion") : 100.0;
               
                Alimento a = new Alimento(id, nombre, carbs, racion);
//System.out.println("Alimento creado: " + a.getNombre() + " -> " + a.getCarbohidratos());
list.add(a);

            }
            Platform.runLater(() -> {
                alimentos.setAll(list);
                comboAlimentos.getItems().setAll(alimentos);
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



private void añadirAlimento() {
    Alimento alimentoSeleccionado = comboAlimentos.getValue();
    //System.out.println("Seleccionado: " + alimentoSeleccionado.getNombre() + " -> " + alimentoSeleccionado.getCarbohidratos());
    if (alimentoSeleccionado == null) {
        showAlert("Selecciona un alimento primero");
        return;
    }

     double cantidad;
    try {
        cantidad = Double.parseDouble(txtCantidad.getText());
        if (cantidad <= 0) throw new NumberFormatException();
    } catch (NumberFormatException e) {
        showAlert("Introduce una cantidad válida en gramos");
        return;
    }
    // crear item con id + nombre + cantidad + carbs
RegistroAlimentoItem item = new RegistroAlimentoItem(
        alimentoSeleccionado.getIdAlimento(),
        alimentoSeleccionado.getNombre(),
        cantidad,
        alimentoSeleccionado.getCarbohidratos()
);

// añadir a la lista principal
lista.add(item);

// Mostrarlo en la tabla
//tablaAlimentos.getItems().add(item);

// Recalcular totales
double totalCarbs = lista.stream()
        .mapToDouble(RegistroAlimentoItem::getCarbohidratosTotales)
        .sum();

labelCarbsTotales.setText(String.format("%.1f g", totalCarbs));
   
    txtCantidad.clear();
    //  calcular bolo 
double glucosaAntes = 0.0;
try {
    glucosaAntes = Double.parseDouble(txtGlucosa.getText());
} catch (NumberFormatException e) {
    // si está vacío o mal escrito, no calcula
}

double ratioInsulina = 10.0;       // 1 unidad cubre 10 g de CH
double factorCorreccion = 50.0;    // 1 unidad baja 50 mg/dL
double glucosaObjetivo = 100.0;    // objetivo ideal

double boloCarbohidratos = totalCarbs / 10.0;
double boloCorreccion = (glucosaAntes - glucosaObjetivo) / 50.0;

double bolo = boloCarbohidratos + boloCorreccion;
if (bolo < 0) bolo = 0;

// redondeo a 0.5
bolo = redondear05(bolo);

labelBolo.setText(String.format("%.1f U", bolo));
}
//método para guardar registro en bbdd
private void guardarRegistro() {
    double glucosa;
    try {
        glucosa = Double.parseDouble(txtGlucosa.getText());
    } catch (NumberFormatException e) {
        showAlert("Introduce una glucosa válida");
        return;
    }
double boloCalculado = 0.0;

try {
    String texto = labelBolo.getText();

    if (texto == null || texto.isEmpty()) {
        boloCalculado = 0.0;
    } else {
        // limpieza completa
        texto = texto.replace(",", ".")     // cabio de coma por punto
                     .replaceAll("[^0-9.]", ""); // Eliminar todo menos números y puntos

        boloCalculado = Double.parseDouble(texto);
    }

} catch (Exception e) {
    System.out.println("Error parseando bolo: " + e.getMessage());
    boloCalculado = 0.0;
}

    // Preparación estructura JSON
    JSONArray alimentosArray = new JSONArray();
    for (RegistroAlimentoItem item : lista) {
        JSONObject alimentoObj = new JSONObject();
alimentoObj.put("idAlimento", item.getIdAlimento());

JSONObject o = new JSONObject();
o.put("alimento", alimentoObj);
o.put("cantidad", item.getCantidad());

alimentosArray.put(o);

    }

    JSONObject registroJson = new JSONObject();
    registroJson.put("glucosaAntes", glucosa);
    registroJson.put("carbohidratos", lista.stream().mapToDouble(RegistroAlimentoItem::getCarbohidratosTotales).sum());
    registroJson.put("usuario", new JSONObject().put("idUsuario", CurrentUser.get().getIdUsuario()));
    registroJson.put("alimentos", alimentosArray);
    //registroJson.put("fechaHora", LocalDateTime.now().toString());
    registroJson.put("fechaHora", LocalDateTime.now().format(formatter));
    registroJson.put("boloCalculado", boloCalculado);



    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8080/api/registros-comidas"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(registroJson.toString()))
            .build();

    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenAccept(response -> {
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                Platform.runLater(() -> {
                    showAlert("Registro guardado con éxito");

                    lista.clear();
                    seleccion.clear();
                    tablaAlimentos.getItems().clear();
                    labelCarbsTotales.setText("0 g");
                    labelBolo.setText("0 U");
                    txtCantidad.clear();
                    txtGlucosa.clear();
                });
            } else {
                Platform.runLater(() ->
                    showAlert("Error al guardar: " + response.statusCode())
                );
            }
        })
        .exceptionally(ex -> {
            ex.printStackTrace();
            Platform.runLater(() ->
                showAlert("Error al conectar con el servidor")
            );
            return null;
        });
}
    private void cargarUltimos5() {

    long idUsuario = CurrentUser.get().getIdUsuario();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/api/registros-comidas/usuario/" + idUsuario + "/ultimos5"))
        .GET()
        .build();

    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenAccept(response -> {
            if (response.statusCode() == 200) {
                Platform.runLater(() -> procesarUltimos5(response.body()));
            }
        })
        .exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
}
    private void procesarUltimos5(String json) {
    try {
        JSONArray arr = new JSONArray(json);
        Platform.runLater(() -> listaUltimos.getItems().clear());

        for (int i = 0; i < arr.length(); i++) {
            JSONObject r = arr.getJSONObject(i);

            String fecha = r.optString("fechaHora", "sin fecha");
            double glucosa = r.optDouble("glucosaAntes", 0);
            double carbs = r.optDouble("carbohidratos", 0);

            // === LEER ALIMENTOS ===
            JSONArray alimentos = r.optJSONArray("alimentos");

            StringBuilder alimentosTexto = new StringBuilder();

            if (alimentos != null) {
                for (int j = 0; j < alimentos.length(); j++) {
                    JSONObject a = alimentos.getJSONObject(j);
                    double cantidad = a.optDouble("cantidad", 0);

                    JSONObject info = a.optJSONObject("alimento");
                    String nombre = info != null ? info.optString("nombre", "???") : "???";

                    alimentosTexto.append("   - ")
                            .append(nombre)
                            .append(" (")
                            .append(cantidad)
                            .append(" g)\n");
                }
            }

            String linea = 
                "📅 " + fecha +
                "\n   Glucosa: " + glucosa +
                " | Carbohidratos: " + carbs + " g\n" +
                alimentosTexto;

            Platform.runLater(() -> listaUltimos.getItems().add(linea));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private double redondear05(double valor) {
    return Math.round(valor * 2.0) / 2.0;
}

     private void showAlert(String message) {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
