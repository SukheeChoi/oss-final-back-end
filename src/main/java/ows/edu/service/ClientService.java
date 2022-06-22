package ows.edu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import ows.edu.dao.ClientDao;
import ows.edu.dto.Client;
import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;

@Service
@Log4j2
public class ClientService {
	@Resource
	ClientDao clientDao;
	
//	public List<Client> getlist(String clientName) {
//		List<Client> list = new ArrayList<>();
//	    list.addAll(clientDao.select(clientName));
////	    log.info("ClientService : " + clientName);
//	    log.info("ClientService : " + list);
//	    return list;
//	};
	
	public int count() {
		return clientDao.count();
	}
	
	public List<String> selectByPage(Pager pager){
		return clientDao.selectByPage(pager);
	}
}
