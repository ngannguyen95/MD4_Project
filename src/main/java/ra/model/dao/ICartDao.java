package ra.model.dao;

import ra.model.entity.BillDetail;
import ra.model.entity.Cart;
import ra.model.entity.CartDetail;

import java.util.List;

public interface ICartDao extends IDao<CartDetail, Integer> {
    List<CartDetail> findCartByCartIdUserLogin(Integer userId);


    CartDetail checkExitProduct(Integer proId, Integer cartId);

    int updateCartByCartId(Cart cart);

    List<Cart> findBillByUserId(Integer userId);

    List<CartDetail> findCartDetailByCartId(Integer cartId);
    Cart findCartById(Integer cId);
}
