//김예원
package ows.edu.dto;

import lombok.Data;

@Data
public class ClientFilter {
	private String[] shippingCategory;
	private int status;
	private boolean unrelease;
	private Long orderNo;
	private String clientName;
//	private int pageNo;
//	private int pageSize;
//	private int startRowIndex;
}
