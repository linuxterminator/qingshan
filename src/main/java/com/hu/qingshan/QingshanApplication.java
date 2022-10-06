package com.hu.qingshan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class QingshanApplication {

	public static void main(String[] args) {
		SpringApplication.run(QingshanApplication.class, args);
	}

}
