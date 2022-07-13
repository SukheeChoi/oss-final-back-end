package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CombineShippingPartner {
  private int rownum;
	
  private Integer orderItemNo;
  private String employeeId;
  private Date receiveDate;
  private Date deliveryDate;
  private Integer receiveQuantity;
  private Integer deliveryQuantity;
  private Integer receiveUnreleaseQuantity;
  private Integer deliveryUnreleaseQuantity;
  
//  private OrderItem orderItem;
//  private Item item;
//  private Release release;
}
