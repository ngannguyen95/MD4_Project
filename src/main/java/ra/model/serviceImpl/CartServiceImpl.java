package ra.model.serviceImpl;

import ra.model.dao.ICartDao;
import ra.model.daoImpl.CartDaoImpl;
import ra.model.entity.Cart;
import ra.model.entity.CartDetail;
import ra.model.service.ICartService;

import java.util.List;

public class CartServiceImpl implements ICartService {

    private ICartDao cartDao = new CartDaoImpl();

    @Override
    public List<CartDetail> findCartByCartIdUserLogin(Integer cartId) {
        return cartDao.findCartByCartIdUserLogin(cartId);
    }

    @Override
    public CartDetail checkExitProduct(Integer proId, Integer cartId) {
        return cartDao.checkExitProduct(proId, cartId);
    }

    @Override
    public int updateCartByCartId(Cart cart) {
        return cartDao.updateCartByCartId(cart);
    }

    @Override
    public List<Cart> findBillByUserId(Integer userId) {
        return cartDao.findBillByUserId(userId);
    }

    @Override
    public List<CartDetail> findCartDetailByCartId(Integer cartId) {
        return cartDao.findCartDetailByCartId(cartId);
    }

    @Override
    public Cart findCartById(Integer cId) {
        return cartDao.findCartById(cId);
    }


    @Override
    public List<CartDetail> findAll() {
        return cartDao.findAll();
    }

    @Override
    public boolean save(CartDetail cartDetail) {
        return cartDao.save(cartDetail);
    }

    @Override
    public boolean update(CartDetail cartDetail) {
        return cartDao.update(cartDetail);
    }

    @Override
    public CartDetail findById(Integer integer) {
        return cartDao.findById(integer);
    }

    @Override
    public CartDetail findByName(CartDetail cartDetail) {
        return cartDao.findByName(cartDetail);
    }

    @Override
    public boolean delete(Integer integer) {
        return cartDao.delete(integer);
    }
}
