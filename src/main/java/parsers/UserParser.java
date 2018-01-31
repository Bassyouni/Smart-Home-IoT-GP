/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import models.User;
import com.google.gson.Gson;
import java.util.ArrayList;

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
        Gson gson = new Gson();
        return null;
        
    }

    @Override
    public User getObject(String jsonText) 
    {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonText, User.class);
        return user;
    }
    
    
}
