package ows.edu.dto;

import lombok.Data;

@Data
public class OrderItem {
	private int orderItemNo;
	private Long orderNo;
	private String itemCode;
	private int qty;
<<<<<<< HEAD
	private int unreleaseQuantity;
=======
	private int orderItemUnreleaseQuantity;
	private String orderItemNote;
		
>>>>>>> branch 'master' of http://kosa3.iptime.org:13000/4ever/final-back-end.git
}
