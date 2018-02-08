/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builders;

import java.util.HashMap;
import models.Device;

/**
 *
 * @author cdc
 */
public class DeviceParameterStringBuilder {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "description";
    private static final String PIN_NUMBER = "pin_number";
    
    public static HashMap<String, String> buildParameterMap(Device device){
        HashMap<String, String> parameterMap = new HashMap<>();
        
        if(device.getId() != null)
            parameterMap.put(ID, device.getId());
        else if(device.getName() != null)
            parameterMap.put(NAME, device.getName());
        else if(device.getType() != null)
            parameterMap.put(TYPE, device.getType());
        else if(device.getDescription() != null)
            parameterMap.put(DESCRIPTION, device.getDescription());
        else if(device.getPinNumber() != -1)
            parameterMap.put(PIN_NUMBER, Integer.toString(device.getPinNumber()));
        
        return parameterMap;
    }
}
