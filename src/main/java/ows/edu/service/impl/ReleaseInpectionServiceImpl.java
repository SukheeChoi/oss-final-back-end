package ows.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ReleaseInspectionDao;
import ows.edu.dto.ReleaseInspection;
import ows.edu.service.ReleaseInspectionService;

@Service
@Log4j2
public class ReleaseInpectionServiceImpl implements ReleaseInspectionService {
	
	@Resource
	ReleaseInspectionDao releaseInspectionDao;

	@Override
	public List<ReleaseInspection> getAfterPickingList() {
		List<ReleaseInspection> list = releaseInspectionDao.selectAfterPickingList();
		return list;
	}
	
}
