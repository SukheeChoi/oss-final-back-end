package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.Pager;
import ows.edu.dto.ReleaseInspectionView;

@Mapper
public interface ReleaseInspectionViewDao {
	public List<ReleaseInspectionView> select();
	public int count();
// 객체 1개만 파라미터로 넘길거라면 @Param은 필요 없습니다~
// 객체와 기본타입을 동시에 파라미터로 넘길 때 유용한 어노테이션으로 알고 있어요~
// 필요에 따라서 @Param을 사용한 경우에는 객체 생성해서 사용하듯이 .필요합니다!
// ex. 매퍼에서 pager객체 내부의 필드를 사용하고 싶다면, #{pager.startRowNo} 이런식!
	public List<ReleaseInspectionView> selectByPage(Pager pager);
}
