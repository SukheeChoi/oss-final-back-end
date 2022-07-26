package ows.edu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
   
   /**
    * api통신을 통해 페이지번호와 필터링 정보를 받아와 해당 페이지에 맞는 데이터 전송
    * 
    * @author 신현주
    * @param apiData 필터링 정보, 페이지 사이즈, 현재 페이지의 정보를 포함한 객체
    */
   @PostMapping("/getFilterList")
   public Map<String, Object> getFilterList(@RequestBody ReleaseInspectionFilter apiData){ //String[] newGroup

      String[] newGroup = apiData.getEmptyGroup();   //긴급, 일반
      int pageSize = apiData.getPageSize();           //pageSeize <= rowsPerPage
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
      
      //페이저 & 필터 설정
      Pager pager = new Pager(pageSize, 10, totalCount, pageNo); 

      pager.setNewGroup(newGroup);
            
      List<ReleasePacking> list = new ArrayList<>();
      list = releaseInspectionService.selectByFilterPage(pager);
      
      Map<String, Object> map = new HashMap<String, Object>();
      
      map.put("data", list);
      map.put("totalCount", totalCount);
      map.put("pageNo", pageNo);
      
      return map;
   }
   
   /**
    * 검수수량 및 바코드 스캔 여부 업데이트
    * 
    * @author 신현주
    * @param barCode
    */
   //검수수량, 미출고 수량 업데이트
   @PutMapping("/RIQtyUpdate")
   public void releaseInspectionQtyUpdate(@RequestParam String barCode) {
      releaseInspectionService.releaseInspectionQtyUpdate(barCode);
   }
   
   /**
    * 미출고수량 및 바코드 스캔 여부 업데이트
    * 
    * @author 신현주
    * @param barCode
    */
   @PutMapping("/unRleaseQtyUpdate")
   public void unRleaseQtyUpdate(@RequestParam String barCode) {      
      releaseInspectionService.unRleaseQtyUpdate(barCode);
   }
   
   /**
    * 출고번호 혹은 바코드 번호로 해당 주문에 대한 정보를 조회하기 위해 조회 버튼을 클릭했을 때 실행.  
    * 
    * @author 신현주
    * @param code 출고번호/바코드번호
    * @param kind 출고/바코드
    * @return releasePackingList 
    */
   @GetMapping("/scanBtnClick")
   public List<ReleasePacking> scan(@RequestParam String code, @RequestParam String kind) {
      List<ReleasePacking> releasePackingList = releaseInspectionService.scan(code,kind);
      return releasePackingList;
   }
   
   /**
    * 박스1, 박스2 등을 눌렀을때, 박스별 품목정보를 리턴해주는 함수 
    * 
    * @author 신현주
    * @param releaseCode
    * @return list 박스별 품목정보
    */
   @GetMapping("/getBoxInfobyOrdNo")
   public List<ReleasePacking> selectByOrderNo(@RequestParam String orderNo, @RequestParam int index){      
      List<ReleasePacking> list = new ArrayList<>();
      list = releaseInspectionService.selectByOrderNo(orderNo, index);
      return list;
   }

   /**
    * 현황 (주문건: 1360건 | 피킹완료건: 530건(긴급5건/일반525건) | 출고검수/패킹건: 0건(긴급3건/일반125건))
    * 현황 정보를 조회하기 위한 컨트롤러
    * 
    * @author 신현주
    * @return map 주문건, 피킹완료건, 출고검수/패킹건을 포함한 현황 정보
    */
   @GetMapping("/releaseInspectionStatus")
   public Map<String, Integer> getTotal(){
      
      Map<String, Integer> map = new HashMap<>();
      int count = orderService.count();
      int pickingDoneCount          = orderService.pickingDoneCount();
      int pickDnCommonCount          = orderService.pickDnCommonCount();
      int pickDnEmergencyCount       = orderService.pickDnEmergencyCount();
      int rlsInspPackingCount       = orderService.rlsInspPackingCount();
      int rlsInspPackCommonCount       = orderService.rlsInspPackCommonCount();
      int rlsInspPackEmergencyCount    = orderService.rlsInspPackEmergencyCount();
      
      map.put("count", count);
      map.put("pickingDoneCount", pickingDoneCount);
      map.put("pickDnCommonCount", pickDnCommonCount);
      map.put("pickDnEmergencyCount", pickDnEmergencyCount);
      map.put("rlsInspPackingCount", rlsInspPackingCount);
      map.put("rlsInspPackCommonCount", rlsInspPackCommonCount);
      map.put("rlsInspPackEmergencyCount", rlsInspPackEmergencyCount);
      
      return map;
   }
   
   /**
    * n번째 박스 패킹 완료 버튼 클릭
    * 
    * @author 신현주
    * @param boxArrays
    * @return updateCount 업데이트 된 행의 개수
    */
   @PostMapping("/updateBoxTable")
   public int packing(@RequestBody List<Box> boxArrays) {
      int updateCount1 = boxService.update(boxArrays);
      int updateCount2 = releaseService.updateReleaseBoxQty(boxArrays.get(0));
      return updateCount1+updateCount2;
   }
   
   /**
    * 박스 추가 버튼 클릭
    * 
    * @author 신현주
    * @param boxArrays
    * @return count insert 된 개수와 update 된 개수의 합
    */
   @PostMapping("/insertToBoxTable")
   public int insertToBoxTable(@RequestBody List<Box> boxArrays) {
      log.info(boxArrays);
      int insertCount = boxService.insert(boxArrays);
      //출고Box수량도 업데이트
      int updateCount = releaseService.updateReleaseBoxQty(boxArrays.get(0));

      return insertCount+updateCount;
   }
   
   // 패킹 최종 완료
   /**
    * 패킹 최종 완료 버튼 클릭했을 때 출고 날짜와 주문상태를 업데이트
    * 
    * @author 신현주
    * @param orderNo
    * @return count 업데이트 한 행의 개수
    */
   @GetMapping("/packingDone")
   public int packing(@RequestParam long orderNo) {
      int updateCount = orderService.updateOrdSts(orderNo);
      return updateCount;
   }
   
   /**
    * 사용자의 요청에 따라 매개변수 출고번호를 가진 물품의 총 합을 조회하기 위해서 서비스 호출
    * 
    * @author 신현주
    * @param rlsCode 출고번호
    * @return DB에서 조회해온 패킹된 물품수량의 총합
    */
   @GetMapping("/getTotalItemQty")
   public int getTotalItemQty(@RequestParam String releaseCode) {
      int totalItemQty = boxService.selectSumItemQty(releaseCode);
      return totalItemQty;
   }
   
   
   /**
    * 사용자의 요청에 따라 매개변수인 주문번호에 해당하는 물품의 검수수량을 조회하기 위해 서비스 호출
    * 
    * @author 신현주
    * @param orderNo
    * @return
    */
   @GetMapping("/getTotalRlQty")
   public int getTotalRlQty(@RequestParam long orderNo) {
      int totalRlQty = releaseInspectionService.getTotalItemQty(orderNo);
      return totalRlQty;
   }
}