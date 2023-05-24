package ra.model.dao;

import ra.model.entity.User;
import ra.model.entity.UserLogin;

import java.util.List;

public interface IUserDao extends IDao<User,Integer> {
    UserLogin login(User user);
    boolean checkExitsLogin(String userName);
    boolean changeStatus(int idChange);
    boolean changePassword(User user);
}
