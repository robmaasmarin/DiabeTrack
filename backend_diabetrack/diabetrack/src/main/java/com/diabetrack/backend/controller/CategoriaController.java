/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.model.Categoria;
import com.diabetrack.backend.dto.CategoriaDTO;
import com.diabetrack.backend.service.CategoriaService;
import com.diabetrack.backend.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

/**
 *
 * @author ESDPC
 */

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    // constructor para inyectar dependencias necesarias
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // devolvemos todas las categorías disponibles
    @GetMapping
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }

    // categoría por id
    @GetMapping("/{id}")
    public CategoriaDTO getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoriaById(id);
    }

    // creación categoría
    @PostMapping
    public CategoriaDTO createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.saveCategoria(categoriaDTO);
    }

    // actualización categoría
    @PutMapping("/{id}")
    public CategoriaDTO updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        categoriaDTO.setIdCategoria(id);
        return categoriaService.saveCategoria(categoriaDTO);
    }

    // eliminar categoría
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
    }
}
