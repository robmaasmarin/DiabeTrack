/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.diabetrack.backend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author ESDPC
 * 
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.diabetrack.backend")
public class Diabetrack {

    public static void main(String[] args) {
        SpringApplication.run(Diabetrack.class, args);
    }
}
