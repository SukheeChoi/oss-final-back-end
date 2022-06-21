package ows.edu.dto;

import lombok.Data;

@Data
public class Client {
  private int clientNo;
  private String clientName;
  private String employeeId;
  private String representative;
  private String representativeContact;
  
  private Order order;
}
