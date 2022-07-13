package ows.edu.dto;

import lombok.Data;

@Data
public class RecentOrder {
  private String orderNo;
  private String orderShippingWay;
  private String orderDate;
  private String itemName;
  private String itemCode;
  private String venderName;
  private Integer orderQuantity;
  private Integer pickingQuantity;
  private String inspectionPacking;
  private String invoiceCode;
  private String shippingAddress;
}
