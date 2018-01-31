/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smarthomesiot.desktop.ParameterStringBuilder;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Home;
import models.User;
import parsers.HomeParser;
import parsers.UserParser;

/**
 *
 * @author cdc
 */
public abstract class ServiceSkeleton {
    protected HttpURLConnection createConnection(String urlPath, String requestMethod){
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
    protected HashMap<String, Object> checkConnection(HttpURLConnection connection){
        
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
    protected HashMap<String, Object> constructFailureMap(int status){
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", "failure");
        responseMap.put("error", status + " - Couldn't establish connection");
        return responseMap;
    }
    protected void constructOutputStream(HttpURLConnection connection, HashMap<String, String> requestParameters){
        // Data is transfered in bytes, so Data OutputStream is used.
        String parameters;
        try {
            parameters = ParameterStringBuilder.getParamsString(requestParameters);
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
    protected String handelResponse(HttpURLConnection connection){
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
    protected HashMap<String, Object> parseResponseAsUser(String response, int parseAs){
        HashMap<String, Object> responseMap = new HashMap<>();
        JsonObject jsonResponse = new JsonParser().parse(response).getAsJsonObject();
        String responseStatus = jsonResponse.get("status").getAsString();
        responseMap.put("status", responseStatus);
        if(responseStatus.equals("failure"))
        {
            responseMap.put("error", jsonResponse.get("error").getAsString());
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
        return responseMap;
    }
    protected HashMap<String, Object> fetchData(String path, String requestMethod, HashMap<String, String> requestParameters, int parseAs){
        HashMap<String, Object> responseMap;
        HttpURLConnection connection = createConnection(path, requestMethod);
        responseMap = checkConnection(connection);//checking if the connection is established: null if valid else a map;
        if( responseMap != null)
            return responseMap;
        constructOutputStream(connection, requestParameters);
        String response = handelResponse(connection);
        responseMap = parseResponseAsUser(response, parseAs);
        return responseMap;
    }
}
