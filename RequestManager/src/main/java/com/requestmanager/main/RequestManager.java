/*
 * To change this license header, choose License Headers in Project Utilities.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.main;

import com.requestmanager.controller.RequestHandler;
import com.requestmanager.model.EnumStatus;
import com.requestmanager.utils.Utilities;

/**
 *
 * @author pldh0199W
 */
public class RequestManager {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Utilities util = new Utilities();
        util.log();
        Utilities.logger.info("Application initialized");
        RequestHandler rh = new RequestHandler(util);
        scenerio1(rh);
        //scenerio2(rh);
        //scenerio3(rh);
        //scenerio4(rh);
        //scenerio5(rh);
        Utilities.logger.info("Application finished a job");
    }
     private static void scenerio1(RequestHandler rh){
         rh.createNewRequest("Reauest 1", "Test 1");
         rh.createNewRequest("Reauest 2", "Test 2");
         rh.createNewRequest("Reauest 3", "Test 3");
         rh.createNewRequest("Reauest 4", "Test 4");
     }

    private static void scenerio2(RequestHandler rh) {
        rh.deleteRequest("99", "Test deleted", EnumStatus.Status.DELETED);
    }

    private static void scenerio3(RequestHandler rh) {
        rh.verifyRequest("5", "Test verified 1", EnumStatus.Status.VERIFIED);
        rh.verifyRequest("71", "Test verified 2", EnumStatus.Status.VERIFIED);
        rh.verifyRequest("6", "Test verified 3", EnumStatus.Status.VERIFIED);
    }

    private static void scenerio4(RequestHandler rh) {
        rh.rejectRequest("5", "Test rejected 1", EnumStatus.Status.REJECTED);
        rh.acceptRequest("71", EnumStatus.Status.ACCEPTED);
        rh.acceptRequest("6", EnumStatus.Status.ACCEPTED);
    }

    private static void scenerio5(RequestHandler rh) {
        rh.rejectRequest("71","Test rejected 2", EnumStatus.Status.REJECTED);
        rh.publishRequest("6", EnumStatus.Status.PUBLISHED);
    }
}
