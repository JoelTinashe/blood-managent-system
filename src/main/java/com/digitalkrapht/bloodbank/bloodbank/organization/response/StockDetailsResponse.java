package com.digitalkrapht.bloodbank.bloodbank.organization.response;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.StockAdjustmentType;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserBackOfficeAgentResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserDonorResponse;
import lombok.Data;
@Data
public class StockDetailsResponse {

    private long id;
    private String quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType;
    private  String stockAdjustmentNotes;
    private BloodGroupResponse bloodGroup;
    private UserBackOfficeAgentResponse backOfficeAgent;
    private UserDonorResponse userDonor;

}
