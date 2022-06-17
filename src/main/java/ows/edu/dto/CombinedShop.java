package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CombinedShop {
  private int orderItemNo;
  private Date orderCheckDate;
  private boolean recieveCheck;
  private int unrelease;
  private boolean deliverCheck;
  private String EmployeeId;
  private Date releaseScheduleDate;
  private Date recieveDate;
}
