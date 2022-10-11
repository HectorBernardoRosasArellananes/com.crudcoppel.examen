package com.crudcoppel.com.crudcoppel.examen.models;


public class ResponseResult  {
    private StatusResponse Meta;
    private Object Data;
    
    public void setMeta(StatusResponse meta) {
        Meta= meta;
    }
    public void setData(Object data) {
        Data = data;
    }
    public StatusResponse getMeta() {
        return Meta;
    }
    public Object getData() {
        return Data;
    }
}

