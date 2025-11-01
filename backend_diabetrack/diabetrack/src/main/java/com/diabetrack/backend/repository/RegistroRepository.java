/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.repository;

import com.diabetrack.backend.model.Registro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author ESDPC
 */

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    @Query("SELECT r FROM Registro r WHERE r.usuario.idUsuario = :idUsuario")
    List<Registro> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}
