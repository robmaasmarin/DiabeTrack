/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.models;
import javafx.scene.image.Image;
/**
 *
 * @author ESDPC
 */



public class Idioma {
    private final String nombre;
    private final Image bandera;

    public Idioma(String nombre, Image bandera) {
        this.nombre = nombre;
        this.bandera = bandera;
    }

    public String getNombre() {
        return nombre;
    }

    public Image getBandera() {
        return bandera;
    }

    @Override
    public String toString() {
        return nombre;
    }
}