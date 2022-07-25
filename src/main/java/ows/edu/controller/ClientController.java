//김예원
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
import ows.edu.dto.Client;
import ows.edu.dto.ClientFilter;
import ows.edu.dto.ClientOrderDetail;
import ows.edu.service.ClientModalService;
import ows.edu.service.ClientService;

@RestController
@RequestMapping("/client")
@Log4j2
public class ClientController {
	@Resource
	ClientService clientService;
	
	@Resource
	private ClientModalService clientModalService;
	
	//배송구분, 주문 단계, 미출고, 주문번호, 거래처 이름로 필터링
	@PostMapping("/getFilterList")
	public Map<String, Object> getFilterList(@RequestBody ClientFilter filterList) {
		String[] shippingCategory = filterList.getShippingCategory();
		int status = filterList.getStatus();
		boolean orderUnrelease = filterList.isUnrelease();
		Long orderNo = filterList.getOrderNo();
		String clientName = filterList.getClientName();
	
		Map<String, Object> map1 = new HashMap<>();
		map1.put("shippingCategory", shippingCategory);
		map1.put("status", status);
		map1.put("orderUnrelease", orderUnrelease);
		map1.put("orderNo", orderNo);
		map1.put("clientName", clientName);

		List<Client> list1 = clientService.getReleaseList(map1);
		List<Client> list2 = clientService.getUnreleaseList(map1);
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list1", list1);
		map2.put("list2", list2);
		return map2;
	}
	
	
	//주문 단계 별 건수 요청
	@GetMapping("/sts")
	public Map<String, Object> statusCnt() {	
		List<Integer> statusCnt = clientService.getstatusCnt();
		
		Map<String, Object> map = new HashMap<>();
		map.put("statusCnt", statusCnt);
		return map;
	}
	
	//미출고 건수 조회
	@GetMapping("/unreleaseCnt")
	public int unreleaseCnt() {
		int unreleaseCnt = clientService.unreleaseCnt();
		return unreleaseCnt;
	}
	
	/**
	 * 주문이력 정보를 반환함
	 * 
	 * @author 이동현
	 * @param clientNo 고객번호
	 * @param orderNo 주문번호
	 * @return 거래처 정보, 진행 주문 정보, 주문 이력을 반환함
	 */
	@GetMapping("/modal")
	public Map<String, Object> getModal(@RequestParam int clientNo, @RequestParam String orderNo) {
		
	  Map<String, Object> data = clientModalService.getModal(clientNo, orderNo);
	  
    return data;
	}
	
	/**
	 * 주문 이력 상세 정보를 반환함
	 * 
	 * @author 이동현
	 * @param orderNo 주문번호
	 * @return 주문 이력에 해당하는 상세 내역을 반환
	 */
	@GetMapping("/modalDetail")
	public Map<String, Object> getModalDetail(@RequestParam String orderNo) {

      List<ClientOrderDetail> clientOrderDetail = clientModalService.getClientOrderDetailByOrderNo(orderNo);
    
      Map<String, Object> map = new HashMap<>();
      map.put("clientOrderDetail", clientOrderDetail);
    return map;
  }
}
