package ows.edu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.Box;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionFilter;
import ows.edu.dto.ReleasePacking;
import ows.edu.service.BoxService;
import ows.edu.service.OrderService;
import ows.edu.service.ReleaseInspectionService;
import ows.edu.service.ReleaseService;

@RestController
@RequestMapping("/releaseInspection")
@Log4j2
public class ReleaseInspectionController {

	@Resource
	ReleaseInspectionService releaseInspectionService;
	
	@Resource
	OrderService orderService;
	
	@Resource
	BoxService boxService;
	
	@Resource
	ReleaseService releaseService;
	
	@GetMapping("/get")
	public List<ReleasePacking> getReleaseInspectionList(){
		List<ReleasePacking> list = new ArrayList<>();
		list = releaseInspectionService.select();
		return list;
	}
	
	@PostMapping("/getFilterList")
	public Map<String, Object> getFilterList(@RequestBody ReleaseInspectionFilter apiData){ //String[] newGroup

		String[] newGroup = apiData.getEmptyGroup();	//긴급, 일반
		int pageSize = apiData.getPageSize();  			//pageSeize <= rowsPerPage
		int pageNo = apiData.getPageNo();
		
		log.info("체크된 필터 : "+Arrays.toString(newGroup));
		
		//전체 데이터 개수
		int totalCount = 0;
		
		if(newGroup.length ==2) {
			totalCount = orderService.pickingDoneCount();
		}else if(newGroup[0].equals("긴급")){
			totalCount = orderService.pickDnEmergencyCount();
		}else {
			totalCount = orderService.pickDnCommonCount();
		}
		
		orderService.pickingDoneCount();
		log.info(totalCount);
		
		//페이저 & 필터 설정
		Pager pager = new Pager(pageSize, 10, totalCount, pageNo); 
		log.info("넘어온 pageNo 정보 : " + pageNo);

		pager.setNewGroup(newGroup);
				
		List<ReleasePacking> list = new ArrayList<>();
		list = releaseInspectionService.selectByFilterPage(pager);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("data", list);
		map.put("totalCount", totalCount);
		map.put("pageNo", pageNo);
		
		return map;
	}
	
	//왼쪽 배송 list
	@GetMapping("/list")
	public Map<String, Object> getList(@RequestParam(defaultValue="1") int pageNo){
		//페이저 
		int totalRows = releaseInspectionService.count();
		Pager pager = new Pager(3, 10, totalRows, pageNo);
		//paging 처리된 list 가져오기
		List<ReleasePacking> list = new ArrayList<>();
		list = releaseInspectionService.selectByPage(pager);	
		
		Map<String, Object> map = new HashMap<>();
		map.put("RIList", list);
		map.put("pager", pager);
		return map;
	}
	
	//검수수량, 미출고 수량 업데이트
	@GetMapping("/RIQtyUpdate")
	public int releaseInspectionQtyUpdate(@RequestParam String releaseCode, @RequestParam String barCode) {
		log.info("====================");
		log.info(releaseCode);
		log.info("releaseCode >> " + releaseCode);
		log.info("barCode >> " + barCode);
		int success = releaseInspectionService.releaseInspectionQtyUpdate(releaseCode, barCode);
		log.info(success);
		
		return success;
	}
	
	@GetMapping("/unRleaseQtyUpdate")
	public int unRleaseQtyUpdate(@RequestParam String releaseCode, @RequestParam String barCode) {		
		log.info("unRleaseQtyUpdate");
		log.info("releaseCode >> " + releaseCode);
		log.info("barCode >> " + barCode);
		int success = releaseInspectionService.unRleaseQtyUpdate(releaseCode, barCode);
		log.info(success);
		
		return success;
	}
	
	//스캔
	@GetMapping("/scanBtnClick")
	public List<ReleasePacking> scan(@RequestParam String code, @RequestParam String kind) {
		log.info("scanBtnClick");
		log.info(code);
		log.info(kind);
		List<ReleasePacking> releasePackingList = releaseInspectionService.scan(code,kind);
		log.info(releasePackingList);
		return releasePackingList;
	}
	
	//박스별품목정보(총검수수량)
	@GetMapping("/selectByReleaseCode")
	public List<ReleasePacking> selectByReleaseCode(@RequestParam String releaseCode){
		List<ReleasePacking> list = new ArrayList<>();
		list = releaseInspectionService.selectByReleaseCode(releaseCode);
		return list;
	}
	
	//박스별품목정보
	@GetMapping("/getBoxInfobyOrdNo")
	public List<ReleasePacking> selectByOrderNo(@RequestParam String orderNo, @RequestParam int index){
		log.info("===========================================/getBoxInfobyOrdNo");
		log.info("orderNo >> " + orderNo);
		log.info("index >> " + index);
		
		List<ReleasePacking> list = new ArrayList<>();
		list = releaseInspectionService.selectByOrderNo(orderNo, index);
		return list;
	}
	
	//현황 (주문건: 1360건 | 피킹완료건: 530건(긴급5건/일반525건) | 출고검수/패킹건: 0건(긴급3건/일반125건))
	@GetMapping("/releaseInspectionStatus")
	public Map<String, Integer> getTotal(){
		
		Map<String, Integer> map = new HashMap<>();
		int count = orderService.count();
		int pickingDoneCount 			= orderService.pickingDoneCount();
		int pickDnCommonCount 			= orderService.pickDnCommonCount();
		int pickDnEmergencyCount 		= orderService.pickDnEmergencyCount();
		int rlsInspPackingCount 		= orderService.rlsInspPackingCount();
		int rlsInspPackCommonCount 		= orderService.rlsInspPackCommonCount();
		int rlsInspPackEmergencyCount 	= orderService.rlsInspPackEmergencyCount();
		
		map.put("count", count);
		map.put("pickingDoneCount", pickingDoneCount);
		map.put("pickDnCommonCount", pickDnCommonCount);
		map.put("pickDnEmergencyCount", pickDnEmergencyCount);
		map.put("rlsInspPackingCount", rlsInspPackingCount);
		map.put("rlsInspPackCommonCount", rlsInspPackCommonCount);
		map.put("rlsInspPackEmergencyCount", rlsInspPackEmergencyCount);
		
		return map;
	}
	
	// n번째 박스 패킹 완료 버튼 클릭 -> 업데이트로 수정
	@PostMapping("/updateBoxTable")
	public int packing(@RequestBody List<Box> boxArrays) {
		log.info("====================================");
		log.info(boxArrays);
		int updateCount1 = boxService.update(boxArrays);
		int updateCount2 = releaseService.updateReleaseBoxQty(boxArrays.get(0));
		return updateCount1+updateCount2;
	}
	
	// 박스 추가 버튼 클릭
	@PostMapping("/insertToBoxTable")
	public int insertToBoxTable(@RequestBody List<Box> boxArrays) {
		log.info(boxArrays);
		int result = boxService.insert(boxArrays);
		return result;
	}
	
	// 패킹 최종 완료
	@GetMapping("/packingDone")
	public int packing(@RequestParam String orderNumber) {
		log.info("패킹 최종 완료");
		return 0;
	}
	
	
	
}
