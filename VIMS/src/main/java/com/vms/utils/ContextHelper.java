package com.vms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 *
 *  Created by jihonghai on 2016/11/17.
 */
@Component
public class ContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    private ContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
    	if(context != null){
    		setContext(context);
    	}
    }

    public static ApplicationContext getContext() {

        return context;
    }
    
    
    private synchronized static void setContext(ApplicationContext ctx) {
    	context = ctx;
    }
}
