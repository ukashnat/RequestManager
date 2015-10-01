/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pldh0199
 */
@XmlRootElement
public class Requests {

    
    private List<Request> requests = null;

    @XmlElement(name="request")
    public List<Request> getRequests() {
        if (requests == null) {
            requests = new ArrayList<Request>();
        }
        return this.requests;
    }
}
