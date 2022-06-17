package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
  private String employeeId;
  private String employeeName;
  private int departmentNo;
  private String branchCode;
  private String position;
  private String contact;
  private Date hireDate;
}
