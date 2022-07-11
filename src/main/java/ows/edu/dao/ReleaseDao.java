package ows.edu.dao;

import java.util.Map;

public interface ReleaseDao {
	
	//rls_done = 1로 업데이트
	public int updateReleaseDone(Map<String, Object> map);
	
	//출고번호 전체 개수
	public int count();
}
