package ows.edu.dto;

import lombok.Data;

@Data
public class InspectionLabelingView { //오른쪽 화면
  private int placingOrderItemNO;
  private String employeeName;
  private String vendorName;
  private String itemName;
  private String itemCode;
  private String placingOrderNo;
  private String lotCode;
  private Integer recievedQuantity;
  private Integer inspectionQuantity;
  private Integer passedItemQuantity;
  private Integer missingItemQuantity;
  private Integer damagedItemQuantity;
  private Integer etcQuantity;
  private boolean Accepted;
  private Integer labelingItemQuantity;
}
