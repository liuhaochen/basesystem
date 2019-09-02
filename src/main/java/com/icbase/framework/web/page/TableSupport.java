package com.icbase.framework.web.page;

import com.icbase.common.constant.Constants;
import com.icbase.common.utils.ServletUtils;

/**
 * 表格数据处理
 * 
 * @author IC-Base
 */
public class TableSupport
{
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getIntParameter(Constants.PAGENUM));
        pageDomain.setPageSize(ServletUtils.getIntParameter(Constants.PAGESIZE));
        pageDomain.setOrderByColumn(ServletUtils.getStrParameter(Constants.ORDERBYCOLUMN));
        pageDomain.setIsAsc(ServletUtils.getStrParameter(Constants.ISASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }

}
