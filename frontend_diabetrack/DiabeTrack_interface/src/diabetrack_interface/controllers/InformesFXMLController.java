/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.utils.Navigator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ESDPC
 */
public class InformesFXMLController {

    @FXML
    private Button btnInformeSemanal;

    @FXML
    private Button btnInformeTabla;

    

    @FXML
    private Button btnVolver;
    

    @FXML
    public void initialize() {

    btnInformeSemanal.setOnAction(e -> generarInformeSemanal());
        btnInformeTabla.setOnAction(e -> generarInformeAlimentos());
        
        btnVolver.setOnAction(e -> Navigator.goToDashboard(btnVolver));
    }


    // método para generar el primer informe
    private void generarInformeSemanal() {

    try {
        long idUsuario = 1;  // <- esto cambiará posteriormente al user que se loguee

        String urlStr = "http://localhost:8080/api/reportes/usuario/" + idUsuario + "/ultimos";
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/pdf");

        int responseCode = conn.getResponseCode();

        if (responseCode == 200) {

            // recibir PDF
            InputStream is = conn.getInputStream();
            byte[] pdfBytes = is.readAllBytes();

            // guardar archivo localmente
            File pdfFile = new File("informe_semanal.pdf");
            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(pdfBytes);
            }

            // abrir el PDF automáticamente
            Desktop.getDesktop().open(pdfFile);

            mostrarInfo("Informe generado correctamente.");
        } else {
            mostrarError("Error al generar el informe (HTTP " + responseCode + ")");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        mostrarError("Error al conectar con el servidor.");
    }
}
    
    // método para generar el informe que saca la relación de alimentos en bbdd
    
    
private void generarInformeAlimentos() {

    try {

        String urlStr = "http://localhost:8080/api/reportes/alimentos";
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/pdf");

        int responseCode = conn.getResponseCode();

        if (responseCode == 200) {

            // recibir PDF
            InputStream is = conn.getInputStream();
            byte[] pdfBytes = is.readAllBytes();

            // guardar archivo localmente
            File pdfFile = new File("informe_alimentos.pdf");
            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                fos.write(pdfBytes);
            }

            // abrir el PDF automáticamente
            Desktop.getDesktop().open(pdfFile);

            mostrarInfo("Informe de alimentos generado correctamente.");

        } else {
            mostrarError("Error al generar el informe (HTTP " + responseCode + ")");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        mostrarError("Error al conectar con el servidor.");
    }
}
    private void mostrarError(String msg) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}

private void mostrarInfo(String msg) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}
}

