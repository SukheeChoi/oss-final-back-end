package ows.edu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.AfterPickingViewDao;
import ows.edu.dao.BarcodeDao;
import ows.edu.dao.OrderDao;
import ows.edu.dao.OrderItemDao;
import ows.edu.dao.PickingDirectionDao;
import ows.edu.dao.ReleaseInspectionDao;
import ows.edu.dao.ReleasePackingDao;
import ows.edu.dto.AfterPickingFilter;
import ows.edu.dto.Box;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleasePacking;
import ows.edu.service.ReleaseInspectionService;

@Service
@Log4j2
public class ReleaseInpectionServiceImpl implements ReleaseInspectionService {
	
	@Resource
	ReleaseInspectionDao releaseInspectionDao;
	@Resource
	OrderItemDao orderItemDao;
	@Resource
	OrderDao orderDao;
	@Resource
	PickingDirectionDao pickingDirectionDao;
	@Resource
	ReleasePackingDao releasePackingDao;
	@Resource
	AfterPickingViewDao afterPickingViewDao;
	@Resource
	BarcodeDao barcodeDao;
	
	/**
	 * 출고검수/패킹 전체 건수 제외 모든 정보를 Mapper로부터 전달받음.
	 * 출고검수/패킹 전체 건수는 출고검수/패킹 진행단계 중 긴급, 일반 건수 각각을 합산해서 구함.
	 * DB사용 최소화 위함.
	 * @author 최숙희
	 */
	@Override
	public Map<String, Object> getSummary() {
		Map<String, Object> map = new HashMap<>();
		map = orderDao.countSummaryByStatus(4);
		// 출고검수/패킹 단계 건수는 해당 단계의 긴급/일반 배송 건수를 합산.(DB 사용 최소화)
		map.put("RLS_INSP_NUM"
				, Integer.parseInt(map.get("EX_NUM").toString())
					+
				Integer.parseInt(map.get("NM_NUM").toString())
			);

		return map;
	}
	
	/**
	 * @author 최숙희
	 */
	@Override
	public List<String> getAssigneeList(AfterPickingFilter afterPickingFilter) {
		List<String> list = afterPickingViewDao.selectReleaseInspectionEmpNm(afterPickingFilter);
		
		return list;
	}

	/**
	 * 1. afterPickingViewDao로부터 필터링된 목록의 전체 행수 받아와서 Pager객체 생성.
	 * 2. afterPickingViewDao로부터 페이지네이션 적용한 목록 받아옴.
	 * 3.  
	 * @author 최숙희
	 */
	@Override
	public Map<String, Object> getAfterPickingList(AfterPickingFilter afterPickingFilter) {
		
		// pagination을 위한 목록의 전체 행 수 조회.
		int totalRows = afterPickingViewDao.selectCountAll(afterPickingFilter);
		// pagination을 위한 Pager 객체 생성.
		Pager pager = new Pager(afterPickingFilter.getPageSize(), 10, totalRows, afterPickingFilter.getPageNo());
		
		List<HashMap<String, String>> ap = afterPickingViewDao.selectAll(
				afterPickingFilter
				, pager
			);
		
		// 비고란 합쳐서 가져온 값 할당.
		// 주문번호 DISTINCT로 가져오기
		List<Long> ordNoList = afterPickingViewDao.selectOrderNo(
				afterPickingFilter
				, pager
			);
//		log.info("ordNoList : " + ordNoList);
		// OI_NO 모으기.
		Map<String, String> noteMap = new HashMap<>();
		for(long ordNo : ordNoList) {
			List<Integer> oiNoList = orderItemDao.selectOiNo(ordNo);
			log.info("oiNoList : " + oiNoList);
			// oiNo로 OI_NT합치기.
			String concatNote = orderItemDao.selectConcatNote(oiNoList);
			log.info("concatNote : " + concatNote);
			noteMap.put(String.valueOf(ordNo), concatNote);
		}
		
		// 
		log.info("ap : " + ap);
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<ap.size(); i++) {
			map = ap.get(i);
			log.info("map : " + map);
//			log.info("ap.get(i).get(\"ORD_NO\") : " + String.valueOf( ap.get(i).get("ORD_NO") ));
			// 비고란에 바인딩할 메모 값 조정.
			if(
				ap.get(i).get("ORD_NO") != null
				&&
				ap.get(i).get("OI_NT") != null
			) {
				map.replace(
					"OI_NT"
					, noteMap.get("ordNo")
				);
			}
			
			
			
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
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("pager", pager);
		resultMap.put("list", ap);
		
		return resultMap;
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
	@Transactional
	public void releaseInspectionQtyUpdate(String barCode) {
		releaseInspectionDao.releaseInspectionQtyUpdate(barCode);
		barcodeDao.updateBarcodeDnTrue(barCode);
	}

	@Transactional
	public void unRleaseQtyUpdate(String barCode) {
		releaseInspectionDao.unRleaseQtyUpdate(barCode);
		barcodeDao.updateBarcodeDnFalse(barCode);
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

	//출고검수일 업데이트
	@Override
	public int updateReleaseInspectionDate(Long orderNo) {
		// TODO Auto-generated method stub
		return releaseInspectionDao.updateReleaseInspectionDate(orderNo);
	}
	
}
