package ows.edu.dto;

import lombok.Data;

@Data
public class ClientFilter {
	private String[] shippingCategory;
	private int status;
	private boolean unrelease;
	private int orderNo;
	private String clientName;
}
