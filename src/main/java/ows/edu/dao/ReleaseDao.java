package ows.edu.dao;

import java.util.Map;

import ows.edu.dto.Box;

public interface ReleaseDao {
	
	//rls_done = 1로 업데이트
	public int updateReleaseDone(String releaseCode);
	
	public int updateReleaseBoxQty(Box box);

}
