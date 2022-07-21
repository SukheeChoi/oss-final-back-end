package ows.edu.dto;

import lombok.Data;

@Data
public class ClientOrder {
  private String clientName;
  private String orderDate;
  private String itemName;
  private String itemCount;
  private String itemQuantity;
  private String orderNo;
  private String orderWay;
  private String releaseDate;
}
