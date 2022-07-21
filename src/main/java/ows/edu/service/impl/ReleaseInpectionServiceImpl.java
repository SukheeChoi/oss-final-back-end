package ows.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.AfterPickingViewDao;
import ows.edu.dao.OrderDao;
import ows.edu.dao.PackingDao;
import ows.edu.dao.PickingDirectionDao;
import ows.edu.dao.ReleaseInspectionDao;
import ows.edu.dao.ReleasePackingDao;
import ows.edu.dto.Box;
//kosa3.iptime.org:13000/4ever/final-back-end.git
import ows.edu.dto.Pager;
import ows.edu.dto.ReleasePacking;
import ows.edu.service.ReleaseInspectionService;

@Service
@Log4j2
public class ReleaseInpectionServiceImpl implements ReleaseInspectionService {
	
	@Resource
	ReleaseInspectionDao releaseInspectionDao;
	@Resource
	OrderDao orderDao;
	@Resource
	PickingDirectionDao pickingDirectionDao;
	@Resource
	PackingDao packingDao;
	@Resource
	ReleasePackingDao releasePackingDao;
	@Resource
	AfterPickingViewDao afterPickingViewDao;
	
	@Override
	public Map<String, Object> getSummary() {
		// 전반적으로 TB_ORD의 STS값을 활용하는 방식으로 변경할 것.
		// (현재는 데이터가 정확하지 않은 상태라 JOIN을 이용.)
		
		Map<String, Object> map = new HashMap<>();
		// 주문건수 조회.(TB_ORD에서 ORD_STS > 0 건수 집계)
		int progressOrderNum  = orderDao.countProgressOrder();
		map.put("progressOrderNum", progressOrderNum);
		// 피킹지시 건수 조회.(TB_ORD에서 ORD_STS = 2 건수 집계)
		int pickingDirectionNum= pickingDirectionDao.countPickingDirection();
		map.put("pickingDirectionNum", pickingDirectionNum);
		// 출고검수/패킹 건수 조회.(TB_ORD에서 ORD_STS = 5 건수 집계)
		// 출고검수와 패킹은 동일한 담당자가 연달아 작업한다는 가정으로, ORD_STS에서 같은 상태값(5)으로 표기.
		int releaseInspectionNum = releaseInspectionDao.countReleaseInspection();
		map.put("releaseInspectionNum", releaseInspectionNum);
		// 미출고 건수 조회.(
		// 	이전 단계의 미출고가 0건이 되어야 다음 단계로 진행할 수 있기 때문에
		// 	현재 처리단계를 확인하지 않고, 출고검수 미출고와 패킹의 미출고 건수를 합함.
		//	null 주의. 
		// )
		// TB_RLS_INSP의 ORD_NO별 SUM(RI_URLS) > 0 인 건수 집계.
		// new: 출고검수와 패킹의 진행 단계를 동일한 값으로 표시하기 때문에 더이상 합산 불필요.
		// ORD_STS = 4인 미출고값 조회.
		int unreleasedNum = releaseInspectionDao.sumUnreleased();
//		int packingUnreleased = packingDao.sumUnreleased();
//		int unreleasedNum = releaseInspectionUnreleased + packingUnreleased;
		map.put("unreleasedNum", unreleasedNum);
		
		// 긴급 건수 조회.
		int expressShippingNum = releaseInspectionDao.countExpressShipping();
		map.put("expressShippingNum", expressShippingNum);
		// 일반 건수 조회.
		int normalShippingNum = releaseInspectionDao.countNormalShipping();
		map.put("normalShippingNum", normalShippingNum);
		
		
		return map;
	}
	
	@Override
	public List<String> getAssigneeList(
			String shippingCategory
			, String shippingWay
			, String released
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName)
	{
		
		List<String> list = afterPickingViewDao
			.selectReleaseInspectionEmployeeName(
				shippingCategory
				, shippingWay
				, released
				, orderNo
				, clientName
				, shippingDestination
				, vendorName
			);
		
		return list;
	}
	
