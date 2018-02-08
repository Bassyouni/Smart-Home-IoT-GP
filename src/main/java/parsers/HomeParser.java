/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import models.Home;

/**
 *
 * @author cdc
 */
public class HomeParser implements JSONParser<Home>{
    private static HomeParser _homeParser;
    
    public static HomeParser getInstance()
    {
        if(_homeParser == null)
        {
            _homeParser = new HomeParser();
        }
        return _homeParser;
    }
    
    
    @Override
    public ArrayList<Home> getArrayOfObjects(String jsonText) {
        
        Type listType = new TypeToken<ArrayList<Home>>(){}.getType();
        ArrayList<Home> homes;
        homes = gson.fromJson(jsonText, listType);
        return homes;
    }

    @Override
    public Home getObject(String jsonText) {
        Home home = gson.fromJson(jsonText, Home.class);
        return home;
    }
    
}
