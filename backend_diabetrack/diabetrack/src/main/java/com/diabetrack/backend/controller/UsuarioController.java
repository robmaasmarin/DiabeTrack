/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.dto.UsuarioDTO;
import com.diabetrack.backend.model.Rol;
import com.diabetrack.backend.model.Sexo;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.service.RolService;
import com.diabetrack.backend.service.UsuarioService;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    // traer usuarios no admin
    @GetMapping("/no-admin")
    public List<Usuario> getNonAdminUsers() {
        return usuarioService.getNonAdminUsers();
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

    // GET para obtener foto de perfil
    @GetMapping("/usuario/{id}/foto")
    public ResponseEntity<Resource> obtenerFoto(@PathVariable Long id) {
        try {
            Usuario u = usuarioService.getUsuarioById(id);
            if (u == null || u.getFotoPerfil() == null) {
                return ResponseEntity.notFound().build();
            }

            Path imgPath = Paths.get("uploads").resolve(u.getFotoPerfil());
            Resource img = new UrlResource(imgPath.toUri());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(img);
            

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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
    // post para foto de perfil

    @PostMapping("/usuario/{id}/foto")
    public ResponseEntity<?> subirFoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {

        try {
            Usuario u = usuarioService.getUsuarioById(id);
            if (u == null) {
                return ResponseEntity.notFound().build();
            }

            // Generar nombre único
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Ruta a la carpeta uploads
            Path uploadPath = Paths.get("uploads").resolve(fileName);

            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

            // Guardar nombre en DB
            u.setFotoPerfil(fileName);
            usuarioService.saveUsuario(u);

            return ResponseEntity.ok("Foto subida correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al subir la foto");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario dto) {

        Usuario original = usuarioService.getUsuarioById(id);

        if (original == null) {
            return ResponseEntity.notFound().build();
        }

        // SOLO campos editables
        if (dto.getPeso() != null) {
            original.setPeso(dto.getPeso());
        }
        if (dto.getAltura() != null) {
            original.setAltura(dto.getAltura());
        }
        if (dto.getTipo_insulina() != null) {
            original.setTipo_insulina(dto.getTipo_insulina());
        }
        if (dto.getMarca_insulina() != null) {
            original.setMarca_insulina(dto.getMarca_insulina());
        }

        Usuario guardado = usuarioService.saveUsuario(original);

        return ResponseEntity.ok(guardado);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}
