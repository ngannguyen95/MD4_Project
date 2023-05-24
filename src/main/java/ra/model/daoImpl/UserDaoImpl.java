package ra.model.daoImpl;

import ra.model.dao.IUserDao;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = null;
        CallableStatement call = null;
        try {
            userList = new ArrayList<>();
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call getListUser()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getInt("role"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getBoolean("sex"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getBoolean("status"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return userList;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  createUser(?,?,?,?,?,?,?)}");
            call.setString(1, user.getUserName());
            call.setString(2, user.getFullName());
            call.setInt(3, user.getAge());
            call.setBoolean(4, user.isSex());
            call.setString(5, user.getPhone());
            call.setString(6, user.getAddress());
            call.setString(7, user.getPassword());
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
    public boolean update(User user) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  updateUser(?,?,?,?,?,?)}");
            call.setInt(1, user.getUserId());
            call.setString(2, user.getUserName());
            call.setString(3, user.getFullName());
            call.setInt(4, user.getAge());
            call.setString(5, user.getPhone());
            call.setString(6, user.getAddress());
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
    public User findById(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        User user = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findUserById(?)}");
            call.setInt(1, integer);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setFullName(rs.getString(3));
                user.setRole(rs.getInt(4));
                user.setAge(rs.getInt(5));
                user.setSex(rs.getBoolean(6));
                user.setPhone(rs.getString(7));
                user.setAddress(rs.getString(8));
                user.setPassword(rs.getString(9));
                user.setStatus(rs.getBoolean(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return user;
    }

    @Override
    public User findByName(User user) {
        Connection conn = null;
        CallableStatement call = null;
        User user1 = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  findUserByName(?)}");
            call.setString(1, user.getUserName());
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                user1 = new User();
                user1.setUserId(rs.getInt(1));
                user1.setUserName(rs.getString(2));
                user1.setFullName(rs.getString(3));
                user1.setRole(rs.getInt(4));
                user1.setAge(rs.getInt(5));
                user1.setSex(rs.getBoolean(6));
                user1.setPhone(rs.getString(7));
                user1.setAddress(rs.getString(8));
                user1.setPassword(rs.getString(9));
                user1.setStatus(rs.getBoolean(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return user1;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  deleteUser(?)}");
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
    public UserLogin login(User user) {
        Connection conn = null;
        CallableStatement call = null;
        UserLogin userLogin = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call checkLogin(?,?)}");
            call.setString(1, user.getUserName());
            call.setString(2, user.getPassword());
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                userLogin = new UserLogin();
                userLogin.setUserId(rs.getInt(1));
                userLogin.setUserName(rs.getString(2));
                userLogin.setFullName(rs.getString(3));
                userLogin.setRole(rs.getInt(4));
                userLogin.setAge(rs.getInt(5));
                userLogin.setSex(rs.getBoolean(6));
                userLogin.setPhone(rs.getString(7));
                userLogin.setAddress(rs.getString(8));
                userLogin.setPassword(rs.getString(9));
                userLogin.setStatus(rs.getBoolean(10));
                userLogin.setCartId(rs.getInt(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return userLogin;
    }

    @Override
    public boolean checkExitsLogin(String userName) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findUserByName(?)}");
            call.setString(1, userName);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeStatus(int idChange) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call changeStatus(?)}");
            call.setInt(1, idChange);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionDB.closeConnection(conn,call);
        }
        return true;
    }

    @Override
    public boolean changePassword(User user) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call changePassword(?,?)}");
            call.setInt(1, user.getUserId());
            call.setString(2,user.getPassword());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionDB.closeConnection(conn,call);
        }
        return true;
    }

}
