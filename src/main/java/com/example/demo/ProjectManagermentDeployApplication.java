package com.example.demo;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.demo.service.EmployeeApiService;

@SpringBootApplication
public class ProjectManagermentDeployApplication {

	public static void main(String[] args) throws MalformedURLException, IOException {
		SpringApplication.run(ProjectManagermentDeployApplication.class, args);
	}
}
