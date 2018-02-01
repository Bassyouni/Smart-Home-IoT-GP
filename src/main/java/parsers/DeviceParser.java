/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import java.util.ArrayList;
import models.Device;

/**
 *
 * @author cdc
 */
public class DeviceParser implements JSONParser{
    private static DeviceParser deviceParser;
    
    public static DeviceParser getInstance()
    {
        if( deviceParser == null)
        {
            deviceParser = new DeviceParser();
        }
        return deviceParser;
    }

    @Override
    public ArrayList getArrayOfObjects(String jsonText) {
        ArrayList<Device> devices = new ArrayList<>();
        devices = gson.fromJson(jsonText, devices.getClass());
        return devices;
    }

    @Override
    public Device getObject(String jsonText) {
        Device device;
        device = gson.fromJson(jsonText, Device.class);
        return device;
    }
    
}
