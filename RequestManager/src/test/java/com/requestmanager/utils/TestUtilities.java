/*
 * To change this license header, choose License Headers in Project Properties.
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
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author PLDH0199
 */
public class TestUtilities {

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

    @Test
    public void TestLog() {
        try {
            Properties logProperties = new Properties();
            logProperties.load(new FileInputStream(getLogPath()));
            Assert.assertEquals("are equal", "log4j.xml", getLogPath());
            PropertyConfigurator.configure(logProperties);
            Assert.assertNotNull(logProperties);
            ConsoleAppender appender = new ConsoleAppender(new SimpleLayout());
            Assert.assertNotNull(logger);
            logger.addAppender(appender);
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }
    }
}
