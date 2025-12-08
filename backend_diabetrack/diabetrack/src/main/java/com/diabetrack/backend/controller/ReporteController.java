/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.controller;

import com.diabetrack.backend.dto.AlimentoReporteDTO;
import com.diabetrack.backend.model.Alimento;
import com.diabetrack.backend.service.AlimentoService;
import com.diabetrack.backend.service.ReporteService;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ESDPC
 */
@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;
    @Autowired
    private AlimentoService alimentoService;

    // generar informe en PDF con últimos registros de comida
    @GetMapping("/usuario/{id}/ultimos")
    public ResponseEntity<byte[]> generar(@PathVariable Long id) throws Exception {

        byte[] pdf = reporteService.generarReporteUltimos(id);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=reporte.pdf")
                .body(pdf);
    }
    // generar reporte de alimentos en PDF

    @GetMapping("/alimentos")
    public ResponseEntity<byte[]> generarInformeAlimentos() {
        try {
            // traemos alimentos de bbdd
            List<Alimento> lista = alimentoService.getAll();
            // conversión de alimento al DTO
            List<AlimentoReporteDTO> datos = lista.stream()
                    .map(AlimentoReporteDTO::new)
                    .toList();
            // parámetros necesarios para reporte
            Map<String, Object> params = new HashMap<>();
            params.put("REPORT_DIR", new File(getClass().getResource("/reports/").toURI()).getAbsolutePath() + File.separator);

            // carga archivo desde resources
            InputStream jrxmlStream = getClass().getResourceAsStream("/reports/reporteAlimentos.jrxml");

            if (jrxmlStream == null) {
                throw new RuntimeException("No se encontró reporteAlimentos.jrxml en /reports/");
            }

            // compilación plantilla jrxml a jasperreport
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);
            // rellenamos el reporte
            JasperPrint print = JasperFillManager.fillReport(
                    jasperReport,
                    params,
                    new JRBeanCollectionDataSource(datos)
            );
            // exportamos el reporte como pdf
            byte[] pdf = JasperExportManager.exportReportToPdf(print);
            // envío al front
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "attachment; filename=alimentos.pdf")
                    .body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
