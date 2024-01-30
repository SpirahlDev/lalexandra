package com.lalexandra.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order extends AbstractModel{
    private int id_order;
    private int id_client;
    public String table="order",primaryKey="id_order";
    // public static String table="client",primarykey="id_client";

    private String status_order,date_order,payment_type;
    private float amount_order;
    

    public void setData(Map<String,Object> keyValue){
		
        this.status_order=(String) keyValue.get("status_order");
    }

    
    public Order(int id_order){
        super();
        if(id_order>0){
            super.initById(id_order);
        }
    }
    public Order(){
        
    }

    @Override
    protected void setTableConst() {

    }

    public int getOrderId(){
        return id_order;
    }

    public String getstatusorder(){
        return status_order;
    }

    public float getAmountorder(){
        return amount_order;
    }
    public String getDateorder(){
        return date_order;
    }

   public static ArrayList<Order> getOrdersList(int id_order) {
	   
		String request = "SELECT order.*,client.* FROM order JOIN client ON order.id_client=client.id_client";
		ArrayList<Order> orderlist = new ArrayList<>();

    try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request);
        ResultSet resultSet = preparedStatement.executeQuery()) {

        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
        int column_nb = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> keyMap = new HashMap<>();
            for (int index = 1; index <= column_nb; index++) {
                String columnName = metaData.getColumnName(index);
                Object columnValue = resultSet.getObject(index);
                keyMap.put(columnName, columnValue);
            }

            Order order = new Order();
            order.setData(keyMap);
            orderlist.add(order);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orderlist;
}

@Override
public boolean save() {
	if(mysqlConnection==null) {
		   initConnexion();
	   }
	// TODO Auto-generated method stub
	return false;
}

}
