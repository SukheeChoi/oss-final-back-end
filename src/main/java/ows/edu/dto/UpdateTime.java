package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateTime {
  private int receiveItem;
  private int receiveQuantity;
  private int labelingWorkTimeNo;
  private String startTime;
  private String endTime;
  private String placingOrderNo;
}
