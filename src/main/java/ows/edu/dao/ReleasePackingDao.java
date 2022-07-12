package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.Pager;
import ows.edu.dto.ReleasePacking;

@Mapper
public interface ReleasePackingDao {
	// 객체 1개만 파라미터로 넘길거라면 @Param은 필요 없습니다~
	// 객체와 기본타입을 동시에 파라미터로 넘길 때 유용한 어노테이션으로 알고 있어요~
	// 필요에 따라서 @Param을 사용한 경우에는 객체 생성해서 사용하듯이 .필요합니다!
	// ex. 매퍼에서 pager객체 내부의 필드를 사용하고 싶다면, #{pager.startRowNo} 이런식!
	
	//단순 가져오기
	public List<ReleasePacking> select();
	
	//데이터 총 개수
	public int count();
	//페이징처리
	public List<ReleasePacking> selectByPage(Pager pager);
	
	//오른쪽에 띄어줄 주문에 대한 상세 정보
	public List<ReleasePacking> selectByOrderNo(int orderNo);
	
	//페이징처리&필터
	public List<ReleasePacking> selectByFilterPage(Pager pager);
	
	//스캔했을 때, 박스별품목정보 띄어주는 용도
	public List<ReleasePacking> selectByReleaseCode(String releaseCode);
	
}
