package ows.edu.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BarcodeDao {
	public void updateBarcodeDnTrue(String barcodeNumber);
	public void updateBarcodeDnFalse(String barcodeNumber);
}
