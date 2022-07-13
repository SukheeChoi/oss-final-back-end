package ows.edu.dto;

import lombok.Data;

@Data
public class Item {
	private String itemCode;
	private String itemName;
	private int vendorNo;
	private Boolean itemOsstem;
}
