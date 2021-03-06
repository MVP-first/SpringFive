package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:FinalDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-24 11:45
 */

public interface FinalDao {
    /**
     * 统计每月贷款信息
     * @return
     */
   @Select("select sum(loan_money) as money,substr(to_char(c_time, 'yyyy-mm-dd'),0,7) as yuefen FROM tb_repay GROUP BY substr(to_char(c_time, 'yyyy-mm-dd'),0,7)")
    List<Map> DaiKuanXinxi();

    /**
     * 统计每月还款信息
     * @return
     */
   @Select("select sum(repay_money) as money ,substr(repayed_date,0,7) as yuefen FROM tb_repay_record  GROUP BY substr(repayed_date,0,7)")
    List<Map> huanKuanXinxi();
    /**
     * 统计每月汇缴金额
     */
    @Select("select nvl(sum(uaowemonery),0) as money,to_char(ubdcreatedate, 'yyyy-mm') as yuefen FROM tb_unitbizdetail GROUP BY to_char(ubdcreatedate, 'yyyy-mm')\n")
    List<Map> meiYueJinE();
    /**
     * 统计每月提取金额
     */
    @Select("select sum(extract_money) as money ,substr(to_char(appl_time, 'yyyy-mm-dd'),0,7)  as yuefen FROM tb_extract_application  GROUP BY substr(to_char(appl_time, 'yyyy-mm-dd'),0,7)\n")
    List<Map> meiYueTiqu();
}
