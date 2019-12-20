package com.example.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class DateOptionService {

	private String[] month = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	public List<Integer> getAllDay() {
		List<Integer> listDay = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++)
			listDay.add(i);
		return listDay;
	}

	public List<String> getAllMonth() {
		List<String> listMonth = new ArrayList<String>();
		for (int i = 0; i < 12; i++)
			listMonth.add(month[i]);
		return listMonth;
	}

	public List<Integer> getAllYear() {
		List<Integer> listYear = new ArrayList<Integer>();
		int yearCurrent = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = yearCurrent; i >= 1905; i--)
			listYear.add(i);
		return listYear;
	}

	public String convertJavaDateToSqlDate(String javaDate) {
		int lengthDOB = javaDate.length();
		Map<String, String> mapMonthToInt = new HashMap<String, String>();
		for (int i = 1; i <= 12; i++) {
			if (i < 10)
				mapMonthToInt.put(month[i - 1], "0" + String.valueOf(i));
			else
				mapMonthToInt.put(month[i - 1], String.valueOf(i));
		}
		String year = javaDate.substring(lengthDOB - 4);
		String month = mapMonthToInt.get(javaDate.substring(0, lengthDOB - 8));
		String day = javaDate.substring(lengthDOB - 7, lengthDOB - 5);
		mapMonthToInt.clear();
		return year + "-" + month + "-" + day;
	}

	public void addListDateToModel(Model model) {
		model.addAttribute("listDay", getAllDay());
		model.addAttribute("listMonth", getAllMonth());
		model.addAttribute("listYear", getAllYear());
	}
}
