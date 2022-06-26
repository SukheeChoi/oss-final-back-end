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
import ows.edu.dto.Client;
import ows.edu.service.ClientService;


@RestController
@RequestMapping("/client")
@Log4j2
public class ClientController {
	
	@Resource
	ClientService clientService;
	
	//거래처 목록 불러오기
	@GetMapping("/")
	public Map<String, Object> getClientNameList() {
		Map<String, Object> map = new HashMap<>();
		List<Client> list = clientService.selectList();
		map.put("list", list);
		log.info("clientcontroller - map: " + map);
		return map;
	}
	
	@GetMapping("/sts")
	public Map<String, Object> statusCnt(@RequestParam(value="status", defaultValue="2") int status) {	
		int statusCnt =clientService.statusCnt(status);
		
		Map<String, Object> sMap = new HashMap<>();
		sMap.put("statusCnt", statusCnt);
		return sMap;
	}
	
}
