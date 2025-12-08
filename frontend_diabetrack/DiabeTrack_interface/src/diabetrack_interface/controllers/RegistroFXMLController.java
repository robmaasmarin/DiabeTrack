/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import com.google.gson.Gson;
import diabetrack_interface.dto.UsuarioDTO;
import diabetrack_interface.models.Usuario;
import diabetrack_interface.utils.Navigator;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class RegistroFXMLController implements Initializable {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab basicData;

    @FXML
    private AnchorPane anchorTab;

    @FXML
    private VBox vboxData;

    @FXML
    private ImageView logoRegistro;

    @FXML
    private Label labelRegistroNuevo;

    @FXML
    private Label labelRequisitos;
    @FXML
    private ImageView infoImg;
    @FXML
    private Button nextButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField passConf;

    @FXML
    private Tab medicalTab;

    @FXML
    private Tab prefTab;
    @FXML
    private AnchorPane anchorTab2;

    @FXML
    private VBox vboxData2;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTField;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField surnameTField;

    @FXML
    private Label fechaLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label genreLabel;
    @FXML
    private HBox hboxGenre;

    @FXML
    private RadioButton hombreRB;

    @FXML
    private RadioButton mujerRB;

    @FXML
    private GridPane gridPesoAltura, gridInsulina;

    @FXML
    private Label weightLabel;

    @FXML
    private TextField pesoTfield;

    @FXML
    private Label alturaLabel;

    @FXML
    private TextField alturaTfield;

    @FXML
    private Button createAccount, btnElegirFoto;

    @FXML
    private AnchorPane anchorTab3;

    @FXML
    private VBox vboxData3;
    @FXML
    private Label medicalLabel, tipoDLabel, yearLabel, labelProfilePicture, fotoStatus;
    @FXML
    private ComboBox tipoDCombo, yearCombo, insulinaMarcaCombo;
    @FXML
    private ImageView imagePerfil;
    private File fotoSeleccionada = null;

    private ToggleGroup sexoGroup;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabPane.getStylesheets().add(getClass().getResource("/diabetrack_interface/css/tabs.css").toExternalForm());

        // Peso restringido a solo números
        pesoTfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pesoTfield.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        // resto de campos con restricciones bien a string o a números
        alturaTfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                alturaTfield.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        nameTField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]*")) {
                nameTField.setText(newValue.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]", ""));
            }
        });
        surnameTField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]*")) {
                surnameTField.setText(newValue.replaceAll("[^a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]", ""));
            }
        });
        // combobox con últimos 40 años
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < 40; i++) {
            yearCombo.getItems().add(currentYear - i);
        }

        // seleccionar el primero por defecto
        yearCombo.getSelectionModel().selectFirst();
        // Toggle para elección de sexo
        sexoGroup = new ToggleGroup();
        hombreRB.setToggleGroup(sexoGroup);
        mujerRB.setToggleGroup(sexoGroup);

        // Opciones de combos
        tipoDCombo.getItems().addAll("Rápida", "Ultrarápida");
        insulinaMarcaCombo.getItems().addAll("FIASP", "Humalog", "Novorapid", "Otra");

        btnElegirFoto.setOnAction(e -> seleccionarFoto());

        // configuración de botones
        createAccount.setOnAction(e -> {
            if (validarCampos()) {
                try {

                    UsuarioDTO u = buildUsuarioDTO();

                    Gson gson = new Gson();
                    String json = gson.toJson(u);

                    URL apiUrl = new URL("http://localhost:8080/api/usuarios/registro");
                    System.out.println("enviando datos al servidor");
                    HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; utf-8");
                    conn.setDoOutput(true);

                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(json.getBytes("UTF-8"));
                    }

                    int response = conn.getResponseCode();

                    if (response == 200 || response == 201) {

                        // leemos el objeto usuario del backend
                        InputStream is = conn.getInputStream();
                        String jsonRespuesta = new String(is.readAllBytes(), "UTF-8");

                        Gson gson2 = new Gson();
                        Usuario usuarioCreado = gson2.fromJson(jsonRespuesta, Usuario.class);

                        // 
                        if (fotoSeleccionada != null) {
                            subirFotoAlServidor(usuarioCreado.getIdUsuario(), fotoSeleccionada);
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(Navigator.getStageFrom(createAccount));
                        alert.setHeaderText(null);
                        alert.setContentText("Cuenta creada correctamente");
                        //esperamos a que el user pulse ok para volver al login
                        alert.showAndWait();
                        // tras el ok pantalla de login
                        Parent currentRoot = createAccount.getScene().getRoot();
                        // definimos transición lentahacia pantalla login
                        FadeTransition fadeOut = new FadeTransition(Duration.millis(350), currentRoot);
                        fadeOut.setFromValue(1.0);
                        fadeOut.setToValue(0.0);

                        fadeOut.setOnFinished(ev -> {
                            try {
                                Parent newRoot = FXMLLoader.load(getClass().getResource("/diabetrack_interface/fxml/LoginFXML.fxml"));

                                // nueva escena
                                Scene scene = new Scene(newRoot);
                                newRoot.setOpacity(0.0);

                                Stage stage = (Stage) createAccount.getScene().getWindow();
                                stage.setScene(scene);

                                // hacemos una transición a la nueva pantalla
                                FadeTransition fadeIn = new FadeTransition(Duration.millis(350), newRoot);
                                fadeIn.setFromValue(0.0);
                                fadeIn.setToValue(1.0);
                                fadeIn.play();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                        //ejecutamos el fadeout
                        fadeOut.play();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.initOwner(Navigator.getStageFrom(createAccount));
                        alert.setHeaderText("Error al crear cuenta");
                        alert.setContentText("Código HTTP: " + response);
                        alert.show();

                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private boolean validarCampos() {
        List<String> errores = new ArrayList();
        // Email
        String email = emailField.getText();
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errores.add("Email inválido");

        }

        // Password
        String password = passField.getText().trim();
        String confirm = passConf.getText().trim();

        boolean validPass = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$");

        if (!validPass) {
            errores.add("Contraseña inválido");

        }
        if (!password.equals(confirm)) {
            errores.add("Las contraseñas no coinciden");

        }

        // Nombre y apellido
        if (nameTField.getText().isEmpty() || !nameTField.getText().matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ]+$")) {
            errores.add("Nombre inválido");

        }
        if (surnameTField.getText().isEmpty() || !surnameTField.getText().matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ]+$")) {
            errores.add("Apellido inválido");

        }

        // Fecha
        if (datePicker.getValue() == null) {
            errores.add("Fecha inválido");

        }

        // Sexo
        if (sexoGroup.getSelectedToggle() == null) {
            errores.add("Sexo inválido");

        }

// Peso
        try {
            int peso = Integer.parseInt(pesoTfield.getText());
            if (peso < 0 || peso > 180) {

            }
        } catch (NumberFormatException e) {
            errores.add("Peso inválido");
        }

        // Altura
        try {
            int altura = Integer.parseInt(alturaTfield.getText());
            if (altura < 0 || altura > 240) {

            }
        } catch (NumberFormatException e) {
            errores.add("Altura inválido");
        }

        // Tipo insulina
        if (tipoDCombo.getValue() == null) {
            errores.add("Tipo inválido");

        }

        // Marca insulina
        if (insulinaMarcaCombo.getValue() == null) {
            errores.add("Marca inválido");

        }
        if (!errores.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Navigator.getStageFrom(createAccount));
            alert.setTitle("Errores de validación");
            alert.setHeaderText("Por favor corrige los siguientes errores:");
            alert.setContentText(String.join("\n", errores));
            alert.showAndWait();
            return false;
        }

        return true; // Todo correcto
    }

    private UsuarioDTO buildUsuarioDTO() {
        UsuarioDTO u = new UsuarioDTO();

        u.setEmail(emailField.getText());
        u.setPassword(passField.getText());
        u.setNombre(nameTField.getText());
        u.setApellido(surnameTField.getText());
        u.setFechaNacimiento(datePicker.getValue().toString());
        u.setSexo(hombreRB.isSelected() ? "Hombre" : "Mujer");
        u.setPeso(Integer.parseInt(pesoTfield.getText()));
        u.setAltura(Integer.parseInt(alturaTfield.getText()));
        u.setTipoInsulina((String) tipoDCombo.getValue());
        u.setMarcaInsulina((String) insulinaMarcaCombo.getValue());
        u.setYearDiagnostico((int) yearCombo.getValue());

        return u;
    }

    private void subirFotoAlServidor(Long idUsuario, File archivo) {
        try {
            String boundary = "----XYZ123";
            HttpURLConnection conn = (HttpURLConnection) new URL(
                    "http://localhost:8080/api/usuarios/usuario/" + idUsuario + "/foto"
            ).openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);

            OutputStream output = conn.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true);

            writer.append("--" + boundary).append("\r\n");
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + archivo.getName() + "\"\r\n");
            writer.append("Content-Type: application/octet-stream\r\n\r\n");
            writer.flush();

            Files.copy(archivo.toPath(), output);
            output.flush();

            writer.append("\r\n--" + boundary + "--\r\n");
            writer.close();

            if (conn.getResponseCode() != 200) {
                mostrarError("No se pudo subir la foto");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
// método para cargar imagen de perfil

    private void seleccionarFoto() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png", "*.jpeg")
        );

        File archivo = fc.showOpenDialog(null);

        if (archivo != null) {
            fotoSeleccionada = archivo;
            imagePerfil.setImage(new Image(archivo.toURI().toString()));
            fotoStatus.setText("Foto cargada ✔");

        }
    }

    //mostramos error en caso de que la validación de los campos no dé ok
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(Navigator.getStageFrom(createAccount));
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
