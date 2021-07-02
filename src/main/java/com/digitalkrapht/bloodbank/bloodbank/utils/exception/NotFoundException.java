
package com.digitalkrapht.bloodbank.bloodbank.utils.exception;

public class NotFoundException extends RuntimeException {

    private String resourceId;

    public NotFoundException(String resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }

    public NotFoundException(Integer id, String member_not_found) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
