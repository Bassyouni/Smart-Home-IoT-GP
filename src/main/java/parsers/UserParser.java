/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import models.Log;
import static parsers.JSONParser.gson;

/**
 *
 * @author Mahmoud Mokhtar
 */
public class UserParser implements JSONParser<User>
{
    private static UserParser _userParser;
    private UserParser(){}
    
    public static UserParser getInstance()
    {
        if(_userParser == null)
        {
            _userParser = new UserParser();
        }
        return _userParser;
    }
    
    @Override
    public ArrayList<User> getArrayOfObjects(String jsonText) 
    {
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users;
        users = gson.fromJson(jsonText, listType);
        return users;
        
    }

    @Override
    public User getObject(String jsonText) 
    {
        User user = gson.fromJson(jsonText, User.class);
        return user;
    }
    
    
}
