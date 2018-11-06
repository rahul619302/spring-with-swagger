package com.cg.SpringWithSwagger.response;

import com.cg.SpringWithSwagger.util.Constants;

import java.util.Map;

public class Response {

    private Constants status;
    private String statusDiscription;
    private Map<String, Object> map;

    public Response() {

    }

    public Response(Constants status, String statusDiscription) {
        this.status = status;
        this.statusDiscription = statusDiscription;
    }

    public Constants getStatus() {
        return status;
    }

    public void setStatus(Constants status) {
        this.status = status;
    }

    public String getStatusDiscription() {
        return statusDiscription;
    }

    public void setStatusDiscription(String statusDiscription) {
        this.statusDiscription = statusDiscription;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", statusDiscription='" + statusDiscription + '\'' +
                ", map=" + map +
                '}';
    }
}
