/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import builders.DeviceParameterStringBuilder;
import builders.HomeParameterStringBuilder;
import java.util.HashMap;
import models.Device;

/**
 *
 * @author cdc
 */
public class HomeService extends ServiceSkeleton{
    private static final String BASE_URL = "http://197.52.108.54:4444/api/homes";
    
    private static HomeService _homeService;
    
    private HomeService(){}
    
    public static HomeService getInstance()
    {
        if(_homeService == null)
        {
            _homeService = new HomeService();
        }
        return _homeService;
    }
    
    public HashMap<String, Object> getAllHomesAttachedToUser(String targetUserId){
        final String PATH = BASE_URL + "/get/" + targetUserId;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 3;// as HomeList
        return fetchData(PATH, REQUEST_METHOD, PARSE_AS);
    }
    // fix issue
    public HashMap<String, Object> updateHome(String targetHomeId, String homeName, String homeAddress){
        final String PATH = BASE_URL + "/update/" + targetHomeId;
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 2;// as Home
        HashMap<String, String> requestParameters;
        //same result is needed but should be redon into function overload
        requestParameters =  HomeParameterStringBuilder.setupAddHomeRequestParameters(homeName, homeAddress);
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    // all the below are just declerations: create/modify the mapGenerator function
    public HashMap<String, Object> addDeviceToHome(String targetHomeId, Device device){
        final String PATH = BASE_URL + "/add-device/" + targetHomeId;
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 4;// as Device
        HashMap<String, String> requestParameters;
        //same result is needed but should be redon into function overload
        requestParameters =  DeviceParameterStringBuilder.buildParameterMap(device);
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> removeDevice(String targetHomeId, Device device){
        final String PATH = BASE_URL + "/remove-device/" + targetHomeId + "/" +  device.getId();
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 0;// status only
        HashMap<String, String> requestParameters;
        //same result is needed but should be redon into function overload
        requestParameters =  new HashMap<>();
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> UpdateDeviceInformation(String targetHomeId, Device device){
        final String PATH = BASE_URL + "/update-device/" + targetHomeId + "/" +  device.getId();
        final String REQUEST_METHOD = REQUEST_METHOD_POST;
        final int PARSE_AS = 0;// status only
        HashMap<String, String> requestParameters;
        requestParameters =  new HashMap<>();
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> addUserToHome(String targetHomeId, String targetUserId){
        final String PATH = BASE_URL + "/add-user/" + targetHomeId + "/" +  targetUserId;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 0;// status only
        HashMap<String, String> requestParameters = new HashMap<>();
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> addLogToDevice(String targetHomeId, String targetDeviceId, String command){
        final String PATH = BASE_URL + "/add-log-to-device/" + targetHomeId + "/" +  targetDeviceId + "/" + command;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 0;// status only
        HashMap<String, String> requestParameters = new HashMap<>();
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> getDeviceLogs(String targetHomeId, String targetDeviceId){
        final String PATH = BASE_URL + "/get-device-logs/" + targetHomeId + "/" + targetDeviceId;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 5;// as list of Logs -> this needs to be implemented
        HashMap<String, String> requestParameters;
        //same result is needed but should be redon into function overload
        requestParameters =  new HashMap<>();
        return fetchData(PATH, REQUEST_METHOD, requestParameters, PARSE_AS);
    }
    
    public HashMap<String, Object> getDevicesAttachedToHome(String targetHomeId){
        final String PATH = BASE_URL + "/get-devices/" + targetHomeId;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 6;// as list of devices -> implemented
        return fetchData(PATH, REQUEST_METHOD,  PARSE_AS);
    }
    
    
    public HashMap<String, Object> getUsersAttachedToHome(String targetHomeId){
        final String PATH = BASE_URL + "/get-users/" + targetHomeId;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 7;// as list of devices -> implemented
        return fetchData(PATH, REQUEST_METHOD,  PARSE_AS);
    }
    
    public HashMap<String, Object> addUserToHomeByEmail(String targetHomeId, String userEmail){
        final String PATH = BASE_URL + "/add-user-by-email/" + targetHomeId + "/" + userEmail;
        final String REQUEST_METHOD = REQUEST_METHOD_GET;
        final int PARSE_AS = 0;
        return fetchData(PATH, REQUEST_METHOD,  PARSE_AS);
    }
    
}
