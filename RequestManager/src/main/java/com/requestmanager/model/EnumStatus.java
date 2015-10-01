/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author pldh0199
 */
@XmlRootElement(name="status")
public class EnumStatus {

    @XmlType(name = "status")
    @XmlEnum
    public enum Status {

        CREATED,
        DELETED,
        VERIFIED,
        REJECTED,
        ACCEPTED,
        PUBLISHED;
    }
}
