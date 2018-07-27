package com.johnny.atcrowdfunding.component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author johnny
 * @create 2018-07-21 下午1:57
 **/
public class AppPathListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        String path = servletContext.getContextPath();
        servletContext.setAttribute("APP_PATH", path);
        System.out.println(path);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}