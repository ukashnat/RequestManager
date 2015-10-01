/*
 * To change this license header, choose License Headers in Project Utilities.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

/**
 *
 * @author PLDH0199
 */
public class Utilities {

    public static Logger logger = Logger.getLogger("Logging");

    public static SimpleDateFormat getSDateFormat() {
        return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    }

    public String getLogPath() {
        return "log4j.xml";
    }

    public String getOutputFileName() {
        return "requests.xml";
    }

    public void log() {
        try {
            Properties logProperties = new Properties();
            logProperties.load(new FileInputStream(getLogPath()));
            PropertyConfigurator.configure(logProperties);
            ConsoleAppender appender = new ConsoleAppender(new SimpleLayout());
            logger.addAppender(appender);
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}
