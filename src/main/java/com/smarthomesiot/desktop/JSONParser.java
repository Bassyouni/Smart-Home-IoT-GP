/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthomesiot.desktop;

import java.util.ArrayList;

/**
 *
 * @author Mahmoud Mokhtar
 * @param <T>
 */
public interface JSONParser<T> 
{
 
    public  ArrayList<T> getArrayOfObjects(String jsonText);
    public T getObject(String jsontext);
    
    
    
}
