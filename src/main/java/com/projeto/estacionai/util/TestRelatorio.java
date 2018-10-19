package com.projeto.estacionai.util;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.ibm.icu.util.Calendar;

import com.projeto.estacionai.security.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

public class TestRelatorio {

	
	public static void main(String[] args) throws SQLException, JRException, ParseException {
		
		Connection connection = Conexao.getConnection();
		
		JasperCompileManager.compileReportToFile("relatorio_movimento_cliente.jrxml");
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date data_inicio = sdf.parse("02/02/2018");
//		Date data_final = sdf.parse("12/08/2018");
//		
//		params.put("DATA_INICIO", data_inicio);
//		params.put("DATA_FIM", data_final);
		JasperPrint print =  JasperFillManager.fillReport("relatorio_movimento_cliente.jasper", params, connection);

        // Make sure the output directory exists.
        File outDir = new File("C:\\Users\\ALISSON\\Documents\\relatorios\\saida");
        outDir.mkdirs();
 
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput("C:\\Users\\ALISSON\\Documents\\relatorios\\saida\\movimento_cliente_resultado.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
		
		connection.close();
	}
	
}
