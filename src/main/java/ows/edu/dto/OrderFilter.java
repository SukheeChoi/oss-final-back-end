package ows.edu.dto;

import lombok.Data;

@Data
public class OrderFilter {
  private String[] company;
  private String[] shippingway;
  private String[] unreleased;
  private String searchSelected;
  private String searchContent;
  private int itemOSS;
  private int itemOSSPRO;
  private int itemVND;
  private int itemVNDPLUS;
  private int shippingCategory;
  private int pickingdirectionUnreleased;

}