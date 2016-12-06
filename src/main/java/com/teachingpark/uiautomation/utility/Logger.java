package com.teachingpark.uiautomation.utility;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.xml.DOMConfigurator;

import com.teachingpark.uiautomation.constant.AppConstant;

import java.util.Hashtable;

/**
 * This is a helper class to provide log related reusable functions
 * @author bpant12
 *
 */
public final class Logger {
    private static Hashtable<String, org.apache.log4j.Logger> objHashtable = new Hashtable<String, org.apache.log4j.Logger>();
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("AUTOMATION");
    private String DefaultLog4JConfiguration = AppConstant.LOG_CONFIGURATION;

    public static final synchronized void loadConfiguration(String File) {
        try {
            DOMConfigurator.configure(File);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
 
    public final void enterMethod() {
        this.logger.info("ENTER --> (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ")");
    }

    public final void enterMethod(String Message) {
        this.logger.info("ENTER --> (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") --> " +
                Message);
    }

    public final void error(Exception e) {
        error("Exception Occurred: (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " +
                e.getMessage());
        for (Object msg : e.getStackTrace())
            error(msg.toString());
    }

    public final void error(Object Message) {
        this.logger.error("(" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " + Message);
    }

    public final void exitMethod() {
        this.logger.info("EXIT --> (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ")");
    }

    public final void exitMethod(String Message) {
        this.logger.info("EXIT --> (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") --> " +
                Message);
    }

    public final void fatal(Exception e) {
        fatal("Exception Occured: (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " +
                e.getMessage());
        for (Object msg : e.getStackTrace())
            fatal(msg.toString());
    }

    public final void fatal(Object Message) {
        this.logger.fatal("(" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " + Message);
    }


    public final String getDefaultLog4JConfiguration() {
        return DefaultLog4JConfiguration;
    }

    public void setDefaultLog4JConfiguration(String defaultLog4JConfiguration) {
        DefaultLog4JConfiguration = defaultLog4JConfiguration;
    }

    public final Level getLevel() {
        return this.logger.getLevel();
    }

    public final void setLevel(Level objLevel) {
        this.logger.setLevel(objLevel);
    }

    public Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName().toString());
    }

    public Logger getLogger(String strName) {
        if (objHashtable.containsKey(strName)) {
            this.logger = objHashtable.get(strName);
        } else {
            loadConfiguration(DefaultLog4JConfiguration);
            this.logger = org.apache.log4j.Logger.getLogger(strName);
            objHashtable.put(
                    strName,
                    this.logger);
        }
        return this;
    }

    public final String getName() {
        return this.logger.getName();
    }

    public final Category getParent() {
        return this.logger.getParent();
    }

    public final void info(Object Message) {
        this.logger.info("(" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " + Message);
    }

    public final void debug(Object Message) {
        this.logger.debug("(" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " + Message);
    }

    public final boolean isAttached(Appender objAppender) {
        return this.logger.isAttached(objAppender);
    }

    public final boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public final void removeAllAppenders() {
        this.logger.removeAllAppenders();
    }

    public final void removeAppender(Appender objAppender) {
        this.logger.removeAppender(objAppender);
    }

    public final void removeAppender(String Name) {
        this.logger.removeAppender(Name);
    }

    public final void warn(Exception e) {
        warn("Exception Occured: (" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " +
                e.getMessage());
        for (Object msg : e.getStackTrace())
            warn(msg.toString());
    }

    public final void warn(Object Message) {
        this.logger.warn("(" + java.lang.Thread.currentThread().getStackTrace()[2].getMethodName().trim() + ") -> " + Message);
    }
}