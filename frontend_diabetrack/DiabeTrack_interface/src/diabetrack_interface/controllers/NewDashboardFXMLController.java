/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Idioma;

import diabetrack_interface.utils.Navigator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;

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
    private HBox comboHbox;

    @FXML
    private ComboBox<Idioma> choiceLanguage; // cambia ? por tu clase Idioma si usas la misma que antes

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
    private Label labelHelp;

    @FXML
    private Button btnContact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // estiramos imagen barra superior al maximizar
        //logoHeader.fitWidthProperty().bind(stackContainer.widthProperty());
        //hacemos los links vbox de las opciones cliqueables
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
            Navigator.changeScene(stage, "/diabetrack_interface/fxml/RegistrarEntradaFXML.fxml");
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
            Navigator.changeScene(stage, "/diabetrack_interface/fxml/CalculoBoloFXML.fxml");
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
            Navigator.changeScene(stage, "/diabetrack_interface/fxml/InformesFXML.fxml");
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
            Navigator.changeScene(stage, "/diabetrack_interface/fxml/CalculoBoloFXML.fxml");
        });

        // TODO
        //creación objetos idioma
        Idioma galego = new Idioma("Galego", new Image(getClass().getResourceAsStream("/diabetrack_interface/resources/images/bgalega.png")));
        Idioma spanish = new Idioma("Español", new Image(getClass().getResourceAsStream("/diabetrack_interface/resources/images/espanabandera.png")));

        choiceLanguage.getItems().addAll(galego, spanish);

        //configuración para mostrar imagen + texto
        choiceLanguage.setCellFactory(cb -> new ListCell<Idioma>() {

            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Idioma item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);

                } else {
                    imageView.setImage(item.getBandera());
                    imageView.setFitHeight(16);//altura minibandera
                    imageView.setPreserveRatio(true);
                    setText(item.getNombre());
                    setGraphic(imageView);

                }
            }
        });
        // Para que también muestre imagen + texto en el botón del ComboBox
        choiceLanguage.setButtonCell(new ListCell<Idioma>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Idioma item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getBandera());
                    imageView.setFitHeight(16);
                    imageView.setPreserveRatio(true);
                    setText(item.getNombre());
                    setGraphic(imageView);
                }
            }
        });

        // idioma a mostrar por defecto
        choiceLanguage.setValue(spanish);
    }

}
