package ra.model.service;

import ra.model.entity.Cart;
import ra.model.entity.CartDetail;

import java.util.List;

public interface ICartService extends IGenericService<CartDetail,Integer> {
    List<CartDetail> findCartByCartIdUserLogin(Integer cartId);
    CartDetail checkExitProduct(Integer proId,Integer cartId);
    int updateCartByCartId(Cart cart);
    List<Cart> findBillByUserId(Integer userId);
    List<CartDetail> findCartDetailByCartId(Integer cartId);
    Cart findCartById(Integer cId);


}
