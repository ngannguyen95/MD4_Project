package ra.model.daoImpl;

import ra.model.dao.ICartDao;
import ra.model.entity.*;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements ICartDao {

    @Override
    public List<CartDetail> findAll() {
        return null;
    }

    @Override
    public boolean save(CartDetail cartDetail) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  createCartdetail(?,?,?)}");
            call.setInt(1, cartDetail.getProId());
            call.setInt(2, cartDetail.getCartId());
            call.setInt(3, cartDetail.getQuantity());
            int check = call.executeUpdate();
            if (check == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return false;
    }

    @Override
    public boolean update(CartDetail cart) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  updateCart(?,?)}");
            call.setInt(1, cart.getCdtId());
            call.setInt(2, cart.getQuantity());
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
    public CartDetail findById(Integer integer) {
        return null;
    }

    @Override
    public CartDetail findByName(CartDetail cartDetail) {
        return null;
    }


    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  deleteCartDetail(?)}");
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
    public CartDetail checkExitProduct(Integer proId, Integer cartId) {
        List<CartDetail> list = findCartByCartIdUserLogin(cartId);
        for (CartDetail cartDetail : list) {
            if (cartDetail.getProId() == proId) {
                return cartDetail;
            }
        }
        return null;
    }

    @Override
    public int updateCartByCartId(Cart cart) {
        Connection conn = null;
        CallableStatement call = null;
        int newCartId = -1;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  updateCartByCartId(?,?,?,?,?,?,?)}");
            call.setInt(1, cart.getCartId());
            call.setInt(2, cart.getUserId());
            call.setString(3, cart.getUserReceiver());
            call.setFloat(4, cart.getTotal());
            call.setString(5, cart.getPhone());
            call.setString(6, cart.getAddress());
            call.registerOutParameter(7, Types.INTEGER);
            call.executeUpdate();
            newCartId = call.getInt(7);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return newCartId;
    }

    @Override
    public List<Cart> findBillByUserId(Integer userId) {
        List<Cart> cartList = null;
        Connection conn = null;
        CallableStatement call = null;
        try {
            cartList = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findBillByUserId(?)}");
            call.setInt(1, userId);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cartId"));
                cart.setOrderDate(rs.getDate("orderDate"));
                cart.setUserReceiver(rs.getString("userReceiver"));
                cart.setPhone(rs.getString("phone"));
                cart.setAddress(rs.getString("address"));
                cart.setTotal(rs.getFloat("total"));
                cartList.add(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartList;
    }

    @Override
    public List<CartDetail> findCartDetailByCartId(Integer cartId) {
        List<CartDetail> cartList = null;
        Connection conn = null;
        CallableStatement call = null;
        try {
            cartList = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findCartDetailByCartId(?)}");
            call.setInt(1, cartId);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCdtId(rs.getInt("cdtId"));
                cartDetail.setProId(rs.getInt("proId"));
                cartDetail.setCartId(rs.getInt("cartId"));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setProName(rs.getString("proName"));
                cartDetail.setImage(rs.getString("image"));
                cartDetail.setPrice(rs.getFloat("price"));
                cartList.add(cartDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartList;

    }

    @Override
    public Cart findCartById(Integer cId) {
        Connection conn = null;
        CallableStatement call = null;
        Cart cart = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findCartById(?)}");
            call.setInt(1, cId);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                cart = new Cart();
                cart.setCartId(rs.getInt(1));
                cart.setUserId(rs.getInt(2));
                cart.setUserReceiver(rs.getString(3));
                cart.setTotal(rs.getFloat(4));
                cart.setPhone(rs.getString(5));
                cart.setOrderDate(rs.getDate(6));
                cart.setAddress(rs.getString(7));
                cart.setStatus(rs.getBoolean(8));
                cart.setStyle(rs.getBoolean(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return cart;
    }

    @Override
    public List<CartDetail> findCartByCartIdUserLogin(Integer userId) {
        List<CartDetail> cartList = null;
        Connection conn = null;
        CallableStatement call = null;
        try {
            cartList = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findCartByCartIdUserLogin(?)}");
            call.setInt(1, userId);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                CartDetail cartDetail = new CartDetail();
                cartDetail.setCdtId(rs.getInt("cdtId"));
                cartDetail.setProId(rs.getInt("proId"));
                cartDetail.setCartId(rs.getInt("cartId"));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setProName(rs.getString("proName"));
                cartDetail.setImage(rs.getString("image"));
                cartDetail.setPrice(rs.getFloat("price"));
                cartList.add(cartDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionDB.closeConnection(conn, call);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartList;
    }


}
