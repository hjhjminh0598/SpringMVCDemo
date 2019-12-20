package com.example.demo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.DateOptionService;
import com.example.demo.service.EmployeeApiService;
import com.example.demo.service.OfficeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeApiService employeeApiService;

	@Autowired
	private DateOptionService dateOptionService;

	@Autowired
	private OfficeService officeService;

	private List<Employee> listAllEmployees;

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/employees")
	public String employees(Model model) throws MalformedURLException, IOException {
		addAllAttributeModel(model);
		return "employees";

	}

	@PostMapping("/employees")
	public String addEmployee(@ModelAttribute Employee employee, Model model)
			throws ParseException, MalformedURLException, IOException {
		employeeApiService.addEmployee(employee);
		addAllAttributeModel(model);
		return "employees";
	}

	@GetMapping("/employees/edit/{id}")
	public String showEditForm(@PathVariable("id") String id, Model model) throws MalformedURLException, IOException {
		addAllAttributeModel(model);
		Employee editEmployee = employeeApiService.getEmployeeByIdFromJsonStr(id);
		model.addAttribute("editEmployee", editEmployee);
		return "updateEmp";
	}

	@PostMapping("/employees/updated/{id}")
	public String editEmployee(@PathVariable("id") String id, @ModelAttribute Employee employee, Model model)
			throws MalformedURLException, IOException, ParseException {
		employeeApiService.editEmployee(id, employee);
		addAllAttributeModel(model);
		return "employees";
	}

	@GetMapping("/employees/delete/{id}")
	public String employees(@PathVariable("id") String id, Model model)
			throws MalformedURLException, IOException, ParseException {
		employeeApiService.deleteEmployee(id);
		addAllAttributeModel(model);
		return "employees";
	}

	private void addAllAttributeModel(Model model) throws MalformedURLException, IOException {
		Employee newEmployee = new Employee();
		newEmployee.setId(employeeApiService.idAutoGenerate());
		model.addAttribute("listManagerId", employeeApiService.getAllManagerId());
		model.addAttribute("listAllOffices", officeService.getAllOffices());
		model.addAttribute("employee", newEmployee);
		listAllEmployees = employeeApiService.getAllEmployeesFromJSONStr();
		model.addAttribute("listAllEmployees", listAllEmployees);
		dateOptionService.addListDateToModel(model);
	}

}
