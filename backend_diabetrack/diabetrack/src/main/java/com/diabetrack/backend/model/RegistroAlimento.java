/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author ESDPC
 */
@Entity
public class RegistroAlimento {
    // identificador único del detalle de registro de comida
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;
    // registro al que pertenece el detalle - relación muchos detalles --> un registro
    @ManyToOne
    @JoinColumn(name = "registro_id")
    @JsonBackReference
    private RegistroComida registro;
    // alimento asociado al detalle
    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getCantidad() { return cantidad; }
    public void setCantidad(Double cantidad) { this.cantidad = cantidad; }

    public RegistroComida getRegistro() { return registro; }
    public void setRegistro(RegistroComida registro) { this.registro = registro; }

    public Alimento getAlimento() { return alimento; }
    public void setAlimento(Alimento alimento) { this.alimento = alimento; }
}
