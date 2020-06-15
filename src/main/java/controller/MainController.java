package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uplaodFile.theamleaf.entity.EmployeeEntity;
import com.uplaodFile.theamleaf.service.EmployeeService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class MainController {

	public static String uploadDitectory = System.getProperty("user.dir") + "/uploads";

	@Autowired
	private EmployeeService service;

	@RequestMapping(value = "/")
	public String home(Model model) {

		return "home";
	}

	@RequestMapping("/upload")
	public String uploadFiles(Model model, @RequestParam("files") MultipartFile[] files) {

		StringBuilder fileName = new StringBuilder();
		for (MultipartFile file : files) {

			Path path = Paths.get(uploadDitectory, file.getOriginalFilename());
			fileName.append(file.getOriginalFilename());
			try {
				Files.write(path, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		model.addAttribute("msg", "Successfully Uploaded The File Name " + fileName);
		return "upload";
	}

	@GetMapping("/allEmployee")
	public ResponseEntity<List<EmployeeEntity>> getAllEmpl(/* @PathVariable String formate */) {

		List<EmployeeEntity> allEmpl = service.getAllEmpl();

		return new ResponseEntity<List<EmployeeEntity>>(allEmpl, HttpStatus.OK);
	}

	@GetMapping("/employeeJasper/{formate}")
	public ResponseEntity<String> getJasperReport(@PathVariable String formate) throws FileNotFoundException, JRException {

		String message = service.getReportBasedOnFormatType(formate);

		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

}
