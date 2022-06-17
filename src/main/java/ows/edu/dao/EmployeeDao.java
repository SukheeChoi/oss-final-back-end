package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Employee;

@Mapper
public interface EmployeeDao {
  public List<Employee> search();
  public Employee searchById(String employeeId);
}
