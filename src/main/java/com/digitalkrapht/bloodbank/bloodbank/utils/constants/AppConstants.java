package com.digitalkrapht.bloodbank.bloodbank.utils.constants;

import org.springframework.stereotype.Repository;

@Repository
public interface AppConstants {
    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "50";

    int MAX_PAGE_SIZE = 1000;
}
