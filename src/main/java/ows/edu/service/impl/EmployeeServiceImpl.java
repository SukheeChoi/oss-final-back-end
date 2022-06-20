package ows.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.EmployeeDao;
import ows.edu.service.EmployeeService;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {
	
	@Resource
	EmployeeDao employeeDao;
	
	
}
