package ows.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.BoxDao;
import ows.edu.dto.Box;
import ows.edu.service.BoxService;

@Service
@Log4j2
public class BoxServiceImpl implements BoxService{
	
	@Resource
	BoxDao boxDao;
	
	public int insert(List<Box> boxArrays) {
		int insertCount = 0;
		
		for(Box boxArray : boxArrays) {
			insertCount =+ boxDao.insert(boxArray);	//insert 되면 1씩 증가
		}
		
		return insertCount;
	}

	@Override
	public int update(List<Box> boxArrays) {
		int updateCount = 0;

		for(Box boxArray : boxArrays) {
			updateCount =+ boxDao.update(boxArray);	//업데이트 되면 1씩 증가
		}
		
		return updateCount;
	}
	
	

}
