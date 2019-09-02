package com.icbase.project.system.dict.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icbase.framework.aspectj.lang.annotation.Log;
import com.icbase.framework.web.controller.BaseController;
import com.icbase.framework.web.domain.Message;
import com.icbase.framework.web.page.TableDataInfo;
import com.icbase.project.system.dict.domain.DictData;
import com.icbase.project.system.dict.service.IDictDataService;

/**
 * 数据字典信息
 * 
 * @author IC-Base
 */
@Controller
@RequestMapping("/system/dict/data")
public class DictDataController extends BaseController
{
    private String prefix = "system/dict/data";

    @Autowired
    private IDictDataService dictDataService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData()
    {
        return prefix + "/data";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(DictData dictData)
    {
        startPage();
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 修改字典类型
     */
    @Log(title = "系统管理", action = "字典管理-修改字典数据")
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, Model model)
    {
        DictData dict = dictDataService.selectDictDataById(dictCode);
        model.addAttribute("dict", dict);
        return prefix + "/edit";
    }

    /**
     * 新增字典类型
     */
    @Log(title = "系统管理", action = "字典管理-新增字典数据")
    @RequiresPermissions("system:dict:add")
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, Model model)
    {
        model.addAttribute("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 保存字典类型
     */
    @Log(title = "系统管理", action = "字典管理-保存字典数据")
    @RequiresPermissions("system:dict:save")
    @PostMapping("/save")
    @ResponseBody
    public Message save(DictData dict)
    {
        if (dictDataService.saveDictData(dict) > 0)
        {
            return Message.success();
        }
        return Message.error();
    }
    
    /**
     * 删除
     */
    @Log(title = "系统管理", action = "字典管理-删除字典数据")
    @RequiresPermissions("system:dict:remove")
    @RequestMapping("/remove/{dictCode}")
    @ResponseBody
    public Message remove(@PathVariable("dictCode") Long dictCode)
    {
        DictData dictData = dictDataService.selectDictDataById(dictCode);
        if (dictData == null)
        {
            return Message.error("字典数据不存在");
        }
        if (dictDataService.deleteDictDataById(dictCode) > 0)
        {
            return Message.success();
        }
        return Message.error();
    }

    @Log(title = "系统管理", action = "字典类型-批量删除")
    @RequiresPermissions("system:dict:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public Message batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = dictDataService.batchDeleteDictData(ids);
        if (rows > 0)
        {
            return Message.success();
        }
        return Message.error();
    }
}
