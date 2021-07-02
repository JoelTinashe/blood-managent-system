package com.digitalkrapht.bloodbank.bloodbank.utils.format;


import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import com.digitalkrapht.bloodbank.bloodbank.utils.exception.BadRequestException;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;


import java.time.*;
import java.time.format.DateTimeParseException;

@Service

public class FormatUtility {


    public boolean isValidPhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse("+"+phoneNumber, "");
            System.out.println("Number is of region - "
                    + phoneUtil.getRegionCodeForNumber(numberProto));
            System.out
                    .println("Is the input number valid - "
                            + (phoneUtil.isValidNumber(numberProto) == true ? "Yes"
                            : "No"));
            if(phoneUtil.isValidNumber(numberProto)){
                return true;
            }else{
                return false;
            }


        } catch (NumberParseException e) {
            return false;
        }
    }

    public boolean isValidEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }
    public Boolean isValidDate(String rawDate) {


        try {
            LocalDate.parse(rawDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Boolean isValidTime(String rawTime) {


        try {
            LocalTime.parse(rawTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    public Boolean isValidDateTime(String dateTimeString){
        try {
            LocalDateTime date = LocalDateTime.parse(dateTimeString);
            return true;
        } catch (DateTimeParseException e) {
            // Throw invalid date message
            return false;
        }
    }

}
