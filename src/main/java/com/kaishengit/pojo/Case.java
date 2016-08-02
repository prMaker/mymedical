package com.kaishengit.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2016/7/31.
 */
@Entity
@Table(name = "t_case")
public class Case implements Serializable{
    private static final long serialVersionUID = -644255334604156279L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patientid")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "diseaseid")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "departmentid")
    private Department department;

    private String easydiagonose;
    private String mainsymptom;
    private String relatedhistory;
    private String positivesign;
    private String checkresult;
    private String treatmentplan;
    private String beddoctor;
    private String nextdiagnosetime;
    private Timestamp createtime;

    @OneToMany
    private List<CaseFile> caseFileList;

    public List<CaseFile> getCaseFileList() {
        return caseFileList;
    }

    public void setCaseFileList(List<CaseFile> caseFileList) {
        this.caseFileList = caseFileList;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEasydiagonose() {
        return easydiagonose;
    }

    public void setEasydiagonose(String easydiagonose) {
        this.easydiagonose = easydiagonose;
    }

    public String getMainsymptom() {
        return mainsymptom;
    }

    public void setMainsymptom(String mainsymptom) {
        this.mainsymptom = mainsymptom;
    }

    public String getRelatedhistory() {
        return relatedhistory;
    }

    public void setRelatedhistory(String relatedhistory) {
        this.relatedhistory = relatedhistory;
    }

    public String getPositivesign() {
        return positivesign;
    }

    public void setPositivesign(String positivesign) {
        this.positivesign = positivesign;
    }

    public String getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    public String getTreatmentplan() {
        return treatmentplan;
    }

    public void setTreatmentplan(String treatmentplan) {
        this.treatmentplan = treatmentplan;
    }

    public String getBeddoctor() {
        return beddoctor;
    }

    public void setBeddoctor(String beddoctor) {
        this.beddoctor = beddoctor;
    }

    public String getNextdiagnosetime() {
        return nextdiagnosetime;
    }

    public void setNextdiagnosetime(String nextdiagnosetime) {
        this.nextdiagnosetime = nextdiagnosetime;
    }
}
