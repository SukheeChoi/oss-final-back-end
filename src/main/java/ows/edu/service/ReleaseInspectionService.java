package ows.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ows.edu.dao.ReleaseInspectionViewDao;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspection;
import ows.edu.dto.ReleaseInspectionView;

@Service
public class ReleaseInspectionService {
	
	@Autowired
	ReleaseInspectionViewDao releaseInspectionViewDao;

	private String releaseCode ="";
	
	public List<ReleaseInspectionView> select(){
		List<ReleaseInspectionView> list = releaseInspectionViewDao.select();
		int count = 0;
		
		for(int i=0; i<list.size(); i++ ) {
			if(!list.get(i).getReleaseCode().equals(releaseCode)) {
				releaseCode = list.get(i).getReleaseCode();
				count++;
			}
			list.get(i).setNo(count);
		}
		return list;
	}
	
	public List<ReleaseInspectionView> selectByFilterPage(Pager pager){
		List<ReleaseInspectionView> list = releaseInspectionViewDao.selectByFilterPage(pager);
		
		int count = 0;
		
		for(int i=0; i<list.size(); i++ ) {
			if(!list.get(i).getReleaseCode().equals(releaseCode)) {
				releaseCode = list.get(i).getReleaseCode();
				count++;
			}
			list.get(i).setNo(count);
		}		
		
		return list;
	}
	
	public int count() {
		return releaseInspectionViewDao.count();
	}
	
	public List<ReleaseInspectionView> selectByPage(Pager pager){
		return releaseInspectionViewDao.selectByPage(pager);
	}
	
	public List<ReleaseInspectionView> selectByOrderNo(int orderNo){
		return releaseInspectionViewDao.selectByOrderNo(orderNo);
	}

}
