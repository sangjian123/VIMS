
package com.vms.utils;

/**
 * Service层公用的Exception.
 */
public class ServiceException extends RuntimeException
{
    
    private static final long serialVersionUID = 1401593546385403720L;
    
    /**
     * 状态码
     */
    private int status;
    
    /**
     * 获取状态码
     * @return
     */
    public int getStatus()
    {
        return status;
    }
    
    /**
     * 构造方法
     */
    public ServiceException()
    {
        super();
    }
    
    /**
     * 构造方法
     * @param message 异常信息
     */
    public ServiceException(String message)
    {
        super(message);
    }
    
    /**
     * 构造方法
     * @param cause 异常
     */
    public ServiceException(Throwable cause)
    {
        super(cause);
    }
    
    /**
     * 构造方法
     * @param message 异常信息
     * @param status 异常状态码
     */
    public ServiceException(String message, int status)
    {
        super(message);
        this.status = status;
    }
    
    /**
     * 构造方法
     * @param message 异常信息
     * @param e 异常
     */
    public ServiceException(String message, Throwable e)
    {
        super(message, e);
    }
    
    /**
     * 构造方法
     * @param message 异常信息
     * @param e 异常
     * @param status 异常状态码
     */
    public ServiceException(String message, int status, Throwable e)
    {
        super(message, e);
        this.status = status;
    }
}
