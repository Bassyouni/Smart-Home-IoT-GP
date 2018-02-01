/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import java.util.ArrayList;
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
        ArrayList<Log> logs = new ArrayList<>();
        return logs;
    }

    @Override
    public Object getObject(String jsonText) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
