/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cdc
 */
public class DatabaseManager {
    private static final String DATABASE_PATH =  "AppDB";
    private static final String URL = "jdbc:sqlite:" + DATABASE_PATH;
    private static Connection connection;
    private static Statement statement;
    
    private static boolean databaseExists(){
        boolean exists = true;
        File databaseFile = new File(DATABASE_PATH);
        if( !(databaseFile.exists() || databaseFile.isFile() || databaseFile.isDirectory() ))
            exists = false;
        return exists;
        
    }
    
    private static ArrayList<String> getDatabaseScriptFromFile() throws FileNotFoundException{
        final String FILE_PATH = "database-construction-DDL.txt";
        ArrayList<String> dataDefenitionLanguage = new ArrayList<>();
        File ddlScript = new File(FILE_PATH);
        Scanner in = new Scanner(ddlScript);
        while(in.hasNext())
            dataDefenitionLanguage.add(in.nextLine());
        in.close();
        return dataDefenitionLanguage;
    }
    
    private static void buildDatabaseTables() throws FileNotFoundException{
        ArrayList<String> dataDefenitionLanguage = getDatabaseScriptFromFile();
        for(int i = 0; i < dataDefenitionLanguage.size(); i++){
            executeUpdate( dataDefenitionLanguage.get(i) );
        }
    }
    
    public static void constructDatabase(){
        if(!databaseExists())
        {
            connect();
            try {
                buildDatabaseTables();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection();
        }
        else
            System.out.println("Database is already constructes...");
    }
    
    public static void connect(){
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static boolean isConnected(){
        try {
            return connection.isValid(2);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void closeConnection(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int executeUpdate(String sqlQuery){
        int rowsAffected = -1;
        connect();
        if(!isConnected())// connection failed
            return rowsAffected;
        try {
            System.out.println(sqlQuery);
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
    
    public static ResultSet executeQuery(String sqlQuery){
        ResultSet rs = null;
        connect();
        if(!isConnected())// connection failed
            return rs; 
        try {
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
