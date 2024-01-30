package com.lalexandra.model;

import com.lalexandra.controllers.Order;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lalexandra.model.CartContent.getProductListIn;

public class Cart extends AbstractModel{
    public static final String TABLE_NAME="cart";
    public final String PRIMARY_KEY_NAME="id_cart";

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public int getId_cart() {
        return id_cart;
    }

    private int id_cart;
    private ArrayList<CartContent> cartContents=new ArrayList<>();

    @Override
    public void setData(Map<String, Object> keyValue) {

    }


    public Cart(Client client){
        super();
        if(client!=null && client.getClientId()>0){
            id_cart=client.getId_cart();
            initCart(id_cart);
        }
    }

    public Cart(){}
    public Cart(int id_cart){
        this.id_cart=id_cart;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    protected void setTableConst() {
        table=TABLE_NAME;
        primaryKey=PRIMARY_KEY_NAME;
    }

    private void initCart(int id_cart){
        cartContents=CartContent.getProductListIn(id_cart);
    }

    public int createNewCart() {
        initConnexion();
        String sql = "INSERT INTO cart () VALUES ()";
        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    return this.id_cart=resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            freeRessource();
        }

        return 0;
    }

    public String addToCart(CartContent cartContent){
        String query="INSERT INTO "+CartContent.TABLE_NAME+" (id_cart,id_prod,unit_prod_price,cart_prod_quantity) VALUES (?,?,?,?)";
        String query_update_nb="UPDATE "+Product.TABLE_NAME+" SET quantity_prod=? WHERE "+Product.PRIMARY_KEY_NAME+"="+cartContent.getProduct().getId_prod();
        initConnexion();
        try(PreparedStatement preparedStatement=mysqlConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement_update=mysqlConnection.prepareStatement(query_update_nb)){

            preparedStatement.setInt(1,this.id_cart);
            preparedStatement.setInt(2,cartContent.getProduct().getId_prod());
            preparedStatement.setFloat(3,cartContent.getProduct().getFloatPrice());
            preparedStatement.setInt(4,cartContent.getCart_prod_quantity());

            preparedStatement_update.setInt(1,cartContent.getProduct().getQuantity_prod()-cartContent.getCart_prod_quantity());

            int result=preparedStatement.executeUpdate();
            int result2=preparedStatement_update.executeUpdate();
            return (result>0 && result2>0)?SUCCESS:FAIL;
        }catch (SQLException e){
            if(e.getErrorCode()==1062){
                return DUPLICATE_KEY;
            }
        }finally {
            freeRessource();
        }

        return FAIL;
    }

    public static Map<String,Float> getCartPriceInfo(int id_cart){
        Map<String,Float> price_info=new HashMap<>();

        ArrayList<CartContent> cartContents=getProductListIn(id_cart);
        float price_cart=0;

        for (CartContent content:cartContents) {
            price_cart+=content.getProduct().getFloatPrice();
        }
        price_info.put("TOTAL_PRODUCT",price_cart);
        price_info.put("TOTAL",price_cart+Order.TAXE);
        return price_info;
    }

}
