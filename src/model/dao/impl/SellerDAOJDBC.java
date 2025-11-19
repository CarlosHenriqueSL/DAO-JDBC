package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDAOJDBC implements SellerDAO {

    Connection connection;

    public SellerDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Seller seller) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement sqlStatement = null;
        ResultSet result = null; 

        try {
            sqlStatement = connection.prepareStatement("SELECT seller.*, department.name as DepName "
                                                            + "FROM seller INNER JOIN department "
                                                            + "ON seller.departmentid = department.id "
                                                            + "WHERE seller.id = ?");

            sqlStatement.setInt(1, id);

            result = sqlStatement.executeQuery();

            if (result.next()) {
                Department department = new Department();
                department.setId(result.getInt("departmentid"));
                department.setName(result.getString("depname"));

                Seller seller = new Seller();
                seller.setId(result.getInt("id"));
                seller.setName(result.getString("name"));
                seller.setEmail(result.getString("email"));
                seller.setBirthDate(result.getDate("birthdate").toLocalDate());
                seller.setBaseSalary(result.getDouble("basesalary"));
                seller.setDepartment(department);

                return seller;
            }

            return null;
        }
        catch (SQLException e)
        {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(sqlStatement);
            DB.closeResultSet(result);
        }

    }

    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
