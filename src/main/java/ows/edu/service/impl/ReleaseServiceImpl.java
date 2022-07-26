package ows.edu.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ReleaseDao;
import ows.edu.dto.Box;
import ows.edu.service.ReleaseService;

@Service
@Log4j2
public class ReleaseServiceImpl implements ReleaseService {

	@Resource
	ReleaseDao releaseDao;

	@Override
	public int updateReleaseBoxQty(Box box) {
		log.info("updateReleaseBoxQty");
		log.info(box);
		int updateCount = releaseDao.updateReleaseBoxQty(box);
		return updateCount;
	}
}
