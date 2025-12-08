/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ESDPC
 */
// autenticación de usuarios
@Service
public class AuthService {

    // repo para acceder a usuarios en BBDD
    @Autowired
    private UsuarioRepository usuarioRepository;
    // passenconder para comparación passwords encriptados
    @Autowired
    private PasswordEncoder encoder;

    public Usuario login(String email, String password) {
        // búsqueda user por email
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) {
            return null;
        }

        if (!encoder.matches(password, usuario.getPassword())) {
            return null;
        }

        return usuario;
    }
}
