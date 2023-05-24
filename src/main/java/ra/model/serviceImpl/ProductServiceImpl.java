package ra.model.serviceImpl;

import ra.model.dao.IProductDao;
import ra.model.daoImpl.ProductDaoImpl;
import ra.model.entity.Product;
import ra.model.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {

private IProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(product);
    }

    @Override
    public Product findById(Integer integer) {
        return productDao.findById(integer);
    }

    @Override
    public Product findByName(Product product) {
        return productDao.findByName(product);
    }

    @Override
    public boolean delete(Integer integer) {
        return productDao.delete(integer);
    }

    @Override
    public boolean changeStatusProduct(int idChange) {
        return productDao.changeStatusProduct(idChange);
    }
}
