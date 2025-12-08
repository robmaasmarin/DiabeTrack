/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package diabetrack_interface;

import diabetrack_interface.utils.Navigator;
import diabetrack_interface.utils.ScreenUtils;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author ESDPC
 */
public class FXMain_Diabetrack extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            // Cargar el splash
            AnchorPane splashRoot = FXMLLoader.load(getClass().getResource("fxml/SplashFXML.fxml"));
            Scene splashScene = new Scene(splashRoot, 600, 450);//ajustar tamaño salida

            // movemos el stage para mostrarlo en la pantalla grande
            ScreenUtils.moveToSecondaryScreen(primaryStage);
            //editando icono de la ventana
            Image icon = new Image(getClass().getResourceAsStream("/diabetrack_interface/resources/images/moradotodo.png"));
            primaryStage.getIcons().add(icon);
            //título a mostrar
            primaryStage.setTitle("DiabeTrack");

            primaryStage.setScene(splashScene);
            primaryStage.sizeToScene();//adaptar tamaño
            primaryStage.show();
            
            //centramos el splash en pantalla
            Navigator.centerOnScreen(primaryStage);

            // crear una transición de 3 segundos
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> {

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/diabetrack_interface/fxml/LoginFXML.fxml"));

                    Scene scene = new Scene(root, 600, 450);

                    scene.getStylesheets().add(getClass().getResource("/diabetrack_interface/css/login.css").toExternalForm());

                    primaryStage.setScene(scene);
                    //centramos también login
                    Navigator.centerOnScreen(primaryStage);

                    primaryStage.show();
                } catch (IOException ex) {

                }

            });
            delay.play();

        } catch (IOException ex) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
   

    }

}
