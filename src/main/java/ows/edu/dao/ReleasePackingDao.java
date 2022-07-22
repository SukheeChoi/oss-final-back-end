package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspection;
import ows.edu.dto.ReleasePacking;

@Mapper
public interface ReleasePackingDao {
	
	//단순 가져오기
	public List<ReleasePacking> select();
	
	//데이터 총 개수
	public int count();
	//페이징처리
	public List<ReleasePacking> selectByPage(Pager pager);
	
	//스캔했을 때, 박스별품목정보 띄어주는 용도 
	public List<ReleasePacking> selectByOrderNo(@Param("orderNo") String orderNo, @Param("index") int index);
	
	//페이징처리&필터
	public List<ReleasePacking> selectByFilterPage(Pager pager);
	
	//오른쪽에 띄어줄 주문에 대한 상세 정보
	public List<ReleasePacking> selectByReleaseCode(String releaseCode);
	
	//스캔
	public List<ReleasePacking> scan(@Param("code") String releaseCode, @Param("kind") String kind);

	
}
