package com.kaishengit.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/2.
 */
@Entity
@Table(name = "t_casefile")
public class CaseFile implements Serializable{

    private static final long serialVersionUID = -4473009664984234790L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    TODO 文件和病例多对一如何使用多对一  使用两个表维护关系？？
    @ManyToOne

    private Case aCase;
    private String name;
    private Long size;
    private String filename;
    private String contenttype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }
}
