/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.model.Registro;
import com.diabetrack.backend.repository.RegistroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author ESDPC
 */

@Service
public class RegistroService {

    private final RegistroRepository registroRepository;

    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    public List<Registro> getAllRegistros() {
        return registroRepository.findAll();
    }

    public Optional<Registro> getRegistroById(Long id) {
        return registroRepository.findById(id);
    }

    public Registro saveRegistro(Registro registro) {
        return registroRepository.save(registro);
    }

    public void deleteRegistro(Long id) {
        registroRepository.deleteById(id);
    }

    //obtener registros por usuario
    public List<Registro> getRegistrosByUsuario(Long idUsuario) {
        return registroRepository.findByUsuarioIdUsuario(idUsuario);
    }

}
