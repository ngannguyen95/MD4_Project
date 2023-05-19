package ra.service;

import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.service.IGenericService;

public interface IUserService extends IGenericService<User,Integer> {
    UserLogin login(User user);
    boolean checkExitsLogin(String userName);
    boolean changeStatus(int idChange);
}
