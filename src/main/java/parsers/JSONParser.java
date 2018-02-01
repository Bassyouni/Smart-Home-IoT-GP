/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author Mahmoud Mokhtar
 * @param <T>
 */
public interface JSONParser<T> 
{
    static final Gson gson = new Gson();
    
    public  ArrayList<T> getArrayOfObjects(String jsonText);
    public T getObject(String jsonText);
    
    
    
}
