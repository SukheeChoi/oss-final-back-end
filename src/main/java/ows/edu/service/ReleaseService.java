package ows.edu.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReleaseService {
	//rls_done = 1로 업데이트
	public int updateReleaseDone(Map<String, Object> map);

}
