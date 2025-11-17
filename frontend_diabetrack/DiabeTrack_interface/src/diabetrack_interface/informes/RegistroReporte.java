/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.informes;

/**
 *
 * @author ESDPC
 */
public class RegistroReporte {

    private String alimento;
    private double cantidad;
    private double carbohidratos;
    private String fecha;

    public RegistroReporte(String alimento, double cantidad, double carbohidratos, String fecha) {
        this.alimento = alimento;
        this.cantidad = cantidad;
        this.carbohidratos = carbohidratos;
        this.fecha = fecha;
    }

    public String getAlimento() { return alimento; }
    public double getCantidad() { return cantidad; }
    public double getCarbohidratos() { return carbohidratos; }
    public String getFecha() { return fecha; }
}

