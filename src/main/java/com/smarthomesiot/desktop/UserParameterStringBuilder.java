/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthomesiot.desktop;

import java.util.HashMap;

/**
 *
 * @author cdc
 */
public class UserParameterStringBuilder extends ParameterStringBuilder{
    private static final String NAME = "";
    private static final String EMAIL = "";
    private static final String BIRTH_DATE = "";
    private static final String PASSWORD = "";
    private static final String PASSWORD_CONFIRMATION = "";
    
    public static  HashMap<String, String> setupUserLoginRequestParameters(String email, String password){
        // Create a map of paramters, keys must match the APIs documentation.
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(EMAIL, email);
        parameters.put(PASSWORD, password);
        return parameters;
    }
    
    public static  HashMap<String, String> setupUserSignUpRequestParameters(String name, String email, String birthDate, String password, String passwordConfirmation){
        // Create a map of paramters, keys must match the APIs documentation.
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(EMAIL, email);
        parameters.put(PASSWORD, password);
        parameters.put(NAME, name);
        parameters.put(BIRTH_DATE, birthDate);
        parameters.put(PASSWORD_CONFIRMATION, passwordConfirmation);
        return parameters;
    }
    
    public static  HashMap<String, String> setupUserUpdateRequestParameters(String name, String birthDate, String password, String passwordConfirmation){
        // Create a map of paramters, keys must match the APIs documentation.
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(PASSWORD, password);
        parameters.put(NAME, name);
        parameters.put(BIRTH_DATE, birthDate);
        parameters.put(PASSWORD_CONFIRMATION, passwordConfirmation);
        return parameters;
    }
}
