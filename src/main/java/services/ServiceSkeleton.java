/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Device;
import models.Home;
import models.Log;
import models.User;
import parsers.DeviceParser;
import parsers.HomeParser;
import parsers.LogParser;
import parsers.UserParser;

/**
 *
 * @author cdc
 */
public abstract class ServiceSkeleton {
    
    protected static final String REQUEST_METHOD_GET = "GET";
    protected static final String REQUEST_METHOD_POST = "POST";
    
    protected static String getParamsString(Map<String, String> params) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
 
        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }
 
        String resultString = result.toString();
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
    }
    
    protected static HttpURLConnection createConnection(String urlPath, String requestMethod){
        URL url;
        HttpURLConnection connection;
        
        try {
            // Create a URL of the web service.
            url = new URL(urlPath);
            // Open a Connection to the server
            connection = (HttpURLConnection) url.openConnection();
            // Set Request Method 
            connection.setRequestMethod(requestMethod);
            // Tell the connection that it will be sending data.
            connection.setDoOutput(true);
            return connection;
        } catch (MalformedURLException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    protected static void constructOutputStream(HttpURLConnection connection, HashMap<String, String> requestParameters){
        // Data is transfered in bytes, so Data OutputStream is used.
        String parameters;
        try {
            parameters = getParamsString(requestParameters);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(parameters);
            out.flush();
            out.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected static HashMap<String, Object> checkConnection(HttpURLConnection connection){
        
        int status;
        try {
            status = connection.getResponseCode();
            if( status != HttpURLConnection.HTTP_OK )
            {
                return constructFailureMap(status);
            }
            else
                return null;
        } catch (IOException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    protected static HashMap<String, Object> constructFailureMap(int status){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", "failure");
        responseMap.put("error", status + " - Couldn't establish connection");
        return responseMap;
    }
    protected static String handleResponse(HttpURLConnection connection){
        String inputLine;
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream()) );
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return response.toString();
        } catch (IOException ex) {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection.disconnect();
        return null;
        
    }
    protected static HashMap<String, Object> parseResponse(String response, int parseAs){
        HashMap<String, Object> responseMap = new HashMap<>();
        JsonObject jsonResponse = new JsonParser().parse(response).getAsJsonObject();
        String responseStatus = jsonResponse.get("status").getAsString();
        responseMap.put("status", responseStatus);
        if(responseStatus.equals("failure"))
        {
            responseMap.put("error", jsonResponse.get("error").getAsString());
        }
        else if(parseAs == 0)// status only
        {
            return responseMap;
        }
        else if(parseAs == 1)// as User
        {
           UserParser userParser = UserParser.getInstance();
           User user = userParser.getObject(jsonResponse.get("response").getAsJsonObject().toString());
           responseMap.put("response", user);
        }
        else if(parseAs == 2)// as Home
        {
            HomeParser homeParser = HomeParser.getInstance();
            Home home = homeParser.getObject( jsonResponse.get("response").getAsJsonObject().toString() );
            responseMap.put("response", home);
        }
        else if(parseAs == 3)// as ArrayList<Home>
        {
            ArrayList<Home> homes = HomeParser.getInstance().getArrayOfObjects( jsonResponse.get("response").getAsJsonArray().toString() );
            responseMap.put("response", homes);
        }
        else if(parseAs == 4)// as device
        {
            Device device;
            device = DeviceParser.getInstance().getObject( jsonResponse.get("response").getAsJsonObject().toString() );
            responseMap.put("response", device);
        }
        else if(parseAs == 5)// as ArrayList<Log>
        {
            ArrayList<Log> logs = LogParser.getInstance().getArrayOfObjects( jsonResponse.get("response").getAsJsonObject().toString() );
            responseMap.put("response", logs);
        }
        else if(parseAs == 6)// as ArrayList<Log>
        {
            ArrayList<Device> devices = DeviceParser.getInstance().getArrayOfObjects( jsonResponse.get("response").getAsJsonArray().toString() );
            responseMap.put("response", devices);
        }
        else if(parseAs == 7)// as ArrayList<User>
        {
            ArrayList<User> users = UserParser.getInstance().getArrayOfObjects( jsonResponse.get("response").getAsJsonArray().toString() );
            responseMap.put("response", users);
        }
        return responseMap;
    }
    protected static HashMap<String, Object> fetchData(String path, String requestMethod, HashMap<String, String> requestParameters, int parseAs){
        HashMap<String, Object> responseMap;
        HttpURLConnection connection = createConnection(path, requestMethod);
        constructOutputStream(connection, requestParameters);//  actually sending the request
        responseMap = checkConnection(connection);//checking if the connection is established: null if valid else a map;
        if( responseMap != null)
            return responseMap;
        String response = handleResponse(connection);
        responseMap = parseResponse(response, parseAs);
        return responseMap;
    }
    
    protected static HashMap<String, Object> fetchData(String path, String requestMethod, int parseAs)
    {
        HashMap<String, Object> responseMap;
        HttpURLConnection connection = createConnection(path, requestMethod);
        responseMap = checkConnection(connection);//checking if the connection is established: null if valid else a map;
        if( responseMap != null)
            return responseMap;
        String response = handleResponse(connection);
        responseMap = parseResponse(response, parseAs);
        return responseMap;
    }
}
