package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OfficeService {

	public List<Integer> getAllOffices() {
		List<Integer> listAllOffices = new ArrayList<>();
		listAllOffices.add(1);
		listAllOffices.add(4);
		listAllOffices.add(5);
		return listAllOffices;
	}
}
