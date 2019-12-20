package com.example.demo.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Employee {

	private String id;
	private String firstName;
	private String lastName;
	private String midName;
	private String address;
	private String gender;
	private float salary;
	private String managerId;
	private int office;

	private java.util.Date dateOfBirth;
	private int date;
	private String month;
	private int year;

	public Employee() {

	}

	public Employee(String id, String firstName, String lastName, String midName, String address, String gender,
			float salary, String managerId, int office, int date, String month, int year) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midName = midName;
		this.address = address;
		this.gender = gender;
		this.salary = salary;
		this.managerId = managerId;
		this.office = office;
		this.date = date;
		this.month = month;
		this.year = year;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getDateOfBirth() {
		String date = String.valueOf(dateOfBirth);
		return date.substring(4, 10) + " " + date.substring(date.length() - 4);
	}

	public void setDateOfBirth(int date, String month, int year) throws ParseException {
		String dateOfBirth = String.valueOf(date) + "-" + month + "-" + String.valueOf(year);
		DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy");
		this.dateOfBirth = formatter.parse(dateOfBirth);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public int getOffice() {
		return office;
	}

	public void setOffice(int office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", midName=" + midName
				+ ", address=" + address + ", gender=" + gender + ", salary=" + salary + ", managerId=" + managerId
				+ ", office=" + office + ", dateOfBirth=" + dateOfBirth + ", date=" + date + ", month=" + month
				+ ", year=" + year + "]";
	}

}
