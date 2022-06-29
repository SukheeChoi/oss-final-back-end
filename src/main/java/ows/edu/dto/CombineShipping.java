package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CombineShipping {
  private int orderItemNo;
  private Date orderCheckDate;
  private boolean receiveCheck;
  private int receiveUnrelease;
  private int deliveryUnrelease;
  private boolean deliverCheck;
  private String employeeId;
  private int releaseScheduleDate;
  private Date receiveDate;
  private int releaseQuantity;
  private int receiveQuantity;
  private int deliveryQuantity;
  private int vendorNo;
  private Date deliveryDate;
  
  private OrderItem orderItem;
  private Item item;
  private Release release;
}
