package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:SealDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 18:49
 */

public interface SealDao {
    /**
     * 封存 启封 销户 分页
     * @param map
     * @return
     */
    @Select("<script>select a.grzh,a.ubitnrop,a.indinrop,a.pid,a.basenummber,a.dalance,to_char(a.lastnaydate,'yyyy-MM-dd') as LASTNAYDATE,a.peraccstate,b.pname from tb_paccountutil a,tb_person_info b where a.pid=b.pid\n" +
            "<where><if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></where></script>")
    List<Map> SealedPage(Map map);
    /**
     * 判断唯一性校验  封存 启封 销户 校验   不能重复操作
     * @param map
     * @return
     */
    @Select("select * from tb_unseal_audit where audit_name = '0' and unseal_account = #{grzh}")
    List<Map> verification(Map map);

    /**
     * 校验贷款的人 不能销户 和 封存
     * @param map
     * @return
     */
    @Select(" select * from tb_repay where  GRZH = #{grzh}")
    List<Map> loansVerification(Map map);

    /**
     * 封存 qi销户   操作弹出层查询信息
     * @param map
     * @return
     */
    @Select("select a.GRZH,a.peraccstate,b.pname,c.uname,\n" +
            " d.DWZH,d.unid from tb_paccountutil a,tb_person_info b，tb_unit c,tb_unitaccount d\n" +
            "where a.pid=b.pid and a.unid=c.unid and c.unid=d.unid and a.GRZH = #{grzh}")
    Map operationQuery(Map map);
    /**
     * 获取审核信息   放入审核表中
     * @param map
     * @return
     * element
     */
    @Select(" select a.GRZH,a.peraccstate,b.psex,b.idnumber,b.pdate,b.phone,b.profession,b.pname,c.uname,d.DWZH from tb_paccountutil a,tb_person_info b,tb_unit c,tb_unitaccount d where a.pid=b.pid and a.unid=c.unid and c.unid=d.unid and a.GRZH = #{grzh}")
    List<Map> unsealAudit(Map map);

    /**
     * 获取审核信息  添加到审核表中
     * @param map
     * @return
     * element
     */
    @Insert("insert into tb_unseal_audit(unseal_id,unseal_name,unseal_unit,unseal_sex,unit_post,unseal_phone,unseal_number,unseal_account,reason,operator,create_time,state,AUDIT_NAME) values(seq_unseal_audit.nextval,#{UNSEAL_NAME},#{UNSEAL_UNIT},#{UNSEAL_SEX},#{UNIT_POST},#{UNSEAL_PHONE},#{UNSEAL_NUMBER},#{UNSEAL_ACCOUNT},#{REASON},#{OPERATOR},sysdate,#{STATE},1)")
    int unsealAuditAdd(Map map);

    /**
     * 启封 封存审核表分页查询
     * @param map
     * @return
     */
    @Select("<script>select unseal_id,unseal_name,unseal_unit,unseal_sex,unit_post,unseal_phone," +
            "unseal_number,unseal_account,reason,operator,state,audit_state,audit_name from tb_unseal_audit " +
            "<where><if test=\"GRZH!=null and GRZH!=''\"> and GRZH like '%'||#{GRZH}||'%'</if></where></script>")
    List<Map>  sealAudit(Map map);
    /**
     * 明细列表分页
     */
    @Select("<script>select a.GRZH,a.unitmonpaysum, a.ydrawamt,a.permonpaysum, a.paop,c.uname,to_char(a.opendate,'yyyy-MM-dd') as OPENDATE,b.pname from tb_paccountutil a, tb_person_info b, tb_unit c " +
            "where a.pid = b.pid and a.unid = c.unid " +
            "<where><if test=\"pname!=null and pname!=''\"> and pname like concat('%',#{pname},'%')</if></where></script>")
    List<Map> getPage(Map map);
}