/*
 * To change this license header, choose License Headers in Project Utilities.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.requestmanager.controller;

import com.requestmanager.model.Requests;
import com.requestmanager.model.EnumStatus;
import com.requestmanager.model.Request;
import com.requestmanager.model.Version;
import com.requestmanager.utils.Utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author pldh0199
 */
public class RequestHandler {
    private Utilities util=null;

    public RequestHandler(Utilities util) {
        this.util=util;
    }

    public void showRequests(int itemsPerPage) {
        for (List<Request> requests : getPages(JAXBUnMarschaller().getRequests(), itemsPerPage)) {
            for (Request r : requests) {
                Utilities.logger.info(r.getRequestName());
            }
        }
        //JAXBMarschaller(JAXBUnMarschaller());
        Utilities.logger.info("All requests have been displayed");
    }

    public void showFilteredRequests(String requestName, EnumStatus.Status status, int itemsPerPage) {
        for (List<Request> requests : getPages(filterByNameAndStatus(requestName, status), itemsPerPage)) {
            for (Request r : requests) {
                Utilities.logger.info(r.getRequestName() + " " + r.getStatus());
            }
        }
        //JAXBMarschaller(JAXBUnMarschaller());
        Utilities.logger.info("Showed filtered requests");
    }

    public void createNewRequest(String requestName, String requestDescription) {
        Requests requests = null;
        if (JAXBUnMarschaller() == null) {
            requests = new Requests();
        } else {
            requests = JAXBUnMarschaller();
        }
        if (requestName != null && requestDescription != null) {
            requests.getRequests().add(createRequest(requestName, requestDescription));
        }

        JAXBMarschaller(requests);
    }

    private Request createRequest(String requestName, String requestDescription) {
        Request r = new Request();
        r.setRequestId(generateUniqueId(100));
        r.setRequestName(requestName);
        r.setRequestDescription(requestDescription);
        r.setStatus(EnumStatus.Status.CREATED);
        r.getVersions().add(createNewVersion(r, EnumStatus.Status.CREATED));

        Utilities.logger.info("New request has been created with Id " + r.getRequestId());

        return r;
    }

    private Version createNewVersion(Request r, EnumStatus.Status status) {
        Version v = new Version();
        v.setVersionId(generateUniqueId(100));
        v.setRequest(r);
        if (status.equals(EnumStatus.Status.CREATED)) {
            v.setVersionCreatedDate(Utilities.getSDateFormat().format(new Date()));
        } else {
            v.setVersionCreatedDate(getCreatedDateFromVersion(r));
        }
        v.setVersionModifiedDate(Utilities.getSDateFormat().format(new Date()));
        return v;
    }
    
    private String  getCreatedDateFromVersion(Request r){
        return r.getVersions().get(r.getVersions().size() - 1).getVersionCreatedDate();
    }

    public void deleteRequest(String toBeDeleted, String requestReasonRejected, EnumStatus.Status s) {
        if (toBeDeleted != null && requestReasonRejected != null) {
            Requests requests = JAXBUnMarschaller();
            for (Request r : requests.getRequests()) {
                if (r.getRequestId().equals(toBeDeleted)
                        && (r.getStatus().equals(EnumStatus.Status.CREATED))) {
                    r.setStatus(s);
                    r.setRequestReasonRejected(requestReasonRejected);
                    r.getVersions().add(createNewVersion(r, s));
                    Utilities.logger.info("Request with Id " + r.getRequestId() + " has been deleted");
                }
            }
            JAXBMarschaller(requests);
        }
    }

    public void verifyRequest(String toBeVerified, String requestDescription, EnumStatus.Status s) {
        if (toBeVerified != null) {
            Requests requests = JAXBUnMarschaller();
            for (Request r : requests.getRequests()) {
                if (r.getRequestId().equals(toBeVerified)
                        && r.getStatus().equals(EnumStatus.Status.CREATED)) {
                    r.setStatus(s);
                    r.setRequestDescription(requestDescription);
                    r.getVersions().add(createNewVersion(r, s));

                    Utilities.logger.info("Request with Id " + r.getRequestId() + " has been verified");
                }
            }
            JAXBMarschaller(requests);
        }
    }

