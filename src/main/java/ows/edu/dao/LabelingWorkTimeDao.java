package ows.edu.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.LabelingWorkTime;

@Mapper
public interface LabelingWorkTimeDao {
  public LabelingWorkTime search(Date labelingWorkTimeDate); //당일 전체 작업 조회
  public List<LabelingWorkTime> searchAll(Date labelingWorkTimeDate); //당일 담당자 작업 조회
}
