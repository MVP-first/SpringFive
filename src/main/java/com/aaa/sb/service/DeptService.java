package com.aaa.sb.service;

import com.aaa.sb.entity.Dept;

import java.util.List;

/**
 * @className:DeptService
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-21 11:24
 */

public interface  DeptService {
    /**
     * 部门列表查询
     * @return
     */
    List<Dept> getList();

    /**
     * 部门添加
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 部门更新
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 部门删除
     * @param deptno
     * @return
     */
    int delDept(Integer deptno);

    /**
     * 根据id查询部门信息
     * @param deptno
     * @return
     */
    Dept getDeptById(Integer deptno);
}
