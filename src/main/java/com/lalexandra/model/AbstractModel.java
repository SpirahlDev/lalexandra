package com.lalexandra.model;

import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class AbstractModel{
    public static final String DUPLICATE_KEY="DUPLICATE_KEY";
    public static final String SUCCESS="INSERT_SUCCESS";
    public static final String FAIL="INSERT_FAIL";

    protected static Connection mysqlConnection;
    protected static BasicDataSource dataSource;
    public String table,primaryKey;
    public abstract void setData(Map<String,Object> keyValue);
    public abstract boolean save();

   
    public AbstractModel(){
    	initDataSource();
        setTableConst();
    }
    protected abstract void setTableConst();
    protected static void initDataSource() {
        System.out.println("etablissement du datasource");

         try {
             Context initContext = new InitialContext();
             dataSource = (BasicDataSource) initContext.lookup("java:comp/env/jdbc/lalexandraDB");
             //mysqlConnection= dataSource.getConnection();
             System.out.println("ETABLIS");
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("NON ETABLIS");
         }
    }
    protected static void initConnexion() {
        if(dataSource==null){
            initDataSource();
        }
        try{
            mysqlConnection=dataSource.getConnection();
        }catch (SQLException e){
            System.out.println("Obtention de la connexion MySQL a echoue");
            e.printStackTrace();
        }

    }
    protected void initById(int id_model){
    	initConnexion();
        Map<String,Object> keyMap=new HashMap<>();
        String request="SELECT * FROM "+this.table+" WHERE "+this.primaryKey+" = ?"; //ecriture de la requète

        try(PreparedStatement preparedStatement=mysqlConnection.prepareStatement(request)){
            preparedStatement.setInt(1, id_model);

            try(ResultSet resultSet=preparedStatement.executeQuery()){

                ResultSetMetaData metaData=resultSet.getMetaData();
                int column_nb=metaData.getColumnCount();

                while (resultSet.next()) {

                    for (int index = 1; index <= column_nb; index++) {
                        String columName=metaData.getColumnName(index);
                        Object columnValue = resultSet.getObject(index);
                        keyMap.put(columName, columnValue);
                    }
                }
                this.setData(keyMap);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            freeRessource();
        }
        //preparation de la requete, de sorte à avoir un objet permettant de l'executer
        
    }

    public static void freeRessource(){
        if(mysqlConnection!=null) {
            try{
                mysqlConnection.close();
            }catch (SQLException e){
                System.out.println("Impossible de liberer la ressource de connexion mysql");
                e.printStackTrace();
            }
            mysqlConnection=null;
        }
    }


    

}