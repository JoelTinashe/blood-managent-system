package com.digitalkrapht.bloodbank.bloodbank.organization.response;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserBackOfficeAgentResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserDonorResponse;
import lombok.Data;

@Data
public class StockDetailsLogResponse {

    private int id;
    private String quantity;
    private String unit;
    private String stockAdjustmentType;
    private  String stockAdjustmentNotes;
    private BloodGroupResponse bloodGroup;
    private UserBackOfficeAgentResponse backOfficeAgent;
    private UserDonorResponse userDonor;

}
