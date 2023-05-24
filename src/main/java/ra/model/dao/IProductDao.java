package ra.model.dao;

import ra.model.entity.Product;

public interface IProductDao extends IDao<Product,Integer> {
    boolean changeStatusProduct(int idChange);
}
