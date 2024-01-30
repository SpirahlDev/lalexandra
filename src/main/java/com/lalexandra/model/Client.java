package com.lalexandra.model;

import com.lalexandra.model.Cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lalexandra.controllers.CustomServlet.hashPassword;

public class Client extends AbstractModel{
    public static String DEFAULT_TABLE="client";
    public static String DEFAULT_PRIMARY="id_client";
    private int id_client;
    private String mail_client;
    private String phonenumber_client;
    private String password;
    private String name_client;
    private String firstname_client;

    public void setMail_client(String mail_client) {
        this.mail_client = mail_client;
    }

    public void setPhonenumber_client(String phonenumber_client) {
        this.phonenumber_client = phonenumber_client;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getId_cart() {
        return id_cart;
    }

    private int id_cart;
    private long registration_date;

    public void setData(Map<String,Object> keyValue){
        if(!keyValue.isEmpty()){
            this.name_client=(String) keyValue.get("name_client");
            this.firstname_client=(String) keyValue.get("firstname_client");

            if(keyValue.get("id_cart")!=null){
                id_cart=(Integer)keyValue.get("id_cart");
            }

            if(keyValue.get("id_client")!=null){
                int id=(Integer)keyValue.get("id_client");
                if(id>0){
                    this.id_client=id;
                }
            }
            setPassword((String) keyValue.get(("password_client")));
            setMail_client((String) keyValue.get("mail_client"));
            setPhonenumber_client((String) keyValue.get("phonenumber_client"));
        }else{
            System.err.println("Erreur dans la création d'objet "+getClass().getName());
        }
    }


    public Client(int id_client){
        super();
        if(id_client>0){
            super.initById(id_client);
        }
    }
    public Client(){
        super();
    }

    @Override
    protected void setTableConst() {
        table=DEFAULT_TABLE;
        primaryKey=DEFAULT_PRIMARY;
    }

    public int getClientId(){
        return id_client;
    }

    public String getName(){
        return name_client;
    }

    public String getFirstname(){
        return firstname_client;
    }

    public String getMail(){
        return mail_client;
    }
    public String getPhoneNumber(){
        return phonenumber_client;
    }
    public String getPassword(){
        return password;
    }
    public long getRegistration(){
        return registration_date;
    }

    public static ArrayList<Client> getClients(int id_client) {
        initConnexion();
        String request = "SELECT * FROM Client";
        ArrayList<Client> clientList = new ArrayList<>();

        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            final ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
            int column_nb = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> keyMap = new HashMap<>();
                for (int index = 1; index <= column_nb; index++) {
                    String columnName = metaData.getColumnName(index);
                    Object columnValue = resultSet.getObject(index);
                    keyMap.put(columnName, columnValue);
                }

                Client client = new Client();
                client.setData(keyMap);
                clientList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            freeRessource();
        }

        return clientList;
    }

    @Override
    public boolean save() {
        initConnexion();

        if (mysqlConnection != null) {
            String request;

            if (this.id_client > 0) {
                request = "UPDATE " + table + " SET name_client = ?, firstname_client = ?, mail_client = ?, phonenumber_client = ?, password_client = ? WHERE id_client = ?";
            } else {
                request = "INSERT INTO " + table + " (name_client, firstname_client, mail_client, phonenumber_client, password_client,id_cart) VALUES (?, ?, ?, ?, ?,?)";
            }

            try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request)) {

                if (this.id_client > 0) {
                    preparedStatement.setString(1, this.name_client);
                    preparedStatement.setString(2, this.firstname_client);
                    preparedStatement.setString(3, this.mail_client);
                    preparedStatement.setString(4, this.phonenumber_client);
                    preparedStatement.setString(5, this.password);
                    preparedStatement.setInt(6, this.id_client);
                } else { //il s'agit d'une insertion
                    preparedStatement.setString(1, this.name_client);
                    preparedStatement.setString(2, this.firstname_client);
                    preparedStatement.setString(3, this.mail_client);
                    preparedStatement.setString(4, this.phonenumber_client);
                    preparedStatement.setString(5, hashPassword(this.password));

                    Cart cart=new Cart();
                    int id_cart=cart.createNewCart();

                    if(id_cart>0){
                        preparedStatement.setInt(6,id_cart);
                        return preparedStatement.executeUpdate() > 0;
                    }else{
                        System.err.println("Identifiant de panier invalide");
                    }
                }
                // Exécution de la requête SQL

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                freeRessource();
            }
        }else{
            System.err.println("mysqlConnection vide ");
        }

        return false;
    }

    public static Client getClientByEmail(String email){
        initConnexion();
        String request = "SELECT * FROM Client WHERE mail_client=?";

        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request)){

             preparedStatement.setString(1,email);
             ResultSet resultSet = preparedStatement.executeQuery();

            final ResultSetMetaData metaData= resultSet.getMetaData();
            int column_nb = metaData.getColumnCount();

            if (resultSet.next()) {
                Map<String, Object> keyMap = new HashMap<>();
                for (int index = 1; index <= column_nb; index++) {
                    String columnName = metaData.getColumnName(index);
                    Object columnValue = resultSet.getObject(index);
                    keyMap.put(columnName, columnValue);
                }

                Client client = new Client();
                client.setData(keyMap);
                return client;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            freeRessource();
        }

        return null;
    }

}
