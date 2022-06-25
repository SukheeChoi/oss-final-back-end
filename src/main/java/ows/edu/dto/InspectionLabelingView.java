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
  private int recievedQuantity;
  private int InspectionQuantity;
  private int PassedItemQuantity;
  private int MissingItemQuantity;
  private int DamagedItemQuantity;
  private int ETCQuantity;
  private boolean Accepted;
  private int LabelingItemQuantity;
}
