/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    private static ArrayList<String> getDatabaseScriptFromFile(){
        ArrayList<String> dataDefenitionLanguage = new ArrayList<>();
        final String FILE_PATH = "database-construction-DDL.txt";
        // this should retrun a list of DDL Strings for the table construction
        // process from file: "database-construction-DDL"
        return dataDefenitionLanguage;
    }
    
    private static void buildDatabaseTables(){
        ArrayList<String> dataDefenitionLanguage = getDatabaseScriptFromFile();
        // builds the table from the DDL list
    }
    
    public static void constructDatabase(){
        if(!databaseExists())
        {
            connect();
            buildDatabaseTables();
            closeConnection();
        }
        else
            System.out.println("Database is already constructes...");
    }
    
    public static boolean connect(){
        boolean success = false;
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
            success = true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    
    public static boolean closeConnection(){
        boolean success = false;
        try {
            statement.close();
            connection.close();
            success =  true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
    
    public static int executeUpdate(String sqlQuery){
        int rowsAffected = -1;
        if(!connect())// connection failed
            return rowsAffected;
        try {
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
    
    public static ResultSet executeQuery(String sqlQuery){
        ResultSet rs = null;
        if(!connect())
            return rs; // connection failed
        try {
            System.out.println(sqlQuery);
            rs = statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
