/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.Registro;
import com.diabetrack.backend.repository.RegistroRepository;
import com.diabetrack.backend.service.RegistroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author ESDPC
 */




@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public List<Registro> getAllRegistros() {
        return registroService.getAllRegistros();
    }

    @GetMapping("/{id}")
    public Optional<Registro> getRegistroById(@PathVariable Long id) {
        return registroService.getRegistroById(id);
    }

    @PostMapping
    public Registro createRegistro(@RequestBody Registro registro) {
        return registroService.saveRegistro(registro);
    }

@PutMapping("/{id}")
    public Registro updateRegistro(@PathVariable Long id, @RequestBody Registro registro) {
        registro.setIdRegistro(id); //
        return registroService.saveRegistro(registro);
    }

    @DeleteMapping("/{id}")
    public void deleteRegistro(@PathVariable Long id) {
        registroService.deleteRegistro(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Registro> getRegistrosByUsuario(@PathVariable Long idUsuario) {
        return registroService.getRegistrosByUsuario(idUsuario);
    }
}