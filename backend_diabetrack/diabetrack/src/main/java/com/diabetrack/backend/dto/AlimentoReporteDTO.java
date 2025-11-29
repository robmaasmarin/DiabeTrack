/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.dto;

import com.diabetrack.backend.model.Alimento;


/**
 *
 * @author ESDPC
 */

public class AlimentoReporteDTO {

    private String nombre;
    private Double carbohidratos;
    private Integer indiceGlucemico;

    public AlimentoReporteDTO(Alimento a) {
        this.nombre = a.getNombre();
        this.carbohidratos = a.getCarbohidratos();
        this.indiceGlucemico = a.getIndiceGlucemico();
    }
    
    public String getNombre() {
    return nombre;
}

public Double getCarbohidratos() {
    return carbohidratos;
}

public Integer getIndiceGlucemico() {
    return indiceGlucemico;
}

}

