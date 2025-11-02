/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

/**
 *
 * @author ESDPC
 */
import com.diabetrack.backend.dto.AlimentoDTO;
import com.diabetrack.backend.dto.CategoriaDTO;
import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.model.Categoria;
import com.diabetrack.backend.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return toDTO(categoria);
    }

    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = toEntity(categoriaDTO);
        Categoria saved = categoriaRepository.save(categoria);
        return toDTO(saved);
    }

    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Conversión DTO -> Entidad
    private Categoria toEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(dto.getIdCategoria());
        categoria.setNombre(dto.getNombre());
        return categoria;
    }

    // Conversión Entidad -> DTO
    private CategoriaDTO toDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setIdCategoria(categoria.getIdCategoria());
        dto.setNombre(categoria.getNombre());

        if (categoria.getAlimentos() != null) {
            List<AlimentoDTO> alimentosDTO = categoria.getAlimentos().stream()
                    .map(this::alimentoToDTO)
                    .collect(Collectors.toList());
            dto.setAlimentos(alimentosDTO);
        }

        return dto;
    }

    private AlimentoDTO alimentoToDTO(Alimento alimento) {
        AlimentoDTO dto = new AlimentoDTO();
        dto.setIdAlimento(alimento.getIdAlimento());
        dto.setNombre(alimento.getNombre());
        dto.setCarbohidratos(alimento.getCarbohidratos());
        dto.setIndiceGlucemico(alimento.getIndiceGlucemico());
        dto.setRacion(alimento.getRacion());
        
        if (alimento.getUsuario() != null) { // ← NUEVO
        dto.setIdUsuario(alimento.getUsuario().getIdUsuario());
    }
        
        return dto;
    }
}

