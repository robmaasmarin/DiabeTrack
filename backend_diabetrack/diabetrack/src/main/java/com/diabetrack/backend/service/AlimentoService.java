/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.dto.AlimentoDTO;
import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.repository.AlimentoRepository;
import com.diabetrack.backend.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ESDPC
 */

// sercicio para gestión lógica asociada a alimentos
@Service
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    // devolvemos todos los alimentos
    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    // alimentos por ID
    public Optional<Alimento> getAlimentoById(Long id) {
        return alimentoRepository.findById(id);
    }

    public Alimento saveAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void deleteAlimento(Long id) {
        alimentoRepository.deleteById(id);
    }

    public List<Alimento> getAlimentosByUsuario(Long idUsuario) {
        return alimentoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public List<Alimento> getAlimentosGlobales() {
        return alimentoRepository.findByUsuarioIsNull();
    }

    public List<Alimento> getAll() {
        return alimentoRepository.findAll();
    }

    @Autowired
    private UsuarioRepository usuarioRepository; // inyecta el repo

    private Alimento toEntity(AlimentoDTO dto) {
        Alimento alimento = new Alimento();
        alimento.setIdAlimento(dto.getIdAlimento());
        alimento.setNombre(dto.getNombre());
        alimento.setCarbohidratos(dto.getCarbohidratos());
        alimento.setIndiceGlucemico(dto.getIndiceGlucemico());
        alimento.setRacion(dto.getRacion());

        // ? Asignar usuario si viene en el DTO
        if (dto.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            alimento.setUsuario(usuario);
        }

        return alimento;
    }

}
