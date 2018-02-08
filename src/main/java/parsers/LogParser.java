/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import models.Device;
import models.Log;

/**
 *
 * @author cdc
 */
public class LogParser implements JSONParser{
    private static LogParser logParser;
    
    public static LogParser getInstance() {
        if( logParser == null)
        {
            logParser = new LogParser();
        }
        return logParser;
    }

    @Override
    public ArrayList<Log> getArrayOfObjects(String jsonText) {
        Type listType = new TypeToken<ArrayList<Log>>(){}.getType();
        ArrayList<Log> logs;
        logs = gson.fromJson(jsonText, listType);
        return logs;
    }

    @Override
    public Object getObject(String jsonText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
