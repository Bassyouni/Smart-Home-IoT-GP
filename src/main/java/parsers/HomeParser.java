/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.google.gson.Gson;
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
        
        ArrayList<Home> homes = new ArrayList<>();
        homes = gson.fromJson(jsonText, homes.getClass());
        return homes;
    }

    @Override
    public Home getObject(String jsonText) {
        Home home = gson.fromJson(jsonText, Home.class);
        return home;
    }
    
}
