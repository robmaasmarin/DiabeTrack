/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.models;

/**
 *
 * @author ESDPC
 */
public class RegistroAlimentoItem {

    private Long idAlimento;
    private String nombre;
    private double cantidad;
    private double carbohidratos;
    private double carbohidratosTotales;

    public RegistroAlimentoItem(Long idAlimento, String nombre, double cantidad, double carbohidratos) {
        this.idAlimento = idAlimento;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.carbohidratos = carbohidratos;
        this.carbohidratosTotales = (carbohidratos / 100.0) * cantidad;
    }

    public Long getIdAlimento() { return idAlimento; }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCarbohidratos() {
        return carbohidratos;
    }
    public double getCarbohidratosTotales() { 
return carbohidratosTotales;}

    public void setCarbohidratos(double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }
}

