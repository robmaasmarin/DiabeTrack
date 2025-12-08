/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.util.Duration;

public class Navigator {

    // método para cambiar de pantalla
    public static void changeScene(Stage stage, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Navigator.class.getResource(fxmlPath));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // método para obtener stage
    public static Stage getStageFrom(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    // creamos efecto fade para que la transición entre pantallas no sea tan brusca
    public static void fadeTo(Node node, String fxmlPath) {
        Stage stage = getStageFrom(node);
        Parent currentRoot = stage.getScene().getRoot();

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), currentRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(ev -> {
            try {
                Parent newRoot = FXMLLoader.load(Navigator.class.getResource(fxmlPath));

              
                newRoot.setOpacity(0.0);
                Scene newScene = new Scene(newRoot);
                stage.setScene(newScene);
                
                Navigator.centerOnScreen(stage);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), newRoot);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        fadeOut.play();
    }
    // método para centrar la app en el monitor
    public static void centerOnScreen(Stage stage) {
    Rectangle2D bounds = Screen.getScreensForRectangle(
            stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()
    ).get(0).getVisualBounds();

    stage.setX(bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2);
    stage.setY(bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2);
}
    public static void goToDashboard(Node node) {
        fadeTo(node, "/diabetrack_interface/fxml/NewDashboardFXML.fxml");
    }
}

