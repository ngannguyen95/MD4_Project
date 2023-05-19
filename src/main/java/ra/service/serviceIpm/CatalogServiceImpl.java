package ra.service.serviceIpm;

import ra.model.entity.Catalog;
import ra.model.util.ConnectionDB;
import ra.service.ICatalogService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceImpl implements ICatalogService {
    @Override
    public List<Catalog> findAll() {
        Connection conn = null;
        List<Catalog> catalogList = new ArrayList<>();
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call getListCatalog()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setCataId(rs.getInt("cataId"));
                catalog.setCataName(rs.getString("cataName"));
                catalog.setCataStatus(rs.getBoolean("cataStatus"));
                catalogList.add(catalog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return catalogList;
    }

    @Override
    public boolean save(Catalog catalog) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  createCatalog(?)}");
            call.setString(1, catalog.getCataName());
            int check = call.executeUpdate();
            if (check == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return false;
    }

    @Override
    public boolean update(Catalog catalog) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  updateCatalog(?,?,?)}");
            call.setInt(1, catalog.getCataId());
            call.setString(2, catalog.getCataName());
            call.setBoolean(3, catalog.isCataStatus());
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return true;
    }

    @Override
    public Catalog findById(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        Catalog catalog1 = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call findCatalogById(?)}");
            call.setInt(1, integer);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                catalog1  = new Catalog();
                catalog1.setCataId(rs.getInt(1));
                catalog1.setCataName(rs.getString(2));
                catalog1.setCataStatus(rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return catalog1;
    }

    @Override
    public Catalog findByName(Catalog catalog) {
        Connection conn = null;
        CallableStatement call = null;
        Catalog catalog1 = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  findCatalogByName(?)}");
            call.setString(1, catalog.getCataName());
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                catalog1  = new Catalog();
                catalog1.setCataId(rs.getInt(1));
                catalog1.setCataName(rs.getString(2));
                catalog1.setCataStatus(rs.getBoolean(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return catalog1;
    }

    @Override
    public boolean delete(Integer integer) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call  deleteCatalog(?)}");
            call.setInt(1, integer);
            call.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, call);
        }
        return true;
    }
    @Override
    public boolean changeStatusCatalog(int idChange) {
        Connection conn = null;
        CallableStatement call = null;
        try {
            conn = ConnectionDB.openConnection();
            call = conn.prepareCall("{call changeStatusCatalog(?)}");
            call.setInt(1, idChange);
            call.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
