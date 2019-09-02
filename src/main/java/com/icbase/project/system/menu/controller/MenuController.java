package com.icbase.project.system.menu.controller;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icbase.framework.aspectj.lang.annotation.Log;
import com.icbase.framework.web.controller.BaseController;
import com.icbase.framework.web.domain.Message;
import com.icbase.project.system.menu.domain.Menu;
import com.icbase.project.system.menu.service.IMenuService;
import com.icbase.project.system.role.domain.Role;

/**
 * 菜单信息
 * 
 * @author IC-Base
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController
{

    private String prefix = "system/menu";

    @Autowired
    private IMenuService menuService;

    @RequiresPermissions("system:menu:view")
    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    @ResponseBody
    public List<Menu> list()
    {
        List<Menu> menuList = menuService.selectMenuAll();
        return menuList;
    }

    /**
     * 删除菜单
     */
    @Log(title = "系统管理", action = "菜单管理-删除菜单")
    @RequiresPermissions("system:menu:remove")
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    public Message remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.selectCountMenuByParentId(menuId) > 0)
        {
            return Message.error(1, "存在子菜单,不允许删除");
        }
        if (menuService.selectCountRoleMenuByMenuId(menuId) > 0)
        {
            return Message.error(1, "菜单已分配,不允许删除");
        }
        if (menuService.deleteMenuById(menuId) > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 修改菜单
     */
    @Log(title = "系统管理", action = "菜单管理-修改菜单")
    @RequiresPermissions("system:menu:edit")
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, Model model)
    {
        Menu menu = menuService.selectMenuById(menuId);
        model.addAttribute("menu", menu);
        return prefix + "/edit";
    }

    /**
     * 新增
     */
    @Log(title = "系统管理", action = "菜单管理-新增菜单")
    @RequiresPermissions("system:menu:add")
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, Model model)
    {
        Menu menu = null;
        if (0L != parentId)
        {
            menu = menuService.selectMenuById(parentId);
        }
        else
        {
            menu = new Menu();
            menu.setMenuId(0L);
            menu.setMenuName("主目录");
        }
        model.addAttribute("menu", menu);
        return prefix + "/add";
    }

    /**
     * 保存菜单
     */
    @Log(title = "系统管理", action = "菜单管理-保存菜单")
    @RequiresPermissions("system:menu:save")
    @PostMapping("/save")
    @ResponseBody
    public Message save(Menu menu)
    {
        if (menuService.saveMenu(menu) > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon()
    {
        return prefix + "/icon";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(Menu menu)
    {
        String uniqueFlag = "0";
        if (menu != null)
        {
            uniqueFlag = menuService.checkMenuNameUnique(menu);
        }
        return uniqueFlag;
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String, Object>> roleMenuTreeData(Role role)
    {
        List<Map<String, Object>> tree = menuService.roleMenuTreeData(role);
        return tree;
    }
    
    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData(Role role)
    {
        List<Map<String, Object>> tree = menuService.menuTreeData();
        return tree;
    }
    
    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long  menuId, Model model)
    {
        model.addAttribute("treeName", menuService.selectMenuById(menuId).getMenuName());
        return prefix + "/tree";
    }
}