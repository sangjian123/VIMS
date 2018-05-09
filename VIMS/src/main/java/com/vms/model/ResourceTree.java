package com.vms.model;

import java.io.Serializable;
import java.util.List;

public class ResourceTree implements Serializable, Comparable <ResourceTree>
{
    
    /**
     * 
     */
    private static final long serialVersionUID = 6521285820052562318L;
    
    private String id;
    
    private String name;
    
    private String url;
    
    private List <ResourceTree> list;
    
    /**
     * @return the id
     */
    public String getId ()
    {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId (String id)
    {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName (String name)
    {
        this.name = name;
    }
    
    /**
     * @return the list
     */
    public List <ResourceTree> getList ()
    {
        return list;
    }
    
    /**
     * @param list the list to set
     */
    public void setList (List <ResourceTree> list)
    {
        this.list = list;
    }
    
    public String getUrl ()
    {
        return url;
    }
    
    public void setUrl (String url)
    {
        this.url = url;
    }
    
    @Override
    public int compareTo (ResourceTree o)
    {
        if (this.id.compareTo (o.getId ()) > 0)
            return 1;
        else if (this.id.compareTo (o.getId ()) == 0)
            return 0;
        else
            return -1;
    }
    
}
