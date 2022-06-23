package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.ReleaseInspection;

@Mapper
public interface ReleaseInspectionDao {
	// 출고검수/패킹 진행 페이지에 대한 전체 조회.
	public List<ReleaseInspection> selectAfterPickingList();

}
