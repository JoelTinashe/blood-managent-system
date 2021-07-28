package com.digitalkrapht.bloodbank.bloodbank.organization.response;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.StockAdjustmentType;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserBackOfficeAgentResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserDonorResponse;
import lombok.Data;
@Data
public class StockDetailsResponse {

    private long id;
    private int quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType;
    private  String stockAdjustmentNotes;
    private UserBackOfficeAgentResponse backOfficeAgent;
    private String userDonorId;

}
