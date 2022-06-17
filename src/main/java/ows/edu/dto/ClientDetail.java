package ows.edu.dto;

import lombok.Data;

@Data
public class ClientDetail {
  private int clientDetailNo;
  private int clientNo;
  private String shipingdestination;
  private String shipingaddress;
  private Boolean manager;
}
