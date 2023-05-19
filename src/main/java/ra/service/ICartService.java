package ra.service;

import ra.model.entity.Cart;
import ra.model.entity.CartDetail;
import ra.service.IGenericService;

import java.util.List;

public interface ICartService extends IGenericService<CartDetail,Integer> {
    List<CartDetail> findCartByCartIdUserLogin(Integer cartId);
    CartDetail checkExitProduct(Integer proId,Integer cartId);
    boolean updateCartByCartId(Cart cart);

}
