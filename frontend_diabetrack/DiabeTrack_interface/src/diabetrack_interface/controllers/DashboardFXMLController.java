/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.models.Idioma;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private StackPane stackContainer;

    @FXML
    private ImageView logoHeader;

    @FXML
    private HBox hboxHeader;

    @FXML

    private ComboBox<Idioma> choiceLanguage; // Idioma es la clase que creamos para banderas + texto

    @FXML
    private ImageView profileImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // estiramos imagen barra superior al maximizar
        //logoHeader.fitWidthProperty().bind(stackContainer.widthProperty());
        
        
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
