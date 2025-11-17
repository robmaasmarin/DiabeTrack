/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.RegistroComida;
import com.diabetrack.backend.service.RegistroComidaService;
import com.diabetrack.backend.dto.ResumenSemanalDTO;
import com.diabetrack.backend.service.ReporteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/usuario/{id}/ultimos5")
public List<RegistroComida> getUltimos5(@PathVariable Long id) {
    return service.ultimos5(id);
}
@GetMapping("/usuario/{id}/ultimos7dias")
public List<RegistroComida> getUltimos7Dias(@PathVariable Long id) {
    return service.ultimos7Dias(id);
}
@GetMapping("/usuario/{id}/resumen7dias")
public ResumenSemanalDTO resumen7dias(@PathVariable Long id) {
    return service.resumen7dias(id);
}
@Autowired
private ReporteService reporteService;

@GetMapping("/usuario/{id}/reporte-ultimos5")
public ResponseEntity<byte[]> reporteUltimos5(@PathVariable Long id) {
    try {
        byte[] pdf = reporteService.generarReporteUltimos(id);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=reporte.pdf")
                .body(pdf);

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}

}