package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.ReleaseInspection;

@Transactional
public interface ReleaseInspectionService {

	List<ReleaseInspection> getAfterPickingList();
	
}
