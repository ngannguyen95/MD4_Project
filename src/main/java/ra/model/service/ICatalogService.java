package ra.model.service;

import ra.model.entity.Catalog;

public interface ICatalogService extends IGenericService<Catalog,Integer> {
    boolean changeStatusCatalog(int idChange);
}
