package ows.edu.dto;

import lombok.Data;

@Data
public class OrderView {
  
  private String orderDate;                  //주문일시
  private long orderNo;                      //주문번호
  private String clientName;                 //거래처
  private String itemName;                   //품목명
  private String itemCode;                   //품목코드
  private int orderItemQuantity;             //주문수량
  private String shippingCategory;           //배송구분
  private String vendorName;                 //업체명
  
  private Integer pickingDirectionAttempt;   //피킹지시 차수
  private String pickingDirectionDate;       //피킹지시 지시일시
  private Integer pickingDirectionQuantity;  //피킹지시 지시수량
  private Integer pickingDirectionUnrelease; //피킹지시 미출고
  
  private String pickingEmployee;            //피킹 담당자
  private Integer pickingQuantity;           //피킹 수량
  private String pickingDate;                //피킹 일시
  private Integer pickingUnrelease;          //피킹 미출고
  
  private String orderShippingWay;           //협력사 배송방식
  private String orderCheckDate;             //협력사 주문확인 일시
  private Integer releaseQuantity;           //협력사 출고수량
  private String releaseScheduleDate;        //협력사 출고예정 일자
  private String recieveDate;                //협력사 수령일시
  
  private String packingInspectionEmployee;  //출고검수패킹 담당자
  private String inspectionDate;             //출고검수패킹 검수일시
  
  private String releaseEmployee;            //출고 담당자
  private String releaseDate;                //출고 일시
  
  private String transferEmployee;           //출고검수패킹 담당자
  private String transferDate;               //출고검수패킹 검수일시
  
  private int orderStatus;                   //주문상태 구분
  private Integer unrelease;                 //미출고
  private boolean itemOSS;                   //제품 분류  
  //v_no가 1이면 오스템제품, v_no가 1이 아니면서 itm_oss가 1이면 오스템상품
  
}