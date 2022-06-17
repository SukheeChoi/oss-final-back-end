package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.Branch;

@Mapper
public interface BranchDao {
  public List<Branch> search();
  public Branch selectbyBranchCode(String BranchCode);
}
