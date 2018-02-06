/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import java.util.ArrayList;
import models.Message;

/**
 *
 * @author Mahmoud Mokhtar
 */
public class MessageParser implements JSONParser<Message>
{
    private static MessageParser messageParser;
    
    public static MessageParser getInstance() {
        if( messageParser == null)
        {
            messageParser = new MessageParser();
        }
        return messageParser;
    }

    @Override
    public ArrayList<Message> getArrayOfObjects(String jsonText) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message getObject(String jsonText) 
    {
        return gson.fromJson(jsonText, Message.class);
    }
    
}
