package ows.edu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.CombineShipping;
import ows.edu.service.CombineShippingService;

@RestController
@RequestMapping("/combineShipping")
@Log4j2
public class CombineShippingController {

	@Resource
	CombineShippingService combineShippingService;
	
	@GetMapping("/getDeliveryList")
	public List<CombineShipping> getReleaseInspectionList(){
		List<CombineShipping> list = new ArrayList<>();
		list = combineShippingService.getDeliveryList();
		return list;
	}
	
	
}
