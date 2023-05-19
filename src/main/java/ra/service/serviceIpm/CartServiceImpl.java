package ra.service.serviceIpm;

import ra.model.entity.Cart;
import ra.model.entity.CartDetail;
import ra.model.entity.User;
import ra.model.util.ConnectionDB;
import ra.service.ICartService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements ICartService {


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
    public boolean updateCartByCartId(Cart cart) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  updateCartByCartId(?,?,?,?,?,?)}");
            call.setInt(1, cart.getCartId());
            call.setInt(2, cart.getUserId());
            call.setString(3, cart.getUserService());
            call.setFloat(4, cart.getTotal());
            call.setString(5, cart.getPhone());
            call.setString(6, cart.getAddress());
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
    public List<CartDetail> findCartByCartIdUserLogin(Integer cartId) {
        List<CartDetail> cartList = null;
        Connection conn = null;
        CallableStatement call = null;
        try {
            cartList = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findCartByCartIdUserLogin(?)}");
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
}
