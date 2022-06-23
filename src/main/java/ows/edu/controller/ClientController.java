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
import ows.edu.service.ClientService;


@RestController
@RequestMapping("/client")
@Log4j2
public class ClientController {
	
	@Resource
	ClientService clientService;
	
	//거래처 목록 불러오기
	@GetMapping("/")
	public Map<String, Object> getClientNameList(@RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		int statusCnt =clientService.status();
		int totalRows = clientService.count();
		Pager pager = new Pager(3, 10, totalRows, pageNo);

		List<String> list = new ArrayList<>();
		list = clientService.selectByPage(pager);
		
		Map<String, Object> map = new HashMap<>();
		log.info("clientcontroller : " + statusCnt);
		map.put("list", list);
		map.put("pager", pager);
		map.put("statusCnt", statusCnt);
		return map;
	}
	
}
