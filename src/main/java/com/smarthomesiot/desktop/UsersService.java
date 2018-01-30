package com.smarthomesiot.desktop;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsersService 
{
    private static String _baseUrl;
    private static UsersService _usersService;

    private UsersService() 
    {
  
    }
    
    
    public static UsersService getInstance()
    {
        if(_usersService == null)
        {
            _baseUrl = "http://localhost:8000/api/users";
            _usersService = new UsersService();
        }
        return _usersService;
    }
    
    public HashMap<String, Object> login(String email, String password)
    {
        
        HashMap<String, Object> responseMap = new HashMap<>();
        try 
        {
            // Create a URL of the web service.
            URL url = new URL(this._baseUrl + "/auth/login");
            // Open a Connection to the server
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set Request Method 
            connection.setRequestMethod("POST");
            // Create a map of paramters, keys must match the APIs documentation.
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("email", email);
            parameters.put("password", password);
            // Tell the connection that it will be sending data.
            connection.setDoOutput(true);
            // Data is transfered in bytes, so Data OutputStream is used.
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();
            
            int status = connection.getResponseCode();
            
            if(status != HttpURLConnection.HTTP_OK)
            {
                responseMap.put("status", "failure");
                responseMap.put("error", status + " - Couldn't establish connection");
                return responseMap;
            }
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();
            
            JsonObject jsonResponse = new JsonParser().parse(response.toString()).getAsJsonObject();
            
            String responseStatus = jsonResponse.get("status").getAsString();
            responseMap.put("status", responseStatus);
            if(responseStatus.equals("failure"))
            {
                responseMap.put("error", jsonResponse.get("error").getAsString());
            }
            else
            {
                UserParser userParser = UserParser.getInstance();
                User user = userParser.getObject(jsonResponse.get("response").getAsJsonObject().toString());
                responseMap.put("response", user);
            }
        } 
        catch (MalformedURLException ex) 
        {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(UsersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseMap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
} //CLASS END 
