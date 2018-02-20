/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import java.util.HashMap;
import models.User;

/**
 *
 * @author cdc
 */
public class UserParameterStringBuilder {
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String BIRTH_DATE = "birth_date";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_CONFIRMATION = "";// check with database creator
    
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
    
    public static  HashMap<String, String> setupUserUpdateRequestParameters(User user){
        // Create a map of paramters, keys must match the APIs documentation.
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(PASSWORD, user.getPassword());
        parameters.put(NAME, user.getName());
        parameters.put(BIRTH_DATE, user.getBirthDate());
        parameters.put(PASSWORD_CONFIRMATION, user.getPassword());
        return parameters;
    }
}
