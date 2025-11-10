/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diabetrack_interface.session;

import diabetrack_interface.models.Usuario;

/**
 *
 * @author ESDPC
 */


public class CurrentUser {

    private static Usuario usuario;

    public static void set(Usuario u) {
        usuario = u;
    }

    public static Usuario get() {
        return usuario;
    }

    public static boolean isLogged() {
        return usuario != null;
    }

    public static void clear() {
        usuario = null;
    }
}

