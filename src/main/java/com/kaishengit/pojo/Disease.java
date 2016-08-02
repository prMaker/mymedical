package com.kaishengit.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/31.
 */
@Entity
@Table(name = "t_disease")
public class Disease implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diseasename;
    @ManyToOne
    @JoinColumn(name = "departmentid")
    private Department department;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiseasename() {
        return diseasename;
    }

    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + id +
                ", diseasename='" + diseasename + '\'' +
                ", department=" + department +
                '}';
    }
}