	@Override
	public int getTotalRows(
			String shippingCategory
			, String shippingWay
			, String released
			, String assignee
			, int orderNo
			, String clientName
			, String shippingDestination
			, String vendorName
			
			, int pageNo
	) {
		
		// pagination을 위한 목록의 전체 행 수 조회.
		int totalRows = afterPickingViewDao
				.selectCountAll(
						shippingCategory
						, shippingWay
						, released
						, assignee
						, orderNo
						, clientName
						, shippingDestination
						, vendorName
					);
				
		// pagination을 위한 Pager 객체 생성.
//		Pager pager = new Pager(10, 10, totalRows, pageNo);
		
		return totalRows;
	}

	@Override
//	public List<AfterPicking> getAfterPickingList(
	public List<HashMap<String, String>> getAfterPickingList(
//	public List<AfterPickingView> getAfterPickingList(
		String shippingCategory
		, String shippingWay
		, String released
		, String assignee
		, int orderNo
		, String clientName
		, String shippingDestination
		, String vendorName
		, Pager pager
	) {
		
		// pagination을 위한 목록의 전체 행 수 조회.
//		int totalRows = afterPickingViewDao
//				.selectCountAll(
//						shippingCategory
//						, shippingWay
//						, released
//						, assignee
//						, orderNo
//						, clientName
//						, shippingDestination
//						, vendorName
//					);
//				
//		// pagination을 위한 Pager 객체 생성.
//		Pager pager = new Pager(10, 10, totalRows, pageNo);
		
//		List<AfterPickingView> ap = afterPickingViewDao.selectAll();
		List<HashMap<String, String>> ap = afterPickingViewDao.selectAll(
//		List<AfterPickingView> ap = afterPickingViewDao.selectAll(
				shippingCategory
				, shippingWay
				, released
				, assignee
				, orderNo
				, clientName
				, shippingDestination
				, vendorName
				, pager
			);
		
		log.info("ap : " + ap);
		log.info("ap.size() : " + ap.size());
		
//		DateFormat df = new SimpleDateFormat("MM-dd HH:mm");
//		String strFormedDate = null;
//		for(int i=0; i<ap.size(); i++) {
//			log.info("ap.get(i) : " + ap.get(i));
//			
//			if(ap.get(i) != null && ap.get(i).getReleaseInspection() != null) {
//				if(ap.get(i).getReleaseInspection().getReleasePrintDate() != null) {
//					// 출고요청서 출력일시 포멧팅.
//					strFormedDate = null;
//					strFormedDate = df.format(ap.get(i).getReleaseInspection().getReleasePrintDate());
//					ap.get(i)
//						.setStrReleasePrintDate(
//							ap.get(i).getReleaseInspectionEmployeeName()
//							+ "\n" 
//							+ "("
//							+ strFormedDate
//							+ ")"
//						);
//				}
//				if(ap.get(i).getReleaseInspection().getReceiptPrintDate() != null) {
//					// 거래명세서 출력일시 포멧팅.
//					strFormedDate = null;
//					strFormedDate = df.format(ap.get(i).getReleaseInspection().getReceiptPrintDate());
//					ap.get(i)
//						.setStrReceiptPrintDate(
//							ap.get(i).getReleaseInspectionEmployeeName()
//							+ "\n" 
//							+ "("
//							+ strFormedDate
//							+ ")"
//						);
//				}
//				if(ap.get(i).getReleaseInspection().getReleaseInspectionDate() != null) {
//					// 검수일시 포멧팅.
//					strFormedDate = null;
//					strFormedDate = df.format(ap.get(i).getReleaseInspection().getReleaseInspectionDate());
//					ap.get(i)
//						.setStrReleaseInspectionDate(
//							strFormedDate
//						);
//				}
//				
//				// OI_URLS_QTY 컬럼 :
//				// 		RI_QTY != null && OI_URLS_QTY == null -> OI_URLS_QTY = 0
//				// 현재 데이터 문제로 0처리해줌.
//				if(
//					ap.get(i).getReleaseInspection().getReleaseInspectionQuantity() != null
//					&&
//					ap.get(i).getOrderItem().getUnreleaseQuantity() == null
//				) {
//					ap.get(i).getOrderItem().setUnreleaseQuantity(0);
//				}
//			}
//			
//			// 합배송 && 협력사 제품 -> 피킹수량 = 전달수량. 
//			//	협력사 직배송은 출고검수 테이블에 데이터가 없으므로, 합배송 여부 검사는 불필요.
//			if(ap.get(i) != null && ap.get(i).getCombineShippingPartner() != null) {
//				if(
//					ap.get(i).getItem().getItemOsstem() == false
//				) {
//					Picking pic = new Picking();
//					pic.setPickingQuantity(ap.get(i).getCombineShippingPartner().getDeliveryQuantity());
//					
//					ap.get(i).setPicking(pic);
//				}
//			}
//			 
//			 
//		}
		/////////========================================================
		
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<ap.size(); i++) {
			map = ap.get(i);
			
			// 출고요청서 출력일시 컬럼 값을 '담당자성명+\n+(+mm:dd+ +hh:MM+)'형태로 만듦.
			if(
				ap.get(i).get("RI_EMP_NAME") != null
				&&
				ap.get(i).get("RI_RLS_PRT_DT") != null
				&&
				!ap.get(i).get("RI_RLS_PRT_DT").equals(" ")
			) {
				map.replace(
						"RI_RLS_PRT_DT"
						, ap.get(i).get("RI_EMP_NAME") 
						+ "\n" 
						+ "("
						+ String.valueOf( ap.get(i).get("RI_RLS_PRT_DT")).substring(5, 10) 
						+ " " 
						+ String.valueOf( ap.get(i).get("RI_RLS_PRT_DT")).substring(11, 16) 
						+")"
					);
			}
			
			// 거래명세서 출력일시 컬럼 값을 '담당자성명+\n+(+mm:dd+ +hh:MM+)'형태로 만듦.
			if(
				ap.get(i).get("RI_EMP_NAME") != null
				&&
				ap.get(i).get("RI_RCPT_PRT_DT") != null
				&&
				!ap.get(i).get("RI_RCPT_PRT_DT").equals(" ")
			) {
				map.replace(
						"RI_RCPT_PRT_DT"
						, ap.get(i).get("RI_EMP_NAME") 
						+ "\n" 
						+ "("
						+ String.valueOf( ap.get(i).get("RI_RCPT_PRT_DT")).substring(5, 10) 
						+ " " 
						+ String.valueOf( ap.get(i).get("RI_RCPT_PRT_DT")).substring(11, 16) 
						+")"
					);
			}
			
			// 검수일시 컬럼 값을 'mm:dd+ +hh:MM'형태로 만듦.
			if(
				ap.get(i).get("RI_RCPT_PRT_DT") != null
				&&
				!ap.get(i).get("RI_RCPT_PRT_DT").equals(" ")
			) {
				map.replace(
						"RI_DT"
						, String.valueOf( ap.get(i).get("RI_RCPT_PRT_DT")).substring(5, 10) 
						+ " " 
						+ String.valueOf( ap.get(i).get("RI_RCPT_PRT_DT")).substring(11, 16) 
					);
			}
			
			// OI_URLS_QTY 컬럼 :
			// 		RI_QTY != null && OI_URLS_QTY == null -> OI_URLS_QTY = 0
			if(map.get("RI_QTY") != null && map.get("OI_URLS_QTY") == null) {
				map.replace(
					"OI_URLS_QTY"
					, String.valueOf(0)
				);
			}
			
			// 합배송 && 협력사 제품 -> 피킹수량 = 전달수량. 
			//	협력사 직배송은 출고검수 테이블에 데이터가 없으므로, 합배송 여부 검사는 불필요.
			if(String.valueOf(map.get("ITM_OSS")).equals("0")) {
				map.replace(
					"PIC_QTY"
					, String.valueOf(map.get("CSP_DLV_QTY"))
				);
			}
			
			// 출고 담당자가 배정되지 않은 상태 -> 검수수량 = " " && 미출고수량 = " "
			if(
				String.valueOf(map.get("RI_EMP_NAME")).equals(" ")
			) {
				map.replace(
					"RI_QTY"
					, " "
				);
				map.replace(
					"OI_URLS_QTY"
					, " "
				);
			}
			// 검수일시가 " " (검수 미완료 경우.) -> 출고 박스 수량 " ".
			if(String.valueOf(map.get("RI_DT")).equals(" ")) {
				map.replace(
					"RLS_BX_QTY"
					,  " "
				);
			}
			
			
			ap.set(i, map);

			// 다음번 for문 순회하기 전에 HashMap 객체 초기화.
			map = null;
		}
		
