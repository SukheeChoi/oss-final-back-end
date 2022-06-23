package ows.edu.dto;

import lombok.Data;

@Data
public class OrderFilter {
  private String[] company;
  private String[] shippingway;
  private String[] unreleased;
  
  private int itemOSS;
  private int itemOSSPRO;
  private int itemVND;
  private int itemVNDPLUS;
  private int orderCatagory;
  private int pickingdirectionUnreleased;

}
