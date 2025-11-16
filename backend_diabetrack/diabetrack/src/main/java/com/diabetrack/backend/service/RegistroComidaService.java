/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.model.RegistroAlimento;
import com.diabetrack.backend.model.RegistroComida;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.repository.AlimentoRepository;
import com.diabetrack.backend.repository.RegistroComidaRepository;
import com.diabetrack.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ESDPC
 */
@Service
public class RegistroComidaService {

    @Autowired
    private RegistroComidaRepository repo;
    @Autowired
private UsuarioRepository usuarioRepository;

/*
    public RegistroComida guardarRegistro(RegistroComida registro) {
        return repo.save(registro);
    }*/
    @Autowired
    private AlimentoRepository alimentoRepository;

    @Transactional
public RegistroComida guardarRegistro(RegistroComida registro) {

    // Asegurar relación bidireccional con usuario
    if (registro.getUsuario() != null && registro.getUsuario().getIdUsuario() != null) {
        Usuario u = usuarioRepository.findById(registro.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        registro.setUsuario(u);
    }

    // Asegurar alimentos
    for (RegistroAlimento ra : registro.getAlimentos()) {
    Alimento a = alimentoRepository.findById(ra.getAlimento().getIdAlimento())
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));
    ra.setAlimento(a);
    ra.setRegistro(registro);
}

    /*if (registro.getAlimentos() != null) {
        for (RegistroAlimento ra : registro.getAlimentos()) {

            // Cargar el alimento REAL desde BBDD
            Alimento alimento = alimentoRepository.findById(ra.getAlimento().getIdAlimento())
                    .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));

            // sustituimos el alimento JSON por el real
            ra.setAlimento(alimento);

            // vincular registro <-> registroAlimento
            ra.setRegistro(registro);
        }
    }*/

    return repo.save(registro);
}
public List<RegistroComida> obtenerTodos() {
    return repo.findAll();
}

public List<RegistroComida> findByUsuario(Long idUsuario) {
    return repo.findByUsuario_IdUsuario(idUsuario);
}



}
