package ows.edu.dto;

import lombok.Data;

@Data
public class PastOrderDetail {
  private String itemName;
  private String itemCode;
  private Integer itemQuantity;
}
