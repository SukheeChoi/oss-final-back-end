package ows.edu.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.AfterPickingFilter;
import ows.edu.dto.Pager;

@Mapper
public interface AfterPickingViewDao {

	public int selectCountAll(AfterPickingFilter afterPickingFilter);
	
	public List<HashMap<String, String>> selectAll(
			@Param("afterPickingFilter") AfterPickingFilter afterPickingFilter
			, @Param("pager") Pager pager
		);

	public List<String> selectReleaseInspectionEmpNm(AfterPickingFilter afterPickingFilter);

}
