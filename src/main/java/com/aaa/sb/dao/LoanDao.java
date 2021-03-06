package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:LoanDao
 * discription:
 * author:MVP
 * createTime:2018-12-10 10:43
 */
public interface LoanDao {
    /**
     * 根据账号获取贷款申请客户列表
     * @param
     * @return
     */
    @Select(value ="select a.GRZH,a.pid,a.unid,p.unit_id,x.DWZH,p.PNAME,p.PSEX,p.PHONE,p.IDCARD,p.IDNUMBER,p.PMARRIAGE,p.PROFESSION,p.EDUCATION,p.PEMAIL,p.PADDRESS,\n" +
            "                                       p.UNIT_ID,to_char(LASTNAYDATE,'yyyy-MM-dd') as LASTNAYDATE, a.PERACCSTATE,a.DALANCE,u.UNAME,u.UOPIPHONE ,p.PID from\n" +
            "                                   TB_PERSON_INFO p left join TB_PACCOUNTUTIL a on a.pid=p.pid left \n" +
            "                                      join tb_unitaccount x on x.UNID=a.unid  left join TB_UNIT u on  x.UNID=U.UNID  where a.grzh=#{GRZH}")
    Map getList(String GRZH);

    /**
     *-
     * 添加贷款信息
     *
     * @return
     */

    @Insert("insert into tb_loanappval(loan_id,pid,DWZH,grzh,togetherzh,SALARY,bank_money,loan_money,loan_periods,loan_rate,loan_bank,loan_repay,"+
            "loan_repayer,loan_repaydate,REPAY_BANK,REPAY_ACCOUNTNAME,REPAY_ACCOUNT,status,house_type,house_location," +
            "house_area,buy_id,buy_name,bank_account,house_total,house_firstpay," +
            "house_price,pawn_type,pawn_name,pawn_idnumber,pawn_address,pawn_status,pawn_money,DAIKAN) " +
            "values(SEQ_LOANAPPVAL1.nextval,#{PID},#{DWZH},#{GRZH},#{GRZH1},#{ SALARY},#{BANK_MONEY},#{loanAmount}," +
            "#{loanPeriods},#{loanRate},#{trustBank},#{paymentMethod},#{payee},#{paymentDay},#{openBank},#{paymentName},#{paymentAccount},1," +
            "#{houseType},#{houseAddress},#{houseArea},#{HIdNum},#{HName},#{HBank},#{houseTotal},#{houseFirst}," +
            "#{housePrice},#{DYType},#{DYName},#{DYIdNum},#{DYAddress},#{DYStatus},#{DYMoney},'个人贷款')")

    int addLoan(Map map);

    /**
     * 将贷款信息添加至还款表中
     * @param map
     * @return
     */

    @Insert("insert into tb_repay (REPAY_ID,GRZH,PNAME,LOAN_MONEY,LOAN_PERIODS,LOAN_RATE,REPAYED_DATE," +
            "REPAY_BANK,REPAY_ACCOUNT,REPAYED_PERIODS,REPAYED_ALL_MONEY,RESIDUE_MONEY,REPAY_STATUS，REPAYED_MONEY,STATE,PID) values(SEQ_REPAY_ID.nextval,#{GRZH},#{PNAME},#{loanAmount},#{loanPeriods},#{loanRate}," +
            "add_months(trunc(sysdate),1)," +
            "#{openBank},#{paymentAccount},0,0,#{loanAmount},'正常',0,0,#{PID})")

    int addRepay(Map map);

    /**
     * 对贷款人是否之前贷过款进行验证
     * @param
     * @return
     */
    @Select("select count(*) from TB_LOANAPPVAL l left join TB_PACCOUNTUTIL p on " +
            "p.PID=l.pid where l.grzh=#{value} and PERACCSTATE='正常'")
       int unique(String value);



}

