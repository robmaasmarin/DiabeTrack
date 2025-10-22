/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
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
    private  GridPane gridPesoAltura, gridInsulina;

    @FXML
    private Label weightLabel;

    @FXML
    private TextField pesoTfield;

    @FXML
    private Label alturaLabel;

    @FXML
    private TextField alturaTfield;

    @FXML
    private Button nextButton2;

    @FXML
    private AnchorPane anchorTab3;

    @FXML
    private VBox vboxData3;
    @FXML
    private Label medicalLabel, tipoDLabel, yearLabel;
    @FXML 
    private ComboBox tipoDCombo, yearCombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabPane.getStylesheets().add(getClass().getResource("/diabetrack_interface/css/tabs.css").toExternalForm());

    }

}
