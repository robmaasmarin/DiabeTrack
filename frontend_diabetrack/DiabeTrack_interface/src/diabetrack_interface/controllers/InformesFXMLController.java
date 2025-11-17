/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package diabetrack_interface.controllers;

import diabetrack_interface.utils.Navigator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button btnInformeMensual;

    @FXML
    private Button btnInformeAlimentos;

    @FXML
    private Button btnVolver;

    @FXML
    public void initialize() {

        btnInformeSemanal.setOnAction(e -> cargarInformeSemanal());
        btnInformeMensual.setOnAction(e -> cargarInformeMensual());
        btnInformeAlimentos.setOnAction(e -> cargarInformeAlimentos());
        //btnVolver.setOnAction(e -> Navigator.goToDashboard());
    }

    private void cargarInformeSemanal() {
        System.out.println("→ Generar informe semanal");
        // FUTURO: llamar a JasperReport con resumen7dias()
    }

    private void cargarInformeMensual() {
        System.out.println("→ Generar informe mensual");
    }

    private void cargarInformeAlimentos() {
        System.out.println("→ Generar informe de alimentos consumidos");
    }
}

