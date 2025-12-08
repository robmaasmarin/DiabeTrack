/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.model.Categoria;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.repository.AlimentoRepository;
import com.diabetrack.backend.repository.CategoriaRepository;
import com.diabetrack.backend.repository.UsuarioRepository;
import com.diabetrack.backend.service.AlimentoService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import lombok.*;
/**
 *
 * @author ESDPC
 */

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    private final AlimentoService alimentoService;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final AlimentoRepository alimentoRepository;
// inyectamos dependencias necesarias para operar con alimentos, users y categorías

    public AlimentoController(
            AlimentoService alimentoService,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            AlimentoRepository alimentoRepository) {
        this.alimentoService = alimentoService;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.alimentoRepository = alimentoRepository;
    }
// obtenemos alimento por id

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> obtenerPorId(@PathVariable Long id) {
        return alimentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // alimentos "globales"
    @GetMapping("/usuario/{idUsuario}")
    public List<Alimento> getAlimentosByUsuario(@PathVariable Long idUsuario) {
        return alimentoService.getAlimentosByUsuario(idUsuario);
    }
    // alimentos globales + alimentos usuario

    @GetMapping
    public List<Alimento> getAllAlimentos() {
        return alimentoService.getAllAlimentos();
    }

    //nuevo endpoint para obtener alimentos globales más alimentos guardados por usuario
    @GetMapping("/usuario/{id}/todos")
    public ResponseEntity<List<Alimento>> getAlimentosGlobalesYUsuario(@PathVariable Long id) {
        List<Alimento> globales = alimentoService.getAlimentosGlobales();
        List<Alimento> personales = alimentoService.getAlimentosByUsuario(id);

        // Combinar ambas listas
        List<Alimento> todos = new ArrayList<>();
        todos.addAll(globales);
        todos.addAll(personales);

        return ResponseEntity.ok(todos);
    }

    // creación nuevo alimento de usuario
    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<Alimento> createAlimento(
            @PathVariable Long idUsuario,
            @RequestBody Alimento alimento) {

        // buscar el usuario
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        // buscar  categoría
        if (alimento.getCategoria() == null || alimento.getCategoria().getIdCategoria() == null) {
            throw new RuntimeException("Debe indicar una categoría válida (idCategoria)");
        }

        Categoria categoria = categoriaRepository.findById(alimento.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + alimento.getCategoria().getIdCategoria()));

        // asignar relaciones
        alimento.setUsuario(usuario);
        alimento.setCategoria(categoria);

        // guardar
        Alimento saved = alimentoService.saveAlimento(alimento);
        return ResponseEntity.ok(saved);
    }
}
