package com.digitalkrapht.bloodbank.bloodbank.security.response;

import lombok.Data;

import java.util.List;
@Data
public class PermissionResponse {
    private String name;
    private long id;
    private List<PrivilegeResponse> priviledges;
}
