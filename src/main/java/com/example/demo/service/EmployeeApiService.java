package com.example.demo.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.EmployeeApiDAO;
import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class EmployeeApiService {

	@Autowired
	private EmployeeApiDAO employeeApiDAO;

	@Autowired
	private DateOptionService dateOptionService;

	public List<Employee> getAllEmployeesFromJSONStr() throws MalformedURLException, IOException {
		String url = "http://localhost:8088/api/v1/employees";
		String allEmpsJSON = employeeApiDAO.getEmployeeApi(url);
		JSONArray empsJSONArr = new JSONArray(allEmpsJSON);
		if (empsJSONArr.length() > 0) {
			List<Employee> listEmps = new ArrayList<Employee>();
			Gson g = new Gson();
			int empsJSONArrLength = empsJSONArr.length();
			for (int i = 0; i < empsJSONArrLength; i++) {
				JSONObject itemEmps = empsJSONArr.getJSONObject(i);
				Employee e = g.fromJson(String.valueOf(itemEmps), Employee.class);
				listEmps.add(e);
			}
			return listEmps;
		}
		return null;
	}

	public Employee getEmployeeByIdFromJsonStr(String id) throws IOException {
		String url = String.format("http://localhost:8088/api/v1/employees/%s", id);
		String empJson = employeeApiDAO.getEmployeeApi(url);
		Gson g = new Gson();
		Employee emp = g.fromJson(String.valueOf(empJson), Employee.class);
		return emp;
	}

	public String idAutoGenerate() throws MalformedURLException, IOException {
		List<Employee> listEmps = getAllEmployeesFromJSONStr();
		int size = listEmps.size();
		int lastEmployeeId = Integer.parseInt(listEmps.get(size - 1).getId());
		String idAutoGenerrate = String.valueOf(lastEmployeeId + 1);
		return lastEmployeeId < 9 ? "00" + idAutoGenerrate
				: lastEmployeeId < 99 && lastEmployeeId >= 9 ? "0" + idAutoGenerrate : idAutoGenerrate;
	}

	public String convertObjectToJson(Employee employee) throws ParseException {
		employee.setDateOfBirth(employee.getDate(), employee.getMonth(), employee.getYear());
		String javaDate = employee.getDateOfBirth();
		String dateOfBirth = dateOptionService.convertJavaDateToSqlDate(javaDate);
		ObjectMapper obj = new ObjectMapper();
		try {
			String json = obj.writeValueAsString(employee).replace(javaDate, dateOfBirth);
			String empJson = "";
			int lengthEmpJson = json.length(), countComma = 0;
			for (int i = lengthEmpJson - 1;; i--) {
				if (countComma == 3) {
					empJson = json.substring(0, i + 1) + "}";
					break;
				}
				if (json.charAt(i) == ',')
					countComma++;
			}
			return empJson;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getAllManagerId() throws MalformedURLException, IOException {
		List<String> listManagerId = new ArrayList<String>();
		List<Employee> listAllEmps = getAllEmployeesFromJSONStr();
		for (Employee e : listAllEmps) {
			listManagerId.add(e.getId());
		}
		return listManagerId;
	}

	public void editEmployee(String id, Employee employee) throws ParseException, IOException {
		employeeApiDAO.editEmployeeApi(id, convertObjectToJson(employee));
	}

	public void addEmployee(Employee employee) throws IOException, ParseException {
		employeeApiDAO.addEmployeeApi(convertObjectToJson(employee));
	}

	public void deleteEmployee(String id) throws IOException, ParseException {
		employeeApiDAO.deleteEmployeeApi(id);
	}
}
