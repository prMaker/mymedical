package com.kaishengit.pojo;

import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/31.
 */
@Entity
@Table(name = "t_department")
public class Department implements Serializable {
    private static final long serialVersionUID = -9073317885667666290L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String departmentname;
    private String principal;

    public Department() {
    }

    public Department(String departmentname, String principal) {
        this.departmentname = departmentname;
        this.principal = principal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentname='" + departmentname + '\'' +
                ", principal='" + principal + '\'' +
                '}';
    }
}
