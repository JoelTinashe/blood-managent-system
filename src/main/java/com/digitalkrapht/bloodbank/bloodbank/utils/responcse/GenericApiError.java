package com.digitalkrapht.bloodbank.bloodbank.utils.responcse;

import lombok.Data;

@Data
public class GenericApiError {
    private String status;
    private String error;
    private int code;

    public GenericApiError(String error,int code) {
        super();
        this.status = "failed";
        this.error = error;
        this.code = code;
    }
}
