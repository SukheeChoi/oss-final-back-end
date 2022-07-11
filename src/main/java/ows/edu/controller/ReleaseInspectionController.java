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
import ows.edu.dto.ReleaseInspection;
import ows.edu.dto.ReleaseInspectionFilter;
import ows.edu.dto.ReleaseInspectionView;
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

	private Integer[] computedStartRowNo;
	
	@GetMapping("/get")
	public List<ReleaseInspectionView> getReleaseInspectionList(){
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.select();
		return list;
	}
	
	@PostMapping("/getFilterList")
	public Map<String, Object> getFilterList(@RequestBody ReleaseInspectionFilter apiData){ //String[] newGroup

		String[] newGroup = apiData.getEmptyGroup();
		int pageSize = apiData.getPageSize();  //pageSeize <= rowsPerPage
		int pageNo = apiData.getPageNo();
		
		log.info("체크된 필터 : "+Arrays.toString(newGroup));
		
		//전체 데이터 개수
		int totalCount = releaseService.count();
		log.info(totalCount);
		
		//페이저 & 필터 설정
		Pager pager = new Pager(pageSize, 10, totalCount, pageNo); 
		log.info("넘어온 pageNo 정보 : " + pageNo);
		
		computedStartRowNo =new Integer[pageSize];
		
		//computedStartRowNo 정보
		for(int i=0; i<pageSize; i++) {
			computedStartRowNo [i] = i;
		}
		
		pager.setStartPageNo(4);
		pager.setComputedStartRowNo(computedStartRowNo);
		
		log.info("그룹의 시작 페이지 번호 : " + pager.getStartPageNo());
		pager.setNewGroup(newGroup);
				
		List<ReleaseInspectionView> list = new ArrayList<>();
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
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.selectByPage(pager);	
		
		Map<String, Object> map = new HashMap<>();
		map.put("RIList", list);
		map.put("pager", pager);
		return map;
	}
	
	@GetMapping("/listItemInfo")
	public List<ReleaseInspectionView> getlistItemInfo(int orderNo){
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.selectByOrderNo(orderNo);
		return list;
	}
	
	//검수수량, 미출고 수량 업데이트
	@GetMapping("/RIQtyUpdate")
	public int releaseInspectionQtyUpdate(String releaseCode) {
		log.info("====================");
		log.info(releaseCode);
		
		int success = releaseInspectionService.releaseInspectionQtyUpdate(releaseCode);
		log.info(success);
		
		return success;
	}
	
	@PostMapping("/unRleaseQtyUpdate")
	public int unRleaseQtyUpdate(@RequestBody String releaseCode) {
		
		log.info("unRleaseQtyUpdate");
		int success = releaseInspectionService.unRleaseQtyUpdate(releaseCode);
		
		return success;
	}
	
	//스캔
	@GetMapping("/scanBtnClick")
	public ReleaseInspection scan(@RequestParam String code, @RequestParam String kind) {
		
		ReleaseInspection releaseInspection = releaseInspectionService.scan(code,kind);
		return releaseInspection;
	}
	
	//박스별품목정보
	@GetMapping("/selectByReleaseCode")
	public List<ReleaseInspectionView> selectByReleaseCode(@RequestParam String releaseCode){
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.selectByReleaseCode(releaseCode);
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
	
	// n번째 박스 패킹 완료 버튼 클릭
	@PostMapping("/packing")
	public int packing(@RequestBody List<Box> boxArray) {
			
		int result1 = boxService.insert(boxArray);
		int result2 = releaseInspectionService.update(boxArray);
		
		return result1+result2;
	}
	
	// 패킹 최종 완료
	@PostMapping("/packingDone")
	public int packing(@RequestBody Map<String, Object> map) {
		log.info("패킹 최종 완료");
		//출고검수, 출고박스수량 업데이트
		releaseService.updateReleaseDone(map);
		
		return 0;
	}
	
	
	
}
