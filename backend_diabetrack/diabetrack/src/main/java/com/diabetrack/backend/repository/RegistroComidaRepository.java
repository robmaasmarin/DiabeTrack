/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.repository;

import com.diabetrack.backend.model.RegistroComida;
import java.time.LocalDateTime;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ESDPC
 */
public interface RegistroComidaRepository extends JpaRepository<RegistroComida, Long> {

    // obtenemos registros de comida asociados a usuario
    List<RegistroComida> findByUsuario_IdUsuario(Long idUsuario);
    // obtenemos registros de comida más recientes asociados a usuario

    @Query("SELECT r FROM RegistroComida r WHERE r.usuario.idUsuario = :id ORDER BY r.fechaHora DESC")
    List<RegistroComida> findLast5ByUsuario(@Param("id") Long id, Pageable pageable);

// ultios 7 días
    @Query("SELECT r FROM RegistroComida r WHERE r.usuario.idUsuario = :id AND r.fechaHora >= :desde ORDER BY r.fechaHora DESC")
    List<RegistroComida> findUltimos7Dias(@Param("id") Long id, @Param("desde") LocalDateTime desde);

// query para informes
    @Query("""
    SELECT r FROM RegistroComida r 
    WHERE r.usuario.idUsuario = :id 
      AND r.fechaHora >= :desde 
    ORDER BY r.fechaHora DESC
""")
    List<RegistroComida> registrosUltimos7Dias(@Param("id") Long id,
            @Param("desde") LocalDateTime desde);

// 5 últimos registros
    List<RegistroComida> findTop5ByUsuarioIdUsuarioOrderByFechaHoraDesc(Long idUsuario);

}
