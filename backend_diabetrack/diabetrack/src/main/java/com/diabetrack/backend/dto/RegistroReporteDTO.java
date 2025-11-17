/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.dto;

import com.diabetrack.backend.model.RegistroComida;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ESDPC
 */
@Getter
@Setter
public class RegistroReporteDTO {

    private String fecha;
    private Double glucosaAntes;
    private Double carbohidratos;
    private Double boloCalculado;

    public RegistroReporteDTO(RegistroComida r) {
        this.fecha = r.getFechaHora().toString();
        this.glucosaAntes = r.getGlucosaAntes();
        this.carbohidratos = r.getCarbohidratos();
        this.boloCalculado = r.getBoloCalculado();
    }
}
