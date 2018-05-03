package com.vms.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Page <T> implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    // 页码，默认是第一页
    private int pageNo = 1;
    
    // 每页显示的记录数，默认是10
    private int pageSize = 10;
    
    // 总记录数
    private int totalRecord;
    
    // 总页数
    private int totalPage;
    
    // 对应的当前页记录
    private List <T> results; //NoSonar
    
    // 封装参数
    private T params; //Nosonar
    
    // 开始位置是否需要计算, 默认true:计算
    private boolean calcIndex = true;
    
    //pageSzie不缓存
    private boolean cachePage = false;
    
    private String sortable;
    
    private Map <String, Object> conditions; //nosonar
    
    public Page ()
    {
    }
    
    public Page (T params, int pageNo, int pageSize, int totalRecord, int totalPage, List <T> results)
    {
        this.params = params;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.results = results;
    }
    
    public Page (int pageNo, int pageSize, int totalRecord, int totalPage, List <T> results, T params,
        Map <String, Object> conditions)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.results = results;
        this.params = params;
        this.conditions = conditions;
    }
    
    public Page (T params)
    {
        this.params = params;
    }
    
    public Page (Map <String, Object> conditions)
    {
        this.conditions = conditions;
    }
    
    public Map <String, Object> getConditions ()
    {
        return conditions;
    }
    
    public void setConditions (Map <String, Object> conditions)
    {
        this.conditions = conditions;
    }
    
    public int getPageNo ()
    {
        return pageNo;
    }
    
    public void setPageNo (int pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public int getPageSize ()
    {
        return pageSize;
    }
    
    public void setPageSize (int pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public int getTotalRecord ()
    {
        return totalRecord;
    }
    
    public void setTotalRecord (int totalRecord)
    {
        
        this.totalRecord = totalRecord;
        
        // 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        this.setTotalPage (totalPage);
    }
    
    public int getTotalPage ()
    {
        return totalPage;
    }
    
    public void setTotalPage (int totalPage)
    {
        this.totalPage = totalPage;
    }
    
    public List <T> getResults ()
    {
        return results;
    }
    
    public void setResults (List <T> results)
    {
        this.results = results;
    }
    
    public T getParams ()
    {
        return params;
    }
    
    public void setParams (T params)
    {
        this.params = params;
    }
    
    public boolean isCalcIndex ()
    {
        return calcIndex;
    }
    
    public void setCalcIndex (boolean calcIndex)
    {
        this.calcIndex = calcIndex;
    }
    
    public boolean isCachePage ()
    {
        return cachePage;
    }
    
    public void setCachePage (boolean cachePage)
    {
        this.cachePage = cachePage;
    }
    
    public String getSortable ()
    {
        return sortable;
    }
    
    public void setSortable (String sortable)
    {
        this.sortable = sortable;
    }
    
    @Override
    public String toString ()
    {
        StringBuilder builder = new StringBuilder ();
        builder.append ("Page [pageNo=").append (pageNo).append (", pageSize=").append (pageSize).append (", results=")
            .append (results).append (", totalPage=").append (totalPage).append (", totalRecord=").append (totalRecord)
            .append (", calcIndex=").append (calcIndex).append ("]");
        return builder.toString ();
    }
    
}