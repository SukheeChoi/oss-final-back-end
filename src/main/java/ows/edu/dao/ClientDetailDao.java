package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.ClientDetail;

@Mapper
public interface ClientDetailDao {
 public List<ClientDetail> search();
 public List<ClientDetail> searchByClientNo();
 public ClientDetail searchByClientDetailNo();
}
