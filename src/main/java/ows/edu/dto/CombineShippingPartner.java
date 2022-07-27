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
  // 메모 추가.
  private String orderItemNote;
  //전달 업데이트시에 주문 상태값 변경하기 위한 주문번호.
  private String orederNo;
  
  private OrderItem orderItem;
  private Item item;
  private Release release;
  private InformationPartner informationPartner;
}
