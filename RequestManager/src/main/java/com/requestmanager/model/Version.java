/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.model;

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
@XmlRootElement(name = "version")
public class Version {

    @XmlAttribute
    @XmlID
    private String versionId;
    @XmlElement(name="request")
    @XmlIDREF
    private Request version;

    @XmlElement(name = "version-modified-date")
    private String versionModifiedDate;

    @XmlElement(name = "version-created-date")
    private String versionCreatedDate;

    public String getVersionCreatedDate() {
        return versionCreatedDate;
    }

    @XmlTransient
    public void setVersionCreatedDate(String versionCreatedDate) {
        this.versionCreatedDate = versionCreatedDate;
    }

    public String getVersionModifiedDate() {
        return versionModifiedDate;
    }

    @XmlTransient
    public void setVersionModifiedDate(String versionModifiedDate) {
        this.versionModifiedDate = versionModifiedDate;
    }

    public Request getRequest() {
        return version;
    }

    @XmlTransient
    public void setRequest(Request version) {
        this.version = version;
    }

    public String getVersionId() {
        return versionId;
    }

    @XmlTransient
    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
