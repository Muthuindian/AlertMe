package com.tech42labs.alertme.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mari on 3/17/17.
 */

public class Reports extends RealmObject {

    @PrimaryKey
    private String empId;

    private String companyId;

    private String AlertTypeId;

    private Date AlertRecieveDate;

    private Date AlertRecieveTime;

    private Date AlertReplyDate;

    private Date AlertReplyTime;

    private double latitude;

    private double longitude;


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAlertTypeId() {
        return AlertTypeId;
    }

    public void setAlertTypeId(String alertTypeId) {
        AlertTypeId = alertTypeId;
    }

    public Date getAlertRecieveDate() {
        return AlertRecieveDate;
    }

    public void setAlertRecieveDate(Date alertRecieveDate) {
        AlertRecieveDate = alertRecieveDate;
    }

    public Date getAlertRecieveTime() {
        return AlertRecieveTime;
    }

    public void setAlertRecieveTime(Date alertRecieveTime) {
        AlertRecieveTime = alertRecieveTime;
    }

    public Date getAlertReplyDate() {
        return AlertReplyDate;
    }

    public void setAlertReplyDate(Date alertReplyDate) {
        AlertReplyDate = alertReplyDate;
    }

    public Date getAlertReplyTime() {
        return AlertReplyTime;
    }

    public void setAlertReplyTime(Date alertReplyTime) {
        AlertReplyTime = alertReplyTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
