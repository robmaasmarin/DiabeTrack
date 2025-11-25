/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.LoginRequest;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    public AuthController(UsuarioService usuarioService, PasswordEncoder encoder) {
        this.usuarioService = usuarioService;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
  System.out.println("📩 Backend recibió:");
    System.out.println("Email = " + loginRequest.getEmail());
    System.out.println("Password = [" + loginRequest.getPassword() + "]");
        Usuario usuario = usuarioService.findByEmail(loginRequest.getEmail());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email no registrado");
        }

        // Comparar contraseña sin cifrar con la cifrada almacenada
        if (!encoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Contraseña incorrecta");
        }

        // NO devolvemos la contraseña
        usuario.setPassword(null);

        return ResponseEntity.ok(usuario);
    }
}