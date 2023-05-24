package ra.model.serviceImpl;

import ra.model.dao.IUserDao;
import ra.model.daoImpl.UserDaoImpl;
import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public User findById(Integer integer) {
        return userDao.findById(integer);
    }

    @Override
    public User findByName(User user) {
        return userDao.findByName(user);
    }

    @Override
    public boolean delete(Integer integer) {
        return userDao.delete(integer);
    }

    @Override
    public UserLogin login(User user) {
        return userDao.login(user);
    }

    @Override
    public boolean checkExitsLogin(String userName) {
        return userDao.checkExitsLogin(userName);
    }

    @Override
    public boolean changeStatus(int idChange) {
        return userDao.changeStatus(idChange);
    }

    @Override
    public boolean changePassword(User user) {
        return userDao.changePassword(user);
    }
}
