package ra.model.dao;

import ra.model.entity.Catalog;

public interface ICatalogDao extends IDao<Catalog,Integer>{
    boolean changeStatusCatalog(int idChange);

}
