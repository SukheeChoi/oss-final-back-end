package ows.edu.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ows.edu.dto.CombineShipping;

@Transactional
public interface CombineShippingService {

	public List<CombineShipping> getDeliveryList();
}
