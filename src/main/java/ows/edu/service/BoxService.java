package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Box;

@Transactional
public interface BoxService {
	public int insert(List<Box> boxArrays);

	public int update(List<Box> boxArrays);

	/**
	 * 박스에 포함된 물품의 총 개수를 조회
	 * 
	 * @author 신현주
	 * @param releaseCode 출고번호
	 * @return 박스에 포장된 물품의 총 개수
	 */
	public int selectSumItemQty(String releaseCode);
}