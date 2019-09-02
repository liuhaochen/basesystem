package com.icbase.framework.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icbase.project.system.dict.domain.DictData;
import com.icbase.project.system.dict.service.IDictDataService;

/**
 * ICBase首创 html调用 thymeleaf 实现字典读取
 * 
 * @author IC-Base
 */
@Component
public class DictService
{
    @Autowired
    private IDictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据信息
     * 
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<DictData> selectDictData(String dictType)
    {
        return dictDataService.selectDictDataByType(dictType);
    }
}
