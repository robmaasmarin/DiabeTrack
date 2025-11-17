/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;
import com.diabetrack.backend.dto.ResumenSemanalDTO;
import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.model.RegistroAlimento;
import com.diabetrack.backend.model.RegistroComida;
import com.diabetrack.backend.model.Usuario;
import com.diabetrack.backend.repository.AlimentoRepository;
import com.diabetrack.backend.repository.RegistroComidaRepository;
import com.diabetrack.backend.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

/**
 *
 * @author ESDPC
 */
@Service
public class RegistroComidaService {

    @Autowired
    private RegistroComidaRepository repo;
    @Autowired
private UsuarioRepository usuarioRepository;

/*
    public RegistroComida guardarRegistro(RegistroComida registro) {
        return repo.save(registro);
    }*/
    @Autowired
    private AlimentoRepository alimentoRepository;

    @Transactional
public RegistroComida guardarRegistro(RegistroComida registro) {

    // Asegurar relación bidireccional con usuario
    if (registro.getUsuario() != null && registro.getUsuario().getIdUsuario() != null) {
        Usuario u = usuarioRepository.findById(registro.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        registro.setUsuario(u);
    }

    // Asegurar alimentos
    for (RegistroAlimento ra : registro.getAlimentos()) {
    Alimento a = alimentoRepository.findById(ra.getAlimento().getIdAlimento())
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));
    ra.setAlimento(a);
    ra.setRegistro(registro);
}


    return repo.save(registro);
}
public List<RegistroComida> obtenerTodos() {
    return repo.findAll();
}

public List<RegistroComida> findByUsuario(Long idUsuario) {
    return repo.findByUsuario_IdUsuario(idUsuario);
}

public List<RegistroComida> ultimos5(Long idUsuario) {
    Pageable limit = PageRequest.of(0, 5);
    return repo.findLast5ByUsuario(idUsuario, limit);
}
public List<RegistroComida> ultimos7Dias(Long idUsuario) {
    LocalDateTime hace7dias = LocalDateTime.now().minusDays(7);
    return repo.findUltimos7Dias(idUsuario, hace7dias);
}

public ResumenSemanalDTO resumen7dias(Long idUsuario) {
    LocalDateTime desde = LocalDateTime.now().minusDays(7);
    List<RegistroComida> lista = repo.registrosUltimos7Dias(idUsuario, desde);

    ResumenSemanalDTO dto = new ResumenSemanalDTO();

    dto.totalRegistros = lista.size();
    dto.carbohidratosTotales = lista.stream()
        .mapToDouble(RegistroComida::getCarbohidratos)
        .sum();

    // carbs agrupados por día
    Map<LocalDate, Double> porDia = lista.stream()
        .collect(Collectors.groupingBy(
                r -> r.getFechaHora().toLocalDate(),
                Collectors.summingDouble(RegistroComida::getCarbohidratos)
        ));

    porDia.forEach((fecha, carbs) ->
        dto.carbohidratosPorDia.add(new ResumenSemanalDTO.CarbsDia(fecha, carbs))
    );

    if (!lista.isEmpty())
        dto.ultimoRegistro = lista.get(0); // ya ordenado DESC

    return dto;
}

}
