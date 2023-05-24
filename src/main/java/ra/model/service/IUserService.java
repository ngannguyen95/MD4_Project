package ra.model.service;

import ra.model.entity.User;
import ra.model.entity.UserLogin;

public interface IUserService extends IGenericService<User,Integer> {
    UserLogin login(User user);
    boolean checkExitsLogin(String userName);
    boolean changeStatus(int idChange);
    boolean changePassword(User user);
}
