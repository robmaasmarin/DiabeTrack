/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.repository;

import com.diabetrack.backend.model.RegistroComida;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ESDPC
 */
public interface RegistroComidaRepository extends JpaRepository<RegistroComida, Long> {
    List<RegistroComida> findByUsuario_IdUsuario(Long idUsuario);

    
}
