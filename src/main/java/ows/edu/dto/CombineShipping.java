package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CombineShipping {
  private int orderItemNo;
  private Date orderCheckDate;
  private boolean recieveCheck;
  private int unrelease;
  private boolean deliverCheck;
  private String employeeId;
  private Date releaseScheduleDate;
  private Date recieveDate;
  
  private OrderItem orderItem;
  private Item item;
  private Release release;
}
