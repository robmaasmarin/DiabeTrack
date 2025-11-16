/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.RegistroComida;
import com.diabetrack.backend.service.RegistroComidaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ESDPC
 */
@RestController
@RequestMapping("/api/registros-comidas")
@CrossOrigin("*")
public class RegistroComidaController {

    @Autowired
    private RegistroComidaService service;

    @PostMapping
    public RegistroComida guardar(@RequestBody RegistroComida registro) {
        return service.guardarRegistro(registro);
    }

    @GetMapping("/usuario/{id}")
    public List<RegistroComida> obtenerPorUsuario(@PathVariable Long id) {
        return service.findByUsuario(id);
    }
    @GetMapping
public List<RegistroComida> obtenerTodos() {
    return service.obtenerTodos();
}

}