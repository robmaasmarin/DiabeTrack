/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;
    @JsonIgnore
    private String password;
    private LocalDate fecha_nacimiento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private Double peso;
    private Double altura;
    private LocalDate año_diagnostico;
    private String tipo_insulina;
    private String marca_insulina;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
    /*  
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Alimento> alimentos = new ArrayList<>();
     */
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference(value = "usuario-alimentos")
    @JsonIgnore
    private List<Alimento> alimentos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<RegistroComida> registrosComida;

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public LocalDate getAño_diagnostico() {
        return año_diagnostico;
    }

    public void setAño_diagnostico(LocalDate año_diagnostico) {
        this.año_diagnostico = año_diagnostico;
    }

    public String getTipo_insulina() {
        return tipo_insulina;
    }

    public void setTipo_insulina(String tipo_insulina) {
        this.tipo_insulina = tipo_insulina;
    }

    public String getMarca_insulina() {
        return marca_insulina;
    }

    public void setMarca_insulina(String marca_insulina) {
        this.marca_insulina = marca_insulina;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
