package diabetrack_interface.models;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ESDPC
 */

public class Alimento {
    private Long idAlimento;
    private String nombre;
    private double carbohidratos; // gramos por 100g
    private double racion; // gramos por ración (si usas)

    public Alimento(Long idAlimento, String nombre, double carbohidratos, double racion) {
        this.idAlimento = idAlimento;
        this.nombre = nombre;
        this.carbohidratos = carbohidratos;
        this.racion = racion;
    }
    
    public Long getIdAlimento() { return idAlimento; }
    public String getNombre() { return nombre; }
    public double getCarbohidratos() { return carbohidratos; }
    public double getRacion() { return racion; }

    @Override
    public String toString() {
        return nombre; // muestra nombre en ComboBox
    }
}