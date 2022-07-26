package ows.edu.service;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Box;

@Transactional
public interface ReleaseService {

	// 박스n패킹(=oneBoxPaking)시 rls_bx_qty 업데이트
	public int updateReleaseBoxQty(Box box);
}
