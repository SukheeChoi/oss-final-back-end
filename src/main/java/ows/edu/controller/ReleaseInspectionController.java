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
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspection;
import ows.edu.dto.ReleaseInspectionView;
import ows.edu.service.ReleaseInspectionService;

@RestController
@RequestMapping("/releaseInspection")
@Log4j2
public class ReleaseInspectionController {

	@Resource
	ReleaseInspectionService releaseInspectionService;
	
	@GetMapping("/get")
	public List<ReleaseInspectionView> getReleaseInspectionList(){
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.select();
		return list;
	}
	
	@PostMapping("/getFilterList")
	public List<ReleaseInspectionView> getFilterList(@RequestBody String[] newGroup){
		log.info(newGroup);
		System.out.println(Arrays.toString(newGroup));
		
		//페이저 & 필터 설정
		Pager pager = new Pager(3, 10, 10, 1);
		pager.setNewGroup(newGroup);
		System.out.println(Arrays.toString(pager.getNewGroup()));
		
		//서비스 단에서 페이저, 필터 처리된 정보 가져오기
		//list = releaseI
		
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.selectByFilterPage(pager);
		return list;
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
	
	
	////검수수량, 미출고 수량 업데이트
	@PostMapping("/RIQtyUpdate")
	public int releaseInspectionQtyUpdate(@RequestBody HashMap<String, String> codes) {
		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("rleaseCode", releaseCode);
//		map.put("barcode", barcode);
		
		System.out.println("releaseInspectionQtyUpdate 제발요 ㅜㅜ");
		System.out.println(codes);
		
		int success = releaseInspectionService.releaseInspectionQtyUpdate(codes);
		System.out.println(success);
		
		return success;
	}
	
	@PostMapping("/unRleaseQtyUpdate")
	public int unRleaseQtyUpdate(@RequestBody HashMap<String, String> codes) {
		
		System.out.println("unRleaseQtyUpdate");
		int success = releaseInspectionService.unRleaseQtyUpdate(codes);
		
		return success;
	}
	
	//스캔
	@GetMapping("/scanBtnClick")
	public ReleaseInspection scan(@RequestParam String releaseCode) {
		ReleaseInspection releaseInspection = releaseInspectionService.scan(releaseCode);
		return releaseInspection;
	}
	
	//현황 (주문건: 1360건 | 피킹완료건: 530건(긴급5건/일반525건) | 출고검수/패킹건: 0건(긴급3건/일반125건))
	@GetMapping("/releaseInspectionStatus")
	public Map<String, Object> getTotal(){
		
		
		Map<String, Object> map = new HashMap<>();
		
		
		
		return map;
	}
}
