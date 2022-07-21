package ows.edu.dto;

import lombok.Data;

@Data
public class ClientOrderDetail {
  private String itemName;
  private String itemCode;
  private Integer itemQuantity;
}
