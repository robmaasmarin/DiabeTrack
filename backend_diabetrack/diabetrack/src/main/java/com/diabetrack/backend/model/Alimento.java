/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.model;

import com.diabetrack.backend.model.Categoria;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    // identificamos y mapeamos alimento
    @JsonProperty("idAlimento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimento")
    private Long idAlimento;
    // nombre del alimento
    @JsonProperty("nombre")
    private String nombre;
    // cantidad de carbs del alimento
    @JsonProperty("carbohidratos")
    private double carbohidratos;
    // índice glucémico
    @JsonProperty("indiceGlucemico")
    private int indiceGlucemico;
    @JsonProperty("racion")
    private double racion;
    // relación con categoría
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    // evitar ciclos infinitos
    @JsonBackReference
    private Categoria categoria;
    // relación opcional con user
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    @JsonBackReference(value = "usuario-alimentos")
    private Usuario usuario;

    // relación inversa alimento en muchos registros
    @OneToMany(mappedBy = "alimento")
    private List<Registro> registros;

    // Getters y setters
    @JsonProperty("idAlimento")
    public Long getIdAlimento() {
        return idAlimento;
    }

    @JsonProperty("id")

    public void setIdAlimento(Long idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public int getIndiceGlucemico() {
        return indiceGlucemico;
    }

    public void setIndiceGlucemico(int indiceGlucemico) {
        this.indiceGlucemico = indiceGlucemico;
    }

    public double getRacion() {
        return racion;
    }

    public void setRacion(double racion) {
        this.racion = racion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
