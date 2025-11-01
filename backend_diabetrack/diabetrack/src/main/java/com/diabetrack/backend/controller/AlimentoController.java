/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.repository.AlimentoRepository;
import com.diabetrack.backend.service.AlimentoService;
import org.springframework.web.bind.annotation.*;
import lombok.*;
/**
 *
 * @author ESDPC
 */


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alimentos")
@CrossOrigin(origins = "*")
public class AlimentoController {

    private final AlimentoService alimentoService;

    public AlimentoController(AlimentoService alimentoService) {
        this.alimentoService = alimentoService;
    }

    @GetMapping
    public List<Alimento> getAllAlimentos() {
        return alimentoService.getAllAlimentos();
    }

    @GetMapping("/{id}")
    public Optional<Alimento> getAlimentoById(@PathVariable Long id) {
        return alimentoService.getAlimentoById(id);
    }

    @PostMapping
    public Alimento createAlimento(@RequestBody Alimento alimento) {
        return alimentoService.saveAlimento(alimento);
    }

    @PutMapping("/{id}")
    public Alimento updateAlimento(@PathVariable Long id, @RequestBody Alimento alimento) {
        alimento.setIdAlimento(id);
        return alimentoService.saveAlimento(alimento);
    }

    @DeleteMapping("/{id}")
    public void deleteAlimento(@PathVariable Long id) {
        alimentoService.deleteAlimento(id);
    }
    
}
