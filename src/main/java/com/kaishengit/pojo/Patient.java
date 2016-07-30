package com.kaishengit.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/7/29.
 */
@Entity
@Table(name = "t_patient")
public class Patient implements Serializable {
    private static final long serialVersionUID = 3598363486821220510L;

    public static final String PATIENT_STATE_NEW = "新建";
    public static final String PATIENT_STATE_INCLINIC = "在诊";
    public static final String PATIENT_STATE_LEAVE = "出院";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String patientname;
    private String sex;
    private String tel;
    private String address;
    private String pinyin;
    private String state;
    private Timestamp createtime;
    private String idcard;
    private Integer age;
    private String allergichistory;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "medicaltypeid")
    private MedicalType medicalType;

    public Patient(String address, String tel, String patientname) {
        this.address = address;
        this.tel = tel;
        this.patientname = patientname;
    }

    public Patient() {
    }

    public String getAllergichistory() {
        return allergichistory;
    }

    public void setAllergichistory(String allergichistory) {
        this.allergichistory = allergichistory;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public MedicalType getMedicalType() {
        return medicalType;
    }

    public void setMedicalType(MedicalType medicalType) {
        this.medicalType = medicalType;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patientname='" + patientname + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", state='" + state + '\'' +
                ", createtime=" + createtime +
                ", idcard='" + idcard + '\'' +
                ", age=" + age +
                ", medicalType=" + medicalType +
                '}';
    }
}
