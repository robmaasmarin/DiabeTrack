/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.model.Rol;
import com.diabetrack.backend.repository.RolRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author ESDPC
 */
@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol getRolByName(String nombre) {
        return rolRepository.findByNombreIgnoreCase(nombre).orElse(null);
    }
}
