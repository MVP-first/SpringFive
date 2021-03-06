package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;
import java.util.List;
public interface RemitDao {

    @Select("<script> select t2.UATEBALANCE,t2.DWZH,t1.UNAME,t2.UDEPOSITRATIO,t2.UPERSONRATIO,t2.UDEPOSITEDPNUM,t2.UAREMAIN,t2.UAOWEMONERY,t2.UASTATE,t2.unid,to_char(t2.uapayenddate,'yyyy-MM-dd') as uapayenddate "+
            " from tb_unit t1 left join tb_unitaccount t2 on t1.unid=t2.unid \n"+
            " <where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);
    @Select("<script>select GRZH,PNAME,BASENUMMBER,UBITNROP,INDINROP,UNITMONPAYSUM,PERMONPAYSUM from tb_paccountutil a left join tb_person_info b on a.pid = b.pid \n"+
            "<where> UNID=(select UNID from tb_unitaccount where DWZH=#{DWZH}) <if test=\"PNAME!=null and PNAME!=''\">  and PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList1(Map map);
    @Select("select a.UNID,UNAME,DWZH,GRZH,PNAME,BASENUMMBER,PERACCSTATE,UBITNROP,INDINROP,UNITMONPAYSUM,PERMONPAYSUM FROM tb_paccountutil a left join tb_unit b on a.UNID=b.UNID \n"+
            "left join tb_person_info c on a.pid = c.pid  left join tb_unitaccount d on d.unid=b.unid where GRZH=#{grzh}")
    List<Map> getList2(String map);
    /**
     * 添加
     * @param map
     * @return
     */
    @Insert("insert into TB_UNITBIZDETAIL (UBID,DWZH,UBDPOPULATION,UBDDETAILTYPE,UBDPAYWAY,ZCK,UBDSETTLESTATES,UBDCREATER,HJYS,UBDACCRUAL) values(SEQ_TB_UNITBIZDETAIL.nextval,#{DWZH},#{UDEPOSITEDPNUM},'汇缴','均缴',#{UATEBALANCE},'成功',#{YWBLR},#{UAOWEMONTHS},#{UAREMAIN})")
//    int add(Map map);
//    @Insert("insert into tb_unitaccount(UAPAYENDDATE)values(sysdate) where DWZH=#{DWZH}")
    int add1(Map map);
    @Update("update tb_unitaccount set UAREMAIN=UAREMAIN-#{UAOWEMONERY},UAPAYENDDATE=sysdate where DWZH=#{DWZH}")
    int update(Map map);
    @Update("update tb_paccountutil set DALANCE=(select DALANCE from tb_paccountutil where GRZH=#{GRZH})+(select YDRAWAMT from tb_paccountutil where GRZH=#{GRZH}), YINTERESTBAL=1 where GRZH=#{GRZH}")
    int update1(Map map);

}