    public void rejectRequest(String toBeRejected, String requestReasonRejected, EnumStatus.Status s) {
        if (toBeRejected != null && requestReasonRejected != null) {
            Requests requests = JAXBUnMarschaller();
            for (Request r : requests.getRequests()) {
                if (r.getRequestId().equals(toBeRejected) 
                        && (r.getStatus().equals(EnumStatus.Status.ACCEPTED) || r.getStatus().equals(EnumStatus.Status.VERIFIED))) {
                    r.setStatus(s);
                    r.setRequestReasonRejected(requestReasonRejected);
                    r.getVersions().add(createNewVersion(r, s));

                    Utilities.logger.info("Request with Id " + r.getRequestId() + " has been rejected");
                }
            }
            JAXBMarschaller(requests);
        }
    }

    public void acceptRequest(String toBeAccepted, EnumStatus.Status s) {
        if (toBeAccepted != null) {
            Requests requests = JAXBUnMarschaller();
            for (Request r : requests.getRequests()) {
                if (r.getRequestId().equals(toBeAccepted)
                        && r.getStatus().equals(EnumStatus.Status.VERIFIED)) {
                    r.setStatus(s);
                    r.getVersions().add(createNewVersion(r, s));

                    Utilities.logger.info("Request with Id " + r.getRequestId() + " has been accepted");
                }
            }
            JAXBMarschaller(requests);
        }
    }

    public void publishRequest(String toBePublished, EnumStatus.Status s) {
        if (toBePublished != null) {
            Requests requests = JAXBUnMarschaller();
            for (Request r : requests.getRequests()) {
                if (r.getRequestId().equals(toBePublished)
                        && r.getStatus().equals(EnumStatus.Status.ACCEPTED)) {
                    r.setStatus(s);
                    r.setRequestPublishedID(generateUniqueId(1000));
                    r.getVersions().add(createNewVersion(r, s));

                    Utilities.logger.info("Request with Id " + r.getRequestId()
                            + " has been published with PublishedId "
                            + r.getRequestPublishedID());
                }
            }
            JAXBMarschaller(requests);
        }
    }

    private String generateUniqueId(int range) {
        ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
        for (int i = 1; i < range; i++) {
            listOfIntegers.add(i);
        }
        Collections.shuffle(listOfIntegers);

        return String.valueOf(listOfIntegers.get(0));
    }

    private void JAXBMarschaller(Requests requests) {
        try {
            JAXBContext jc = JAXBContext.newInstance(new Class[]{Requests.class, Request.class, Version.class, EnumStatus.class});
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //print(marshaller, requests);

            writeToFile(marshaller, requests);

        } catch (JAXBException ex) {
            Utilities.logger.error(ex);
        }
    }

    private String print(Marshaller marshaller, Requests requests) throws JAXBException {
        StringWriter debugInfo = new StringWriter();
        marshaller.marshal(requests, System.out);
        return debugInfo.toString();
    }

    private void writeToFile(Marshaller marshaller, Requests requests) throws JAXBException {
        try {
            try (OutputStream os = new FileOutputStream(util.getOutputFileName())) {
                marshaller.marshal(requests, os);
            }
        } catch (FileNotFoundException ex) {
            Utilities.logger.error(ex);
        } catch (IOException ex) {
            Utilities.logger.error(ex);
        }
    }

    private Requests JAXBUnMarschaller() {
        try {
            JAXBContext jc = JAXBContext.newInstance(new Class[]{Requests.class, Request.class, Version.class, EnumStatus.class});
            Unmarshaller u = jc.createUnmarshaller();

            return (Requests) u.unmarshal(new File(util.getOutputFileName()));
        } catch (JAXBException ex) {
            Utilities.logger.error(ex);
        }
        return null;
    }

    //It's taken from http://stackoverflow.com
    public static <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (c == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<T>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size()) {
            pageSize = list.size();
        }
        int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
        List<List<T>> pages = new ArrayList<List<T>>(numPages);
        for (int pageNum = 0; pageNum < numPages;) {
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        }

        return pages;
    }

    private List<Request> filterByNameAndStatus(String name, EnumStatus.Status status) {
        List<Request> filtered = new ArrayList<Request>();
        Requests requests = JAXBUnMarschaller();
        for (Request r : requests.getRequests()) {
            if ((name != null ? r.getRequestName().equals(name) : true)
                    && (status != null ? r.getStatus().equals(status) : true)) {
                filtered.add(r);
            }
        }
        return filtered;
    }
}
