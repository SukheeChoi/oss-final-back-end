package ows.edu.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ows.edu.dto.LabelingWorkTime;

@Mapper
public interface LabelingWorkTimeDao {
  public List<LabelingWorkTime> searchbyToday(Date labelingWorkTimeDate); //당일 전체 담당자 작업 조회
}