		return ap;
	}

	
	//현주=============================================================================
	
	private String releaseCode ="";
	
	public List<ReleasePacking> select(){
		List<ReleasePacking> list = releasePackingDao.select();
		int count = 0;
		
		for(int i=0; i<list.size(); i++ ) {
			if(!list.get(i).getReleaseCode().equals(releaseCode)) {
				releaseCode = list.get(i).getReleaseCode();
				count++;
			}
			list.get(i).setNo(count);
		}
		return list;
	}
	
	public List<ReleasePacking> selectByFilterPage(Pager pager){
		List<ReleasePacking> list = releasePackingDao.selectByFilterPage(pager);
		
//		int count = pager.getStartRowIndex();
//		
//		//현재 페이지 번호
//		int pageNo = pager.getPageNo();
//		
//		//이전까지의 releseCode 개수
//		
//		
//		for(int i=0; i<list.size(); i++ ) {
//			if(!list.get(i).getReleaseCode().equals(releaseCode)) {
//				releaseCode = list.get(i).getReleaseCode();
//				count++;
//			}
//			list.get(i).setNo(count);
//		}		
		
		return list;
	}
	
	public int count() {
		return releasePackingDao.count();
	}
	
	public List<ReleasePacking> selectByPage(Pager pager){
		return releasePackingDao.selectByPage(pager);
	}
	
	public List<ReleasePacking> selectByOrderNo(String orderNo, int index){
		return releasePackingDao.selectByOrderNo(orderNo, index);
	}
	
	//검수수량, 미출고 수량 업데이트
	@Override
	public int releaseInspectionQtyUpdate(String releaseCode, String barCode) {
		// TODO Auto-generated method stub
		return releaseInspectionDao.releaseInspectionQtyUpdate(releaseCode, barCode);
	}

	@Override
	public int unRleaseQtyUpdate(String releaseCode, String barCode) {
		// TODO Auto-generated method stub
		return releaseInspectionDao.unRleaseQtyUpdate(releaseCode, barCode);
	}

	//스캔
	@Override
	public List<ReleasePacking> scan(String release, String kind) {
		return releasePackingDao.scan(release, kind);
	}

	@Override
	public int update(List<Box> boxArrays) {
		int updateCount = 0;
		System.out.println("========update=========");
		for(Box boxArray : boxArrays) {
			
			Map<String, Integer> map = new HashMap<>();
//			map.put("releaseInspectionQty", boxArray.getReleaseInspectionQty());
//			System.out.println(boxArray.getReleaseInspectionQty());
			map.put("orderItemNo", boxArray.getOrderItemNo());
			System.out.println(boxArray.getOrderItemNo());
			updateCount =+ releaseInspectionDao.update(map);
		}		
		return updateCount; 
	}

	@Override
	public List<ReleasePacking> selectByReleaseCode(String releaseCode) {
		// TODO Auto-generated method stub
		return releasePackingDao.selectByReleaseCode(releaseCode);
	}
	
}
