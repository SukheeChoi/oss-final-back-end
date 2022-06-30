package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.Box;

@Transactional
public interface BoxService {
	public int insert(List<Box> boxArray);
}
