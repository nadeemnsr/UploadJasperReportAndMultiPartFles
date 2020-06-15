package com.uplaodFile.theamleaf.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.uplaodFile.theamleaf.entity.EmployeeEntity;
import com.uplaodFile.theamleaf.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	public List<EmployeeEntity> getAllEmpl() {
		return repo.findAll();
	}

	public String getReportBasedOnFormatType(String formate) throws FileNotFoundException, JRException {

		String path = "C:\\Users\\Admin\\Desktop\\JasperReport";

		List<EmployeeEntity> employees = repo.findAll();
		// load file and compile it

		File file = ResourceUtils.getFile("classpath:employees.jrxml");
		JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
		Map<String, Object> map = new HashMap<>();
		map.put("createdBy", "Nadeem Sadiq");
		JasperPrint print = JasperFillManager.fillReport(report, map, dataSource);
		if (formate.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(print, path + "\\employees.html");
		}
		if (formate.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(print, path + "\\employees.pdf");
		}

		return "report generated in path : " + path;
	}
}
