/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class SplashFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorFondo;

    @FXML
    private ImageView fondoImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ajustar tamaño de la imagen de fondo al AnchorPane
        fondoImageView.fitWidthProperty().bind(anchorFondo.widthProperty());
        fondoImageView.fitHeightProperty().bind(anchorFondo.heightProperty());
        
        

        // TODO
    }

}
