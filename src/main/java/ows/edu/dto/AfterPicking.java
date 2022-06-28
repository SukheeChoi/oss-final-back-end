package ows.edu.dto;

import lombok.Data;

@Data
public class AfterPicking {
	private String strReleaseInspectionUnreleased;
	private String strPackingUnreleased;
	private String strAfterPickingUnreleased;
	///
	private ReleaseInspection releaseInspection;
	private Release release;
	private Item item;
	private Picking picking;
	private Vendor vendor;
	private Order order;
	private Packing packing;
	private Client client;
	
}
