package com.lalexandra.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartContent extends AbstractModel{
    public static final String TABLE_NAME="cart_content";

    public Product product;
    public float unit_prod_price;
    private int id_cart;

    public Product getProduct() {
        return product;
    }

    public float getUnit_prod_price() {
        return unit_prod_price;
    }

    public int getCart_prod_quantity() {
        return cart_prod_quantity;
    }


    public int cart_prod_quantity;

    public CartContent(int id_cart,int id_prod){
        this.id_cart=id_cart;
        initCartContent(id_prod);
    }
    public CartContent(int id_cart){
        this.id_cart=id_cart;
    }
    public CartContent(){}

    @Override
    public void setData(Map<String, Object> keyValue) {
        int id_prod=(Integer)keyValue.get("id_prod");
        if(id_prod>0){
            product=new Product(id_prod);
            unit_prod_price=(Float)keyValue.get("unit_prod_price");
            cart_prod_quantity=(Integer)keyValue.get("cart_prod_quantity");
        }
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    protected void setTableConst() {
        table=TABLE_NAME;
    }

    public static ArrayList<CartContent> getProductListIn(int id_cart){
        ArrayList<CartContent> productIn=new ArrayList<>();
        String request="SELECT * FROM "+TABLE_NAME+" WHERE id_cart=?";

        initConnexion();
        try(PreparedStatement preparedStatement=mysqlConnection.prepareStatement(request)){
            preparedStatement.setInt(1,id_cart);

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Map<String,Object> keyMap=new HashMap<>();
                ResultSetMetaData metaData=resultSet.getMetaData();
                int column_nb=metaData.getColumnCount();

                for (int i=1;i<=column_nb;i++){
                    String col_name=metaData.getColumnName(i);
                    keyMap.put(col_name,resultSet.getObject(i));
                }

                CartContent cartContent=new CartContent();
                cartContent.setData(keyMap);
                productIn.add(cartContent);
            }

        }catch (Exception e){
            System.out.println("Une erreur s'est produite lors du chargement des produit du panier");
            e.printStackTrace();
        }finally {
            freeRessource();
        }

        return productIn;
    }

    private void initCartContent(int id_prod){
        String request="SELECT * FROM "+TABLE_NAME+" WHERE id_cart=? AND id_prod=?";

        initConnexion();
        try(PreparedStatement preparedStatement=mysqlConnection.prepareStatement(request)){
            preparedStatement.setInt(1,this.id_cart);
            preparedStatement.setInt(2,id_prod);

            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                Map<String,Object> keyMap=new HashMap<>();
                ResultSetMetaData metaData=resultSet.getMetaData();
                int column_nb=metaData.getColumnCount();

                for (int i=1;i<=column_nb;i++){
                    String col_name=metaData.getColumnName(i);
                    keyMap.put(col_name,resultSet.getObject(i));
                }

                this.setData(keyMap);
            }

        }catch (Exception e){
            System.out.println("Une erreur s'est produite lors du chargement d'un produit du panier");
            e.printStackTrace();
        }finally {
            freeRessource();
        }
    }

    public CartContent setProduct(Product product){
        this.product=product;
        return this;
    }

    public CartContent setCart_prod_quantity(int quantity){
        this.cart_prod_quantity=quantity;
        return this;
    }

}
