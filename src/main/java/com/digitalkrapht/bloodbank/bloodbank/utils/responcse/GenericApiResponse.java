package com.digitalkrapht.bloodbank.bloodbank.utils.responcse;

import lombok.Data;

@Data
public class GenericApiResponse {
    public GenericApiResponse(String massage) {
        this.status = "success";
        this.massage = massage;
    }

    private String status;
    private String massage;

}
