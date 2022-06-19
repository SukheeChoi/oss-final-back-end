package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;

@Mapper
public interface ReleaseInspectionViewDao {
	public List<ReleaseInspectionView> select();
	public int count();
	public List<ReleaseInspectionView> selectByPage(@Param("pager") Pager pager);
}
