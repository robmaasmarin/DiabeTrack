/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.dto;


import lombok.Getter;
import lombok.Setter;

public class AlimentoDTO {
    private Long idAlimento;
    private String nombre;
    private Double carbohidratos;
    private Integer indiceGlucemico;
    private Double racion;
    private Long idCategoria;
    private Long idUsuario;
    
    // getters y setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


    public Long getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Long idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Integer getIndiceGlucemico() {
        return indiceGlucemico;
    }

    public void setIndiceGlucemico(Integer indiceGlucemico) {
        this.indiceGlucemico = indiceGlucemico;
    }

    public Double getRacion() {
        return racion;
    }

    public void setRacion(Double racion) {
        this.racion = racion;
    }
}
