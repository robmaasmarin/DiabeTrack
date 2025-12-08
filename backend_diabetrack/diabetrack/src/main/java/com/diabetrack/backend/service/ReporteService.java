/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diabetrack.backend.service;

import com.diabetrack.backend.dto.RegistroReporteDTO;
import com.diabetrack.backend.model.RegistroComida;
import com.diabetrack.backend.repository.RegistroComidaRepository;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ESDPC
 */
@Service
public class ReporteService {

    @Autowired
    private RegistroComidaRepository registroRepo;

    public byte[] generarReporteUltimos(Long idUsuario) throws Exception {

        List<RegistroComida> registros = registroRepo.findTop5ByUsuarioIdUsuarioOrderByFechaHoraDesc(idUsuario);

        List<RegistroReporteDTO> data = registros.stream()
                .map(r -> new RegistroReporteDTO(r))
                .collect(Collectors.toList());

        InputStream jrxml = getClass().getResourceAsStream("/reports/report_registros.jrxml");
        JasperReport report = JasperCompileManager.compileReport(jrxml);

        Map<String, Object> params = new HashMap<>();
        params.put("REPORT_DIR", getClass().getResource("/reports/").toString());

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
        JasperPrint jp = JasperFillManager.fillReport(report, params, ds);

        return JasperExportManager.exportReportToPdf(jp);

    }
}
