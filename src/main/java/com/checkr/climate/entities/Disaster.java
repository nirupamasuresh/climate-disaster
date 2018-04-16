package com.checkr.climate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Entity for each disaster summary
 */
@Entity
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int disasterNumber;
    private int ihProgramDeclared;
    private int iaProgramDeclared;
    private int paProgramDeclared;
    private int hmProgramDeclared;
    private String state;
    private Date declarationDate;
    private String fyDeclared;
    private String disasterType;
    private String incidentType;
    private String title;
    private Date incidentBeginDate;
    private Date incidentEndDate;
    private Date disasterCloseOutDate;
    private String declaredCountyArea;
    private String placeCode;
    private String hash;
    private Date lastRefresh;

    protected Disaster() {
    }

    public Disaster(int disasterNumber, int ihProgramDeclared, int iaProgramDeclared, int paProgramDeclared, int hmProgramDeclared, String state, Date declarationDate, String fyDeclared, String disasterType, String incidentType, String title, Date incidentBeginDate, Date incidentEndDate, Date disasterCloseOutDate, String declaredCountyArea, String placeCode, String hash, Date lastRefresh) {
        this.disasterNumber = disasterNumber;
        this.ihProgramDeclared = ihProgramDeclared;
        this.iaProgramDeclared = iaProgramDeclared;
        this.paProgramDeclared = paProgramDeclared;
        this.hmProgramDeclared = hmProgramDeclared;
        this.state = state;
        this.declarationDate = declarationDate;
        this.fyDeclared = fyDeclared;
        this.disasterType = disasterType;
        this.incidentType = incidentType;
        this.title = title;
        this.incidentBeginDate = incidentBeginDate;
        this.incidentEndDate = incidentEndDate;
        this.disasterCloseOutDate = disasterCloseOutDate;
        this.declaredCountyArea = declaredCountyArea;
        this.placeCode = placeCode;
        this.hash = hash;
        this.lastRefresh = lastRefresh;
    }

    public int getDisasterNumber() {
        return disasterNumber;
    }

    public void setDisasterNumber(int disasterNumber) {
        this.disasterNumber = disasterNumber;
    }

    public int getIhProgramDeclared() {
        return ihProgramDeclared;
    }

    public void setIhProgramDeclared(int ihProgramDeclared) {
        this.ihProgramDeclared = ihProgramDeclared;
    }

    public int getIaProgramDeclared() {
        return iaProgramDeclared;
    }

    public void setIaProgramDeclared(int iaProgramDeclared) {
        this.iaProgramDeclared = iaProgramDeclared;
    }

    public int getPaProgramDeclared() {
        return paProgramDeclared;
    }

    public void setPaProgramDeclared(int paProgramDeclared) {
        this.paProgramDeclared = paProgramDeclared;
    }

    public int getHmProgramDeclared() {
        return hmProgramDeclared;
    }

    public void setHmProgramDeclared(int hmProgramDeclared) {
        this.hmProgramDeclared = hmProgramDeclared;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public String getFyDeclared() {
        return fyDeclared;
    }

    public void setFyDeclared(String fyDeclared) {
        this.fyDeclared = fyDeclared;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getIncidentBeginDate() {
        return incidentBeginDate;
    }

    public void setIncidentBeginDate(Date incidentBeginDate) {
        this.incidentBeginDate = incidentBeginDate;
    }

    public Date getIncidentEndDate() {
        return incidentEndDate;
    }

    public void setIncidentEndDate(Date incidentEndDate) {
        this.incidentEndDate = incidentEndDate;
    }

    public Date getDisasterCloseOutDate() {
        return disasterCloseOutDate;
    }

    public void setDisasterCloseOutDate(Date disasterCloseOutDate) {
        this.disasterCloseOutDate = disasterCloseOutDate;
    }

    public String getDeclaredCountyArea() {
        return declaredCountyArea;
    }

    public void setDeclaredCountyArea(String declaredCountyArea) {
        this.declaredCountyArea = declaredCountyArea;
    }

    public String getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(String placeCode) {
        this.placeCode = placeCode;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }

    @Override
    public String toString() {
        return "Disaster{" +
                "id=" + id +
                ", disasterNumber=" + disasterNumber +
                ", ihProgramDeclared=" + ihProgramDeclared +
                ", iaProgramDeclared=" + iaProgramDeclared +
                ", paProgramDeclared=" + paProgramDeclared +
                ", hmProgramDeclared=" + hmProgramDeclared +
                ", state='" + state + '\'' +
                ", declarationDate=" + declarationDate +
                ", fyDeclared='" + fyDeclared + '\'' +
                ", disasterType='" + disasterType + '\'' +
                ", incidentType='" + incidentType + '\'' +
                ", title='" + title + '\'' +
                ", incidentBeginDate=" + incidentBeginDate +
                ", incidentEndDate=" + incidentEndDate +
                ", disasterCloseOutDate=" + disasterCloseOutDate +
                ", declaredCountyArea='" + declaredCountyArea + '\'' +
                ", placeCode='" + placeCode + '\'' +
                ", hash='" + hash + '\'' +
                ", lastRefresh=" + lastRefresh +
                '}';
    }
}
