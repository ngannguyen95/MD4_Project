package ra.service;

import ra.model.entity.Product;

public interface IProductService extends IGenericService<Product,Integer> {
boolean changeStatusProduct(int idChange);
}
