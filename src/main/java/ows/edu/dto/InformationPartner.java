package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class InformationPartner {

  private Integer orderItemNo;
  private Date orderCheckDate;
  private Date releaseCheckDate;
  private Integer releaseQuantity;

}
