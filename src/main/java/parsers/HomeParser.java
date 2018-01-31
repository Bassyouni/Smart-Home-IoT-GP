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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Home getObject(String jsonText) {
        Gson gson = new Gson();
        Home home = gson.fromJson(jsonText, Home.class);
        return home;
    }
    
}
