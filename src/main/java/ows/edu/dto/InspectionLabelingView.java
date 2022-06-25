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
  private Integer InspectionQuantity;
  private Integer PassedItemQuantity;
  private Integer MissingItemQuantity;
  private Integer DamagedItemQuantity;
  private Integer ETCQuantity;
  private boolean Accepted;
  private Integer LabelingItemQuantity;
}
