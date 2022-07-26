package ows.edu.controller;

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
import ows.edu.dto.AfterPickingFilter;
import ows.edu.dto.Pager;
import ows.edu.service.ReleaseInspectionService;

@RestController
@RequestMapping("/afterPicking")
@Log4j2
public class AfterPickingController {

	@Resource
	ReleaseInspectionService releaseInspectionService;
	
	
	/**
	 * '출고검수/패킹 진행'페이지 상단 현황 정보 응답.
	 * 주문건수, 피킹지시건수, 출고검수+패킹 건수, 미출고건수
	 * 출고검수 중에서 긴급/일반 건수
	 * 
	 * @author 최숙희
	 * @return Map<String, Object> 현황 정보
	 */
	@GetMapping("/summary")
	public Map<String, Object> getSummary() {
		Map<String, Object> summaryMap = releaseInspectionService.getSummary();
		
		return summaryMap;
	}
	
	/**
	 * @author 최숙희
	 * @param afterPickingFilter 출고검수/패킹 진행 목록 필터링에 필요한 모든 정보
	 * @return Map<String, Object> 목록 전체를 기준으로 출고검수/패킹 담당자
	 */
	@PostMapping("/assigneeList")
	public Map<String, Object> getAssigneeList(@RequestBody AfterPickingFilter afterPickingFilter) {
		Map<String, Object> map = new HashMap<>();
		List<String> list = releaseInspectionService.getAssigneeList(afterPickingFilter);
		map.put("list", list);
		
		return map;
	}

	/**
	 * @author 최숙희
	 * @param afterPickingFilter 출고검수/패킹 진행 목록 필터링에 필요한 모든 정보
	 * @return Map<String, Object> 처리 진행중인 출고검수/패킹 단계의 목록
	 */
	@PostMapping("/")
	public Map<String, Object> getList(@RequestBody AfterPickingFilter afterPickingFilter) {
		
		if(afterPickingFilter.getPageNo() == null || afterPickingFilter.getPageNo() == 0) {
			afterPickingFilter.setPageNo(1);
		}
		if(afterPickingFilter.getPageSize() == null || afterPickingFilter.getPageSize() == 0) {
			afterPickingFilter.setPageSize(18);
		}

		Map<String, Object> map = new HashMap<>();
		map = releaseInspectionService.getAfterPickingList(afterPickingFilter);

		return map;
	}

	
}
