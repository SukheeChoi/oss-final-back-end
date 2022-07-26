package ows.edu.dao;

import ows.edu.dto.Box;

public interface BoxDao {
   //'박스추가'버튼 눌렀을 때
   public int insert(Box box);
   
   //'박스n패킹완료'버튼 눌렀을 때
   public int update(Box box);
   
   /**
    * 박스에 포함된 물품의 총 개수를 조회하는 mapper와 연결
    * 
    * @author 신현주
    * @param rlsCode 출고번호
    * @return 박스에 포장된 물품의 총 개수
    */
   public int selectSumItemQty(String releaseCode);
}