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
import javafx.scene.Node;

public class Navigator {

    public static void changeScene(Stage stage, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Navigator.class.getResource(fxmlPath));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // helper: obtener Stage a partir de cualquier nodo
    public static Stage getStageFrom(javafx.scene.Node node) {
        return (Stage) node.getScene().getWindow();
    }
    public static void goToDashboard(Node node) {
        Stage stage = getStageFrom(node);
    changeScene(stage, "/diabetrack_interface/fxml/NewDashboardFXML.fxml");
}
    
}

