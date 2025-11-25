/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.service.UsuarioService;
import com.diabetrack.backend.service.RolService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import com.diabetrack.backend.dto.UsuarioDTO;
import com.diabetrack.backend.model.Sexo;
import com.diabetrack.backend.model.Rol;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import static org.springframework.util.StringUtils.capitalize;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // permite peticiones desde el frontend
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
//TESTEANDO PARA MANEJO DE ERRORES

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody UsuarioDTO dto) {
        Usuario usuario = usuarioService.fromDTO(dto);
        
        //cifrado
        usuario.setPassword(usuarioService.encodePassword(dto.getPassword()));
        return usuarioService.saveUsuario(usuario);
    }

    // creación usuario desde front
@PostMapping("/registro")
public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioDTO dto) {

    Usuario usuario = usuarioService.fromDTO(dto);

    // Cifrar la contraseña correctamente
    usuario.setPassword(usuarioService.encodePassword(dto.getPassword()));

    Usuario guardado = usuarioService.saveUsuario(usuario);

    return ResponseEntity.ok(guardado);
}


    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setIdUsuario(id);
        return usuarioService.saveUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}
