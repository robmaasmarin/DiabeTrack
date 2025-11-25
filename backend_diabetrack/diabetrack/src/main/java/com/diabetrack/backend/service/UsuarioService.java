/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.config.SecurityConfig;
import com.diabetrack.backend.dto.UsuarioDTO;
import com.diabetrack.backend.model.Rol;
import com.diabetrack.backend.model.Sexo;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.repository.UsuarioRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author ESDPC
 */

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private RolService rolService;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario registrarUsuario(Usuario u) {
        u.setPassword(encoder.encode(u.getPassword()));
        return usuarioRepository.save(u);
    }

    public Usuario fromDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(dto.getEmail());
        //usuario.setPassword(dto.getPassword());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());

        // convertir fechaNacimiento (String > LocalDate)
        if (dto.getFechaNacimiento() != null && !dto.getFechaNacimiento().isEmpty()) {
            usuario.setFecha_nacimiento(LocalDate.parse(dto.getFechaNacimiento()));
        }

        // sexo (String > Enum)
        if (dto.getSexo() != null) {
            usuario.setSexo(Sexo.valueOf(dto.getSexo().trim()));

        }

        // peso / altura
        if (dto.getPeso() != null) {
            usuario.setPeso(dto.getPeso().doubleValue());
        }
        if (dto.getAltura() != null) {
            usuario.setAltura(dto.getAltura().doubleValue());
        }

        // año diagnostico (int > LocalDate)
        if (dto.getYearDiagnostico() != null) {
            usuario.setAño_diagnostico(LocalDate.of(dto.getYearDiagnostico(), 1, 1));
        }

        usuario.setTipo_insulina(dto.getTipoInsulina());
        usuario.setMarca_insulina(dto.getMarcaInsulina());

        // rol por defecto > usuario
        Rol rol = rolService.getRolByName("usuario");
        usuario.setRol(rol);

        return usuario;
    }

    public UsuarioService(UsuarioRepository usuarioRepository, RolService rolService) {
        this.usuarioRepository = usuarioRepository;
        this.rolService = rolService;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }
    
    //cifrado
    public String encodePassword(String rawPassword) {
    return encoder.encode(rawPassword);
}


}
