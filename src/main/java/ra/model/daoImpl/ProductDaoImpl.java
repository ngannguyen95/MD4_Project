package ra.model.daoImpl;

import ra.model.dao.IProductDao;
import ra.model.entity.Product;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements IProductDao {
    @Override
    public List<Product> findAll() {
        Connection conn = null;
        List<Product> productList = new ArrayList<>();
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call getListProduct()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProId(rs.getInt("proId"));
                product.setProName(rs.getString("proName"));
                product.setImage(rs.getString("image"));
                product.setPrice(rs.getFloat("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCataId(rs.getInt("cataId"));
                product.setProStatus(rs.getBoolean("proStatus"));
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return productList;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  createProduct(?,?,?,?,?)}");
            call.setString(1, product.getProName());
            call.setString(2, product.getImage());
            call.setFloat(3, product.getPrice());
            call.setInt(4, product.getQuantity());
            call.setInt(5, product.getCataId());
            int check = call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return true;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  updateProduct(?,?,?,?,?,?)}");
            call.setInt(1, product.getProId());
            call.setString(2, product.getProName());
            call.setString(3, product.getImage());
            call.setFloat(4, product.getPrice());
            call.setInt(5, product.getQuantity());
            call.setInt(6, product.getCataId());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return true;
    }

    @Override
    public Product findById(Integer integer) {

        Connection conn = null;
        CallableStatement call = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findProductById(?)}");
            call.setInt(1, integer);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProId(rs.getInt(1));
                product.setProName(rs.getString(2));
                product.setImage(rs.getString(3));
                product.setPrice(rs.getFloat(4));
                product.setQuantity(rs.getInt(5));
                product.setCataId(rs.getInt(6));
                product.setProStatus(rs.getBoolean(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return product;
    }

    @Override
    public Product findByName(Product product) {

        Connection conn = null;
        CallableStatement call = null;
        Product product1 = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  findProductByName(?)}");
            call.setString(1, product.getProName());
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                product1 = new Product();
                product1.setProId(rs.getInt(1));
                product1.setProName(rs.getString(2));
                product1.setImage(rs.getString(3));
                product1.setPrice(rs.getFloat(4));
                product1.setQuantity(rs.getInt(5));
                product1.setCataId(rs.getInt(6));
                product1.setProStatus(rs.getBoolean(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return product1;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  deleteProduct(?)}");
            call.setInt(1, integer);
            call.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return true;
    }


    @Override
    public boolean changeStatusProduct(int idChange) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call changeStatusProduct(?)}");
            call.setInt(1, idChange);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
