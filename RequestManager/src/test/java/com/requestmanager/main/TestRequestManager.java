/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.main;

import com.requestmanager.controller.RequestHandler;
import com.requestmanager.utils.Utilities;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author pldh0199
 */
public class TestRequestManager {

    @Test
    public void TestRequestManager() {
        Utilities util = new Utilities();
        util.log();
        Assert.assertNotNull(util);
        RequestHandler rh = new RequestHandler(util);
        scenerio1(rh);
    }
    
    private void scenerio1(RequestHandler rh) {
        rh.showRequests(10);
    }
}
