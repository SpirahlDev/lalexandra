package com.lalexandra.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Category extends AbstractModel{
    private int id_category;
    public final String PRIMARY_KEY_NAME="id_category",TABLE_NAME="category";
    private String name_category,description_category,image_category,system_name;

    public void setData(Map<String,Object> keyValue){
        this.name_category=(String) keyValue.get("name_category");
        this.description_category=(String) keyValue.get("description_category");
        this.image_category=(String) keyValue.get("image_category");
        this.system_name=(String)keyValue.get("system_name");
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public void setDescription_category(String description_category) {
        this.description_category = description_category;
    }

    public Category(int id_category){
        super();
        if(id_category>0){
            super.initById(id_category);
        }
    }
    public Category(){
        setTableConst();
    }

    protected void setTableConst(){
        table=TABLE_NAME;
        primaryKey=PRIMARY_KEY_NAME;
    }
    public String system_name() {
    	return this.system_name;
    }
    public int getCategoryId(){
        return id_category;
    }

    public String getName(){
        return name_category;
    }

    public String getDescription(){
        return description_category;
    }
    public String getImage(){
        return image_category;
    }

   public static ArrayList<Category> getCategories(int id_category) {
        initConnexion();

        String request = "SELECT * FROM category";
        ArrayList<Category> categoryList = new ArrayList<>();

        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int column_nb = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> keyMap = new HashMap<>();
                for (int index = 1; index <= column_nb; index++) {
                    String columnName = metaData.getColumnName(index);
                    Object columnValue = resultSet.getObject(index);
                    keyMap.put(columnName, columnValue);
                }

                Category category = new Category();
                category.setData(keyMap);
                categoryList.add(category);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           freeRessource();
        }

        return categoryList;
}

@Override
public boolean save() {
    initConnexion();
	// TODO Auto-generated method stub
	return false;
}

}
