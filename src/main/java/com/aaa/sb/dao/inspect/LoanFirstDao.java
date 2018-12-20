package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:LoanFirstDao
 * discription:
 * author:cmq
 * createTime:2018-12-17 10:00
 */
public interface LoanFirstDao {
    /**
     * 获得贷款初审数据
     * @return
     */
    @Select("<script> select a.PID,a.PNAME,b.LOAN_MONEY,b.LOAN_PERIODS,b.LOAN_RATE,b.LOAN_BANK,b.LOAN_REPAY,b.CTIME,b.STATUS \n" +
            "from tb_person_info a,tb_loanappval b where a.PID=b.PID\n" +
            "<where><if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></where></script>")
    List<Map> getList();

    /**
     * 信息审查
     * @param map
     * @return
     */
    @Select(value = "select a.*,b.*,c.*,d.* from tb_person_info a\n" +
            "join tb_paccountutil b on a.pid=b.pid\n" +
            "join tb_unit c on  b.unid=c.unid\n" +
            "join tb_loanappval d on a.pid=d.pid\n" +
            "where a.pid=#{PID}")
    List<Map> reList(Map map);

    /**
     * 根据共同借款人姓名查询信息
     * @param map
     * @return
     */
    @Select(value = "select * from tb_person_info where pname=#{TOGETHERZH}")
    List<Map> rethList(Map map);
}
