package com.uplaodFile.theamleaf;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.MainController;

@SpringBootApplication
@ComponentScan({"com.uplaodFile.theamleaf","controller"})
public class UploadingFilesInSpringBootWithTheamLeafApplication {

	public static void main(String[] args) {
		new File(MainController.uploadDitectory).mkdir();
		SpringApplication.run(UploadingFilesInSpringBootWithTheamLeafApplication.class, args);
	}

}
