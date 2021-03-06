package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import java.util.Map;
import java.util.List;

public interface BaseAdjustmentDao {
    @Select("<script> select t2.UATEBALANCE,t2.DWZH,t1.UNAME,t2.UDEPOSITRATIO,t2.UPERSONRATIO,t2.UDEPOSITEDPNUM,t2.UAREMAIN,t2.UAOWEMONERY,t2.UASTATE,t2.unid,to_char(t2.uapayenddate,'yyyy-MM-dd') as uapayenddate "+
            " from tb_unit t1 left join tb_unitaccount t2 on t1.unid=t2.unid \n"+
            " <where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);
    @Select("<script>select GRZH,PNAME,BASENUMMBER,UBITNROP,INDINROP,UNITMONPAYSUM,PERMONPAYSUM from tb_paccountutil a left join tb_person_info b on a.pid = b.pid \n"+
    "<where> UNID=(select UNID from tb_unitaccount where DWZH=#{DWZH}) <if test=\"PNAME!=null and PNAME!=''\">  and PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList1(Map map);
    @Select("select a.UNID,UNAME,DWXZ,GRZH,PNAME,BASENUMMBER,PERACCSTATE,UBITNROP,INDINROP,UNITMONPAYSUM,PERMONPAYSUM FROM tb_paccountutil a left join tb_unit b on a.UNID=b.UNID \n"+
    "left join tb_person_info c on a.pid = c.pid where GRZH=#{grzh}")
    List<Map> getList2(String map);
    /**
     * 根据个人账号更改 缴存基数 公司缴纳 个人缴纳
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set BASENUMMBER = #{BASENUMMBER},UNITMONPAYSUM = #{UBITNROP}*#{BASENUMMBER}/100,PERMONPAYSUM = #{INDINROP}*#{BASENUMMBER}/100" +
            " where GRZH = #{GRZH}")
    int update(Map map);
    /**
     * 根据个人账号更改公司和个人缴纳总金额
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set YDRAWAMT = (#{UBITNROP}*#{BASENUMMBER}/100+#{INDINROP}*#{BASENUMMBER}/100) where GRZH = #{GRZH}")
    int update1(Map map);
    /**
     * 根据公司ID更改应缴纳金额
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set UAOWEMONERY = (select sum(YDRAWAMT) from TB_PACCOUNTUTIL where unid= (select UNID from tb_paccountutil where GRZH=#{GRZH})) WHERE UNID=(select UNID from TB_UNIT where DWXZ=#{DWXZ})")
    int update2(Map map);
}
