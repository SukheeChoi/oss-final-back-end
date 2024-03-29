package ows.edu.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReleaseInspection {
	private Integer orderItemNo;
	private Integer releaseInspectionQuantity;
	private Date releaseInspectionDate;
	private Date releasePrintDate;
	private Date receiptPrintDate;
	private String employeeId;
	private long releaseInspectionBarcode;

	private String employeeName;

	private Release release;
	private Item item;
	private Picking picking;
	private Vendor vendor;
	private Order order;
	private Employee employee;
	private Packing packing;
	private Client client;
}
