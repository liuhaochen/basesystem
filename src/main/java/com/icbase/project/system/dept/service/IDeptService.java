package com.icbase.project.system.dept.service;

import java.util.List;
import java.util.Map;

import com.icbase.project.system.dept.domain.Dept;

/**
 * 部门管理 服务层
 * 
 * @author IC-Base
 */
public interface IDeptService
{
    /**
     * 查询部门管理集合
     * 
     * @return 所有部门信息
     */
    public List<Dept> selectDeptAll();
    
    /**
     * 查询部门管理树
     * 
     * @return 所有部门信息
     */
    public List<Map<String, Object>> selectDeptTree();

    /**
     * 查询部门人数
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int saveDept(Dept dept);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    public Dept selectDeptById(Long deptId);
    
    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public String checkDeptNameUnique(Dept dept);
}
