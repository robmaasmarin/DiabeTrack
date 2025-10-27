/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author ESDPC
 */
@RestController
public class TestController {
     @GetMapping("/api/test")
    public String test() {
        return "Conexión OK con la base de datos Diabetrack ✅";
    }
    
}
