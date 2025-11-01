/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.repository.AlimentoRepository;

import org.springframework.stereotype.Service;
/**
 *
 * @author ESDPC
*/

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    public AlimentoService(AlimentoRepository alimentoRepository) {
        this.alimentoRepository = alimentoRepository;
    }

    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    public Optional<Alimento> getAlimentoById(Long id) {
        return alimentoRepository.findById(id);
    }

    public Alimento saveAlimento(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public void deleteAlimento(Long id) {
        alimentoRepository.deleteById(id);
    }
}