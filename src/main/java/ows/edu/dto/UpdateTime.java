package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateTime {
  private int recieveitem;
  private int recieveQuantity;
  private int labelingWorkTimeNo;
  private Date startTime;
  private Date endTime;
}
