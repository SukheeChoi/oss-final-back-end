package ows.edu.dao;

import ows.edu.dto.Box;

public interface BoxDao {
	//'박스추가'버튼 눌렀을 때
	public int insert(Box box);
	
	//'박스n패킹완료'버튼 눌렀을 때
	public int update(Box box);
}
