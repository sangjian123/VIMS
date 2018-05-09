/**
 * 类名:DateEditor.java
 * 描述: [描述]
 * @author:Administrator
 * 创建日期:2016年11月8日
 * 
 *
*/

package com.vms.model;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author   Administrator
 * @version  [版本号]
 * @since    [产品/模块版本]
 * @see 	 [相关类/方法]
 */
public class DateEditor extends PropertyEditorSupport
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger (DateEditor.class);
    
    public void setAsText (String text)
    {
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = format.parse (text);
        }
        catch (Exception e)
        {
            format = new SimpleDateFormat ("yyyy-MM-dd");
            try
            {
                date = format.parse (text);
            }
            catch (Exception e1)
            {
                LOGGER.error ("date parse error.", e1);
            }
        }
        setValue (date);
    }
}
