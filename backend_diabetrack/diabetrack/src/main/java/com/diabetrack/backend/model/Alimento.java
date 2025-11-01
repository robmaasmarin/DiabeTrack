/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.model;
import com.diabetrack.backend.model.Categoria;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "alimentos")
public class Alimento {
     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlimento;
    private String nombre;
    private double carbohidratos;
    private int indiceGlucemico;
    private double racion;

    @ManyToOne
@JoinColumn(name = "id_categoria")
@JsonBackReference
private Categoria categoria;


    @OneToMany(mappedBy = "alimento")
    private List<Registro> registros;
    
    // Getters y setters
    public Long getIdAlimento() { return idAlimento; }
    public void setIdAlimento(Long idAlimento) { this.idAlimento = idAlimento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getCarbohidratos() { return carbohidratos; }
    public void setCarbohidratos(double carbohidratos) { this.carbohidratos = carbohidratos; }

    public int getIndiceGlucemico() { return indiceGlucemico; }
    public void setIndiceGlucemico(int indiceGlucemico) { this.indiceGlucemico = indiceGlucemico; }

    public double getRacion() { return racion; }
    public void setRacion(double racion) { this.racion = racion; }
    
}