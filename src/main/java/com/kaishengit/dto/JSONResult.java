package com.kaishengit.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 * 为JSON通用文件
 * 含4个构造方法 无参，有参 为一状态，错误（error，msg），正确（object），正确（List<Object>）
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JSONResult{

    public static final String DTO_STATE_SUCCESS = "success";
    public static final String DTO_STATE_ERROR = "error";

    private String state;
    private Object data;
    private List<Object> dataList;
    private String msg;

    public JSONResult() {
    }

    public JSONResult(String state) {
        this.state = state;
    }

    public JSONResult(Object data) {
        this.state = DTO_STATE_SUCCESS;
        this.data = data;
    }

    public JSONResult(List<Object> dataList) {
        this.state = DTO_STATE_SUCCESS;
        this.dataList = dataList;
    }

    public JSONResult(String state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
