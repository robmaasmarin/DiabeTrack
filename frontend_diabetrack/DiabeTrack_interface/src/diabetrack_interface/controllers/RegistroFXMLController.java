/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button createAccount;

    @FXML
    private AnchorPane anchorTab3;

    @FXML
    private VBox vboxData3;
    @FXML
    private Label medicalLabel, tipoDLabel, yearLabel;
    @FXML
    private ComboBox tipoDCombo, yearCombo, insulinaMarcaCombo;

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

        // configuración de botones
        createAccount.setOnAction(e -> {
            if (validarCampos()) {
                // Avanzar al siguiente paso
                System.out.println("Validación correcta, continuar...");
            } 
            //else {
                //System.out.println("Error en validación, mostrar mensaje al usuario.");
            //}
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
        alert.setTitle("Errores de validación");
        alert.setHeaderText("Por favor corrige los siguientes errores:");
        alert.setContentText(String.join("\n", errores));
        alert.showAndWait();
        return false;
    }

        return true; // Todo correcto
    }
    
    
    //mostramos error en caso de que la validación de los campos no dé ok
     /*private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }*/
     
}
