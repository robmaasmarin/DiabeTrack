/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

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
}
