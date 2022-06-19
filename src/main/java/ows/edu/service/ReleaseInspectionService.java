package ows.edu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ReleaseInspectionViewDao;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;

@Service
@Log4j2
public class ReleaseInspectionService {
	@Resource
	ReleaseInspectionViewDao releaseInspectionViewDao;
	
	public List<ReleaseInspectionView> select(){
		return releaseInspectionViewDao.select();
	}
	
	public int count() {
		return releaseInspectionViewDao.count();
	}
	
	public List<ReleaseInspectionView> selectByPage(Pager pager){
		return releaseInspectionViewDao.selectByPage(pager);
	}
}
