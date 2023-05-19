package ra.service;

import ra.model.entity.Catalog;
import ra.service.IGenericService;

public interface ICatalogService extends IGenericService<Catalog,Integer> {
    boolean changeStatusCatalog(int idChange);
}
