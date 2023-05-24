package ra.model.serviceImpl;

import ra.model.dao.ICatalogDao;
import ra.model.daoImpl.CatologDaoImpl;
import ra.model.entity.Catalog;
import ra.model.service.ICatalogService;

import java.util.List;

public class CatalogServiceImpl implements ICatalogService {
    private ICatalogDao catalogDao = new CatologDaoImpl();

    @Override
    public boolean changeStatusCatalog(int idChange) {
        return catalogDao.changeStatusCatalog(idChange);
    }

    @Override
    public List<Catalog> findAll() {
        return catalogDao.findAll();
    }

    @Override
    public boolean save(Catalog catalog) {
        return catalogDao.save(catalog);
    }

    @Override
    public boolean update(Catalog catalog) {
        return catalogDao.update(catalog);
    }

    @Override
    public Catalog findById(Integer integer) {
        return catalogDao.findById(integer);
    }

    @Override
    public Catalog findByName(Catalog catalog) {
        return catalogDao.findByName(catalog);
    }

    @Override
    public boolean delete(Integer integer) {
        return catalogDao.delete(integer);
    }
}
