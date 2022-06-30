package ows.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ows.edu.dto.OrderFilter;
import ows.edu.dto.OrderView;
import ows.edu.dto.Pager;

@Mapper
public interface OrderViewDao {
  public List<OrderView> select();
  public List<OrderView> selectByFilter(@Param("of") OrderFilter orderfilter, @Param("pager") Pager pager);
  public List<OrderView> selectByPage(Pager pager);
  
  public int countAll();
  public int countOsstem();
  public int countVendorPlus();
  public int countVendorDir();
  public int countunleased();
  
  public int count(@Param("of") OrderFilter orderFilter);
  
  /*  
  CREATE VIEW ORD_VIEW
  AS
  select orditm.OI_NO
     , vnd.V_NO
     , itm.ITM_OSS
     , date_format(ord.ORD_DT, '%m-%d %H:%i') as ORD_DT
       , ord.ORD_NO
       , clt.CLT_NAME
     , itm.ITM_NAME
       , itm.ITM_CODE
       , orditm.OI_QTY
       , ord.ORD_SHP_CAT
       , IFNULL(vnd.V_NAME , '오스템제품') as V_NAME
       , pd.PD_ATM
       , date_format(pd.PD_DT, '%m-%d %H:%i') as PD_DT
       , pd.PD_QTY
       , pd.PD_URLS
       , ord.ORD_SHP_WAY
       , date_format(cs.CS_ORD_CHK_DT, '%m-%d %H:%i') as CS_ORD_CHK_DT
       , cs.CS_RCV_QTY
       , date_format(cs.CS_RLS_SCH_DT, '%m-%d %H:%i') as CS_RLS_SCH_DT
       , date_format(cs.CS_RCV_DT, '%m-%d %H:%i') as CS_RCV_DT
  from TB_ORD_ITM orditm
  LEFT JOIN TB_ORD ord ON ord.ORD_NO = orditm.ORD_NO
  JOIN TB_CLT clt ON ord.CLT_NO = clt.CLT_NO
  JOIN TB_ITM itm ON orditm.ITM_CODE = itm.ITM_CODE
  LEFT JOIN TB_VND vnd ON itm.V_NO = vnd.V_NO
  JOIN TB_PIC_DRC pd ON orditm.OI_NO = pd.OI_NO
  LEFT JOIN TB_CMB_SHP cs ON orditm.OI_NO = cs.OI_NO
  order by ORD_DT desc, ORD_NO, orditm.OI_NO;
  
  */
}
