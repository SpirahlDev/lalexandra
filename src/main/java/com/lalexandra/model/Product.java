package com.lalexandra.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class Product extends AbstractModel{
    public static final String TABLE_NAME="product";
    public final static String PRIMARY_KEY_NAME="id_prod";

    //atributes
	private int id_prod,
    quantity_prod;

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public void setQuantity_prod(int quantity_prod) {
        this.quantity_prod = quantity_prod;
    }

    public void setName_prod(String name_prod) {
        this.name_prod = name_prod;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    public void setImageURI_prod(String imageURI_prod) {
        this.imageURI_prod = imageURI_prod;
    }

    public void setPrice_prod(float price_prod) {
        this.price_prod = price_prod;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    private String name_prod,
    description_prod,
    imageURI_prod;
    float price_prod;
    public Category cat;
    //

    public Product(int id_prod){
        super();
        super.initById(id_prod);
    }
    public Product(){
        setTableConst();
    }

    @Override
    protected void setTableConst() {
        table=TABLE_NAME;
        primaryKey=PRIMARY_KEY_NAME;
    }

    public void setData(Map<String, Object> keyValueData) {
        if (keyValueData.containsKey("name_prod")) {
            this.name_prod = (String) keyValueData.get("name_prod");
        }

        if (keyValueData.containsKey("description_prod")) {
            this.description_prod = (String) keyValueData.get("description_prod");
        }

        if (keyValueData.containsKey("imageURI_prod")) {
            this.imageURI_prod = (String) keyValueData.get("imageURI_prod");
        }

        if (keyValueData.containsKey("quantity_prod")) {
            Object quantityValue = keyValueData.get("quantity_prod");

            if (quantityValue != null) {
                this.quantity_prod = Integer.valueOf(quantityValue.toString());
            }
        }

        if(keyValueData.containsKey("id_prod")){
            Object id = keyValueData.get("id_prod");
            int id_prod=Integer.valueOf(keyValueData.get("id_prod").toString());

            if(id!=null && id_prod>0){
                this.id_prod=id_prod;
            }else{
                // throw new Exception("Identifiant non correct");
            }
        }

        if(keyValueData.containsKey("price_prod")){
        	Object id = keyValueData.get("price_prod");
        	float price_prod=Float.parseFloat(keyValueData.get("price_prod").toString());

        	if(id!=null && id_prod>0){
        		this.price_prod=price_prod;
        	}else{
        		// throw new Exception("Identifiant non correct");
        	}
        }

        if(keyValueData.containsKey("id_category")){
            int id_category=Integer.valueOf(keyValueData.get("id_category").toString());
            this.cat=new Category(id_category);
        }else if(this.cat==null){
            //
        }



    }


    public void setCategory(Category cat){
        this.cat=cat;
    }

    public boolean save() {
            initConnexion();

        try {
            if (this.id_prod > 0) {
                String request = "UPDATE " + table + " SET name_prod = ?, description_prod = ?, imageURI_prod = ?, quantity_prod = ?, id_category = ? WHERE id_prod = ?";
                PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request);

                preparedStatement.setString(1, this.name_prod);
                preparedStatement.setString(2, this.description_prod);
                preparedStatement.setString(3, this.imageURI_prod);
                preparedStatement.setInt(4, this.quantity_prod);
                preparedStatement.setInt(5, cat.getCategoryId());
                preparedStatement.setInt(6, this.id_prod);

                return preparedStatement.executeUpdate() > 0;
            } else {// il s'agit d'une insertion, si id_prod est null

                String request = "INSERT INTO " + table + " (name_prod, description_prod, imageURI_prod, quantity_prod, id_category) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request);

                preparedStatement.setString(1, this.name_prod);
                preparedStatement.setString(2, this.description_prod);
                preparedStatement.setString(3, this.imageURI_prod);
                preparedStatement.setInt(4, this.quantity_prod);
                preparedStatement.setInt(5, cat.getCategoryId());

                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static ArrayList<Product> getProductList(int id_category) throws SQLException {
        initConnexion();
        ArrayList<Product> productList = new ArrayList<>();

        String request = "SELECT * FROM product JOIN category ON product.id_category=category.id_category ORDER BY add_date DESC";

        if (id_category > 0) {
            request = "SELECT * FROM product JOIN category ON product.id_category=category.id_category WHERE product.id_category=? ORDER BY add_date DESC";
        }

        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request)) {
            if (id_category > 0) {
                preparedStatement.setInt(1, id_category);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                Map<String, Object> keyMap = new HashMap<>();

                for (int index = 1; index <= columnCount; index++) {
                    String columnName = metaData.getColumnName(index);
                    Object columnValue = resultSet.getObject(index);
                    keyMap.put(columnName, columnValue);
                }

                Product product = new Product();
                product.setData(keyMap);
                productList.add(product);
            }
        } catch (Exception e) {
            System.out.println("Impossible de charger une collection de produits");
            e.printStackTrace();
        } finally {
           freeRessource();
        }

        return productList;
    }


    public static ArrayList<Product> getProductList(int id_category, int limit) throws SQLException {
        initConnexion();
        ArrayList<Product> productList = new ArrayList<>();
        String request = "SELECT * FROM product JOIN category ON product.id_category=category.id_category WHERE ";

        if (id_category <= 1) {
            request += "product.id_category > ? LIMIT ?";
        } else {
            request += "product.id_category = ? LIMIT ?";
        }

        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(request)) {
            preparedStatement.setInt(1, id_category);
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int column_nb = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> keyMap = new HashMap<>();
                for (int index = 1; index <= column_nb; index++) {
                    String columnName = metaData.getColumnName(index);
                    Object columnValue = resultSet.getObject(index);
                    keyMap.put(columnName, columnValue);
                }
                Product product = new Product();
                product.setData(keyMap);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Erreur, impossible de charger les produits");
            e.printStackTrace();
        } finally {
           freeRessource();
        }

        return productList;
    }




    public int getId_prod() {
		return id_prod;
	}
	public int getQuantity_prod() {
		return quantity_prod;
	}
	public String getName_prod() {
		return name_prod;
	}
	public String getDescription_prod() {
		return description_prod;
	}
	public String getImageURI_prod() {
		return imageURI_prod;
	}

	public String getPrice_prod() {
		return String.valueOf(price_prod);
	}

    public float getFloatPrice(){
        return price_prod;
    }

    public static Product getProductByName(String name_prod){
        initConnexion();
        Map<String,Object> keyMap=new HashMap<>();
        String request="SELECT * FROM "+TABLE_NAME+" WHERE name_prod LIKE ?";

        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement=mysqlConnection.prepareStatement(request)){
            preparedStatement.setString(1,"%"+name_prod+"%");

            resultSet=preparedStatement.executeQuery();

            if (resultSet.next()) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int column_nb = resultSetMetaData.getColumnCount();

                for (int i = 1; i <= column_nb; i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    keyMap.put(columnName, columnValue);
                }

                Product pr = new Product();
                pr.setData(keyMap);
                return pr;
            } else {
                System.out.println("Jeu de resultat vide");
            }

        }catch (SQLException e){
            System.out.println("Erreur lors de la recherche de produit par nom [getProductByName]");
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // Gérer l'exception de fermeture
                    e.printStackTrace();
                }
            }
            freeRessource();
        }
        return null;
    }

    public static ArrayList<Product> searchProduct(String pattern) {
        ArrayList<Product> products = new ArrayList<>();
        initConnexion();

        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE name_prod LIKE ?";

        try (PreparedStatement preparedStatement = mysqlConnection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%"+pattern + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();

                product.setId_prod(resultSet.getInt("id_prod"));
                product.setName_prod(resultSet.getString("name_prod"));
                product.setDescription_prod(resultSet.getString("description_prod"));
                product.setPrice_prod(resultSet.getFloat("price_prod"));
                product.setImageURI_prod(resultSet.getString("imageURI_prod"));
                product.setQuantity_prod(resultSet.getInt("quantity_prod"));
                product.setCategory(new Category(resultSet.getInt("id_category")));

                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            freeRessource();
        }

        return products;
    }

}


