package ows.edu.dto;

import lombok.Data;

@Data
public class AfterPickingView {	
	private String releaseInspectionEmployeeId;
	private String releaseInspectionEmployeeName;
	private String releaseEmployeeId;
	private String releaseEmployeeName;
	
	// service단에서 포멧 처리 이후에 대입.
	private String strReleaseInspectionDate;
	private String strReleasePrintDate;
	private String strReceiptPrintDate;
	
	private Order order;
	private OrderItem orderItem;
	private Item item;
	private Client client;
	private Picking picking;
	private CombineShippingPartner combineShippingPartner;
	private Employee employee;
	private ReleaseInspection releaseInspection;
	private Release release;
	private Vendor vendor;
}
