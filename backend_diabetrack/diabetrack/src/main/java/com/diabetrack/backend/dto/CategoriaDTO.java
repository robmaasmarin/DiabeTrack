/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class CategoriaDTO {
    private Long idCategoria;
    private String nombre;
    private List<AlimentoDTO> alimentos;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AlimentoDTO> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<AlimentoDTO> alimentos) {
        this.alimentos = alimentos;
    }
}

