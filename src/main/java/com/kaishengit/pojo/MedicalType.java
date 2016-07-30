package com.kaishengit.pojo;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/7/29.
 */
@Entity
@Table(name = "t_medicaltype")
public class MedicalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String medicalensuretype;

    public MedicalType(String medicalensuretype) {
        this.medicalensuretype = medicalensuretype;
    }

    public MedicalType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicalensuretype() {
        return medicalensuretype;
    }

    public void setMedicalensuretype(String medicalensuretype) {
        this.medicalensuretype = medicalensuretype;
    }
}
