package com.lalexandra.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Delivery extends AbstractModel{
    private int id_delivery;
    public final String TABLE_NAME="delivery",PRIMARY_KEY_NAME="id_delivery";
    private static ArrayList<Delivery> deliveryList;

    private String delivered_date,planned_delivered_date;
    private int id_order;

    public void setData(Map<String,Object> keyValue){
        this.delivered_date=(String) keyValue.get("delivered_date");
        this.planned_delivered_date=(String) keyValue.get("planned_delivered_date");
    }

    
    public Delivery(int id_delivery){
        super();
        if(id_delivery>0){
            super.initById(id_delivery);
        }
    }
    public Delivery(){
        setTableConst();
    }

    @Override
    protected void setTableConst() {
        table=TABLE_NAME;
        primaryKey=PRIMARY_KEY_NAME;
    }

    public int getDeliveryId(){
        return id_delivery;
    }

    public String getplannedate(){
        return planned_delivered_date;
    }

    public int getOrderid(){
        return id_order;
    }

  

   public static ArrayList<Delivery> getDeliveriesList(int id_delivery) {
    String request = "SELECT * FROM Delivery";
    ArrayList<Delivery> deliveryList = new ArrayList<>();

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

            Delivery delivery = new Delivery();
            delivery.setData(keyMap);
            deliveryList.add(delivery);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return deliveryList;
}

@Override
public boolean save() {
	// TODO Auto-generated method stub
	return false;
}

}
