/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.repository;

import com.diabetrack.backend.model.Registro;
import com.diabetrack.backend.model.Rol;
import java.util.List;
import java.util.Optional;
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

   @Query("SELECT r FROM Rol r WHERE LOWER(r.nombre) = LOWER(:nombre)")
    Optional<Rol> findByNombreIgnoreCase(@Param("nombre") String nombre);
    @Query("SELECT r FROM Registro r WHERE r.usuario.idUsuario = :idUsuario")
        // obtenemos los registros asociados a usuario específico
List<Registro> findByUsuarioIdUsuario(@Param("idUsuario") Long idUsuario);

}
