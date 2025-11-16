/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.models;

import javafx.beans.property.*;

public class SeleccionAlimento {
    private final ObjectProperty<Alimento> alimento = new SimpleObjectProperty<>();
    private final DoubleProperty cantidad = new SimpleDoubleProperty();
    private final DoubleProperty carbs = new SimpleDoubleProperty();

    public SeleccionAlimento(Alimento alimento, double cantidad) {
        this.alimento.set(alimento);
        this.cantidad.set(cantidad);
        double calculatedCarbs = alimento.getCarbohidratos() * cantidad / 100.0;
        this.carbs.set(calculatedCarbs);
    }

    public Alimento getAlimento() { return alimento.get(); }
    public ObjectProperty<Alimento> alimentoProperty() { return alimento; }
    public String getNombre() {
    return getAlimento().getNombre();
}


    public double getCantidad() { return cantidad.get(); }
    public DoubleProperty cantidadProperty() { return cantidad; }
    public void setCantidad(double cantidad) {
        this.cantidad.set(cantidad);
        // actualizar carbs cuando cambie cantidad
        this.carbs.set(getAlimento().getCarbohidratos() * cantidad / 100.0);
    }

    public double getCarbs() { return carbs.get(); }
    public double getCarbohidratosTotales() {
        return getCarbs();
    }
    public DoubleProperty carbsProperty() { return carbs; }
}
