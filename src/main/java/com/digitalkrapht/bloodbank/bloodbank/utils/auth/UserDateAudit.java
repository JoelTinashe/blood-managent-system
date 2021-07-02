package com.digitalkrapht.bloodbank.bloodbank.utils.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy"},
        allowGetters = true
)
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

}
