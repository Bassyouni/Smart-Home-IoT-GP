/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import java.util.HashMap;

/**
 *
 * @author cdc
 */
public class HomeParameterStringBuilder {
    private static final String HOME_NAME = "name";
    private static final String HOME_ADDRESS = "address";
    private static final String TOPIC = "topic";
    
    public static HashMap<String, String> setupAddHomeRequestParameters(String homeName, String homeAddress){
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(HOME_NAME, homeName);
        parameters.put(HOME_ADDRESS, homeAddress);
        return parameters;
    }
}
