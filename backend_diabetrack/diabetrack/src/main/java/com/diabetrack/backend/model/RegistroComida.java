/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ESDPC
 */
@Entity
public class RegistroComida {
    // identificador único del registro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double glucosaAntes;
    private Double carbohidratos;
    private Double  boloCalculado;

    private LocalDateTime fechaHora;
    // user al que pertenece el registro - many registros to one usuario
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")

private Usuario usuario;
    // lista alimentos consumidos en este registro - cada alimento un registro
    @OneToMany(mappedBy = "registro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RegistroAlimento> alimentos = new ArrayList<>();
    
    



    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public Double getGlucosaAntes() {
        return glucosaAntes;
    }

    public void setGlucosaAntes(Double glucosaAntes) {
        this.glucosaAntes = glucosaAntes;
    }

    public Double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(Double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public Double getBoloCalculado() {
        return boloCalculado;
    }

    public void setBoloCalculado(Double boloCalculado) {
        this.boloCalculado = boloCalculado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<RegistroAlimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<RegistroAlimento> alimentos) {
        this.alimentos = alimentos;
    }
    
}


