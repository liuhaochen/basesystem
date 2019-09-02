package com.icbase.project.system.config.domain;

import com.icbase.framework.web.domain.BaseEntity;

/**
 * 参数配置表 sys_config
 * 
 * @author IC-Base
 */
public class Config extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    private Integer configId;
    /** 参数名称 */
    private String configName;
    /** 参数键名 */
    private String configKey;
    /** 参数键值 */
    private String configValue;
    /** 系统内置（Y是 N否） */
    private String configType;

    public Integer getConfigId()
    {
        return configId;
    }

    public void setConfigId(Integer configId)
    {
        this.configId = configId;
    }

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getConfigKey()
    {
        return configKey;
    }

    public void setConfigKey(String configKey)
    {
        this.configKey = configKey;
    }

    public String getConfigValue()
    {
        return configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }

    public String getConfigType()
    {
        return configType;
    }

    public void setConfigType(String configType)
    {
        this.configType = configType;
    }

}
