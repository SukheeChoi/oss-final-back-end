package ows.edu.dto;

import lombok.Data;

@Data
public class OrderStatus {
  private int total;
  private int osstem;
  private int vendorShippingPlus;
  private int vendorShippingDir;
}
