/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.model;

import com.requestmanager.model.EnumStatus.Status;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pldh0199
 */
@XmlRootElement(name="request")
public class Request {

    @XmlAttribute
    private String requestName = "";

    @XmlElement(name = "request-description")
    private String requestDescription = "";

    @XmlAttribute
    @XmlID
    private String requestId;

    @XmlAttribute
    private String requestPublishedID;
    
    @XmlElement(name = "status")
    private Status status;

    @XmlElement(name = "request-reason-rejected")
    private String requestReasonRejected;

    @XmlElement(name = "version")
    private List<Version> versions;
    
    @XmlIDREF
    private Version version;

    public Version getVersion() {
        return version;
    }
    @XmlTransient
    public void setVersion(Version version) {
        this.version = version;
    }

    public List<Version> getVersions() {
        if (versions == null) {
            versions = new ArrayList<Version>();
        }
        return this.versions;
    }
    @XmlTransient
    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public String getRequestReasonRejected() {
        return requestReasonRejected;
    }
    @XmlTransient
    public void setRequestReasonRejected(String requestReasonRejected) {
        this.requestReasonRejected = requestReasonRejected;
    }

    public Status getStatus() {
        return status;
    }
    @XmlTransient
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRequestPublishedID() {
        return requestPublishedID;
    }
    @XmlTransient
    public void setRequestPublishedID(String requestPublishedID) {
        this.requestPublishedID = requestPublishedID;
    }

    public String getRequestId() {
        return requestId;
    }
    @XmlTransient
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }
    @XmlTransient
    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestName() {
        return requestName;
    }
    @XmlTransient
    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }
}
