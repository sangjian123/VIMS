package com.vms.model;

import java.util.Map;

public abstract class GroupBase
{
    public Map<String, Object> fieldMethodMap;
    
    public abstract Object getGroupKey(String field);

    public Map<String, Object> getFieldMethodMap()
    {
        return fieldMethodMap;
    }

    public void setFieldMethodMap(Map<String, Object> fieldMethodMap)
    {
        this.fieldMethodMap = fieldMethodMap;
    }
}
