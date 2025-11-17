/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.dto;

import com.diabetrack.backend.model.RegistroComida;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ESDPC
 */
public class ResumenSemanalDTO {

    public int totalRegistros;
    public double carbohidratosTotales;

    public static class CarbsDia {
        public LocalDate fecha;
        public double carbs;
        public CarbsDia(LocalDate fecha, double carbs) {
            this.fecha = fecha;
            this.carbs = carbs;
        }
    }

    public List<CarbsDia> carbohidratosPorDia = new ArrayList<>();

    public RegistroComida ultimoRegistro;
}
