package com.example.TACS2021UTN.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    @Value("${data.pageable.maxSize}")
    private Integer maxPageSize;

    protected String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

    //Provisorio, no encontre una mejor manera de manejarlo global que funcionara
    protected Integer getPageSize(Integer querySize){
        return Math.min(maxPageSize, querySize);
    }
}
