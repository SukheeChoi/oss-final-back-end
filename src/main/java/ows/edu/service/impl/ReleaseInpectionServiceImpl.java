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
import ows.edu.dao.ReleaseInspectionViewDao;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;
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
	ReleaseInspectionViewDao releaseInspectionViewDao;
	@Resource
	AfterPickingViewDao afterPickingViewDao;
	
	@Override
	public Map<String, Object> getSummary() {
		// 전반적으로 TB_ORD의 STS값을 활용하는 방식으로 변경할 것.
		// (현재는 데이터가 정확하지 않은 상태라 JOIN을 이용.)
		
		Map<String, Object> map = new HashMap<>();
		// 주문건수 조회.
		int progressOrderNo  = orderDao.countProgressOrder();
		map.put("progressOrderNo", progressOrderNo);
		// 피킹지시 건수 조회.(ORD_PRC_ORD IS NULL && )
		int pickingDirectionNo = pickingDirectionDao.countPickingDirection();
		map.put("pickingDirectionNo", pickingDirectionNo);
		// 출고검수 + 패킹 건수 조회.(출고검수가 완료되어야 패킹을 진행하기 때문에 출고검수 건수를 사용.)
		int releaseInspectionNo = releaseInspectionDao.countReleaseInspection();
//		int packingNo = packingDao.countPackingNo();
		map.put("releaseInspectionNo", releaseInspectionNo);
		// 미출고 건수 조회.(
		// 	이전 단계의 미출고가 0건이 되어야 다음 단계로 진행할 수 있기 때문에
		// 	현재 처리단계를 확인하지 않고, 출고검수 미출고와 패킹의 미출고 건수를 합함.
		//	null 주의. 
		// )
		int releaseInspectionUnreleased = releaseInspectionDao.sumUnreleased();
		int packingUnreleased = packingDao.sumUnreleased();
		int unreleasedNo = releaseInspectionUnreleased + packingUnreleased;
		map.put("unreleasedNo", unreleasedNo);
		
		// 긴급 건수 조회.
		int expressShippingNo = releaseInspectionDao.countExpressShipping();
		map.put("expressShippingNo", expressShippingNo);
		// 일반 건수 조회.
		int normalShippingNo = releaseInspectionDao.countNormalShipping();
		map.put("normalShippingNo", normalShippingNo);
		
		
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
//	public List<AfterPicking> getAfterPickingList(
	public List<HashMap<String, String>> getAfterPickingList(
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
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		
//		List<AfterPickingView> ap = afterPickingViewDao.selectAll();
		List<HashMap<String, String>> ap = afterPickingViewDao.selectAll(
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
		HashMap<String, String> map = new HashMap<>();
		for(int i=0; i<ap.size(); i++) {
			map = ap.get(i);
			
			// 출고요청서 출력일시 컬럼 값을 '담당자성명+\n+(+mm:dd+ +hh:MM+)'형태로 만듦.
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
			
			// 거래명세서 출력일시 컬럼 값을 '담당자성명+\n+(+mm:dd+ +hh:MM+)'형태로 만듦.
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
			
			// 검수일시 컬럼 값을 'mm:dd+ +hh:MM'형태로 만듦.
			map.replace(
				"RI_DT"
				, String.valueOf( ap.get(i).get("RI_RCPT_PRT_DT")).substring(5, 10) 
				+ " " 
				+ String.valueOf( ap.get(i).get("RI_RCPT_PRT_DT")).substring(11, 16) 
			);
			
			ap.set(i, map);
			log.info("new String.valueOf( ap.get(i).get(\"RI_RLS_PRT_DT\")) : " + String.valueOf( ap.get(i).get("RI_RLS_PRT_DT")));

			// 다음번 for문 순회하기 전에 HashMap 객체 초기화.
			map = null;
		}
//		Iterator<String> iterator = ap.get(0).keySet().iterator();
//		while(iterator.hasNext()) {
//			String key = iterator.next();
//			String value = String.valueOf(ap.get(0).get(key));
//			log.info("key : " + key);
//			log.info("value : " + value);
//		}
		///
		
		// 불필요해진 코드 주석처리.
//		for(int i=0; i<list.size(); i++) {
//			// packing의 미출고가 null인 경우에는, 출고검수의 미출고를 검토.
//			if(list.get(i).getStrPackingUnreleased().equals(" ")) {
//				// 출고검수 미출고가 null인지 아닌지 검토.
//				// 출고검수 미출고가 null인 경우: 출고검수 진행중. 미완료 상태.
//				// strAfterPickingUnreleased에 공백문자 할당.
//				if(list.get(i).getStrReleaseInspectionUnreleased().equals(" ")) {
//					// 패킹 미출고 == null && 출고검수 미출고 == null인 경우.
//					// 아직 출고검수 미완료.
//					// strAfterPickingUnreleased에 공백문자 할당.
//					list.get(i).setStrAfterPickingUnreleased(" ");
//				} else {
//					// 패킹 미출고 == null && 출고검수 미출고 != null
//					// 출고검수에서 미출고로 인해 작업중단 됐거나 미출고X+패킹 진행중(미완료) 상태.
//					// strAfterPickingUnreleased에 출고검수 미출고 값 그대로 할당.
//					list.get(i).setStrAfterPickingUnreleased(list.get(i).getStrReleaseInspectionUnreleased());
//				}
//				
//			} else {// 이외에 경우에는 패킹의 미출고 값을 그대로 strAfterPickingUnreleased에 할당.
//				// packing의 미출고가 null이 아닌 경우: 출고검수는 완료(미출고 0)인 상태.
//				// strAfterPickingUnreleased에 패킹의 미출고값 할당.
//				list.get(i).setStrAfterPickingUnreleased(list.get(i).getStrPackingUnreleased());
//			}
//			
//			log.info("list.get(i) : " + list.get(i));
//		}
		return ap;
	}

	
	//현주=============================================================================
	
	private String releaseCode ="";
	
	public List<ReleaseInspectionView> select(){
		List<ReleaseInspectionView> list = releaseInspectionViewDao.select();
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
	
	public List<ReleaseInspectionView> selectByFilterPage(Pager pager){
		List<ReleaseInspectionView> list = releaseInspectionViewDao.selectByFilterPage(pager);
		
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
	
	public int count() {
		return releaseInspectionViewDao.count();
	}
	
	public List<ReleaseInspectionView> selectByPage(Pager pager){
		return releaseInspectionViewDao.selectByPage(pager);
	}
	
	public List<ReleaseInspectionView> selectByOrderNo(int orderNo){
		return releaseInspectionViewDao.selectByOrderNo(orderNo);
	}

	
}
