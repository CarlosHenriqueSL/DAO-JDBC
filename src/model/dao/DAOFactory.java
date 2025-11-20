package model.dao;

import db.DB;
import model.dao.impl.SellerDAOJDBC;
import model.dao.impl.DepartmentDAOJDBC;

public class DAOFactory {

    public static SellerDAO createSellerDAO() {
        return new SellerDAOJDBC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDAO() {
        return new DepartmentDAOJDBC(DB.getConnection());
    }
}
