package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Pager;

@Mapper
public interface OrderDao {
	// ordercheckview의 전체 행 수(orderitemno 갯수) 조회.
	public int count();
	// controller의 parameter인 pageNo와 count()를 이용해서 만든 Pager로
	// pageNo에 해당하는 '주문확인'페이지의 내용을 받아옴.
	public List<String> selectByPage(Pager pager);
}
