package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CombineShippingPartner {
  private int rownum;
	
  private int orderItemNo;
  private String employeeId;
  private Date receiveDate;
  private Date deliveryDate;
  private int receiveQuantity;
  private int deliveryQuantity;
  private int receiveUnreleaseQuantity;
  private int deliveryUnreleaseQuantity;
  
  private OrderItem orderItem;
  private Item item;
  private Release release;
}
