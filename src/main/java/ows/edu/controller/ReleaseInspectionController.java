package ows.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.Pager;
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
	
	@GetMapping("/getFilterList")
	public List<ReleaseInspectionView> getFilterList(List<String> newGroup){
		log.info(newGroup);
		List<ReleaseInspectionView> list = new ArrayList<>();
		list = releaseInspectionService.select();
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
	
	
}
