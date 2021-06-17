package org.example.quangnh1.model.response;

public class ServiceResult {
    private Object object;
    private String status;
    private String message;
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAIL = "FAIL";

    public ServiceResult(Object object, String status, String message) {
        this.object = object;
        this.status = status;
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
