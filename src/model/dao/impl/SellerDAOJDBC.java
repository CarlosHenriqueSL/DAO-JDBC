package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        PreparedStatement sqlStatement = null;

        try {
            sqlStatement = connection.prepareStatement(
                                                    """
                                                    INSERT INTO seller
                                                        (Name, Email, BirthDate, BaseSalary, DepartmentId)
                                                        VALUES
                                                        (?, ?, ?, ?, ?)
                                                    """, Statement.RETURN_GENERATED_KEYS);

            sqlStatement.setString(1, seller.getName());
            sqlStatement.setString(2, seller.getEmail());
            sqlStatement.setDate(3, java.sql.Date.valueOf(seller.getBirthDate()));
            sqlStatement.setDouble(4, seller.getBaseSalary());
            sqlStatement.setInt(5, seller.getDepartment().getId());

            int rowsAffected = sqlStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet result = sqlStatement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    seller.setId(id);
                }

                DB.closeResultSet(result);
            }
            else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(sqlStatement);
        }

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
                Department department = instantiateDepartment(result);
                Seller seller = instantiateSeller(result, department);
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

    private Seller instantiateSeller(ResultSet result, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(result.getInt("id"));
        seller.setName(result.getString("name"));
        seller.setEmail(result.getString("email"));
        seller.setBirthDate(result.getDate("birthdate").toLocalDate());
        seller.setBaseSalary(result.getDouble("basesalary"));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartment(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("departmentid"));
        department.setName(result.getString("depname"));
        return department;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement sqlStatement = null;
        ResultSet result = null; 

        try {
            sqlStatement = connection.prepareStatement("SELECT seller.*,department.name as DepName "
                                                        + "FROM seller INNER JOIN department "
                                                        + "ON seller.DepartmentId = department.Id "
                                                        + "ORDER BY Name");

            result = sqlStatement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (result.next()) {
                Department dep = departmentMap.get(result.getInt("departmentid"));

                if (dep == null) {
                    dep = instantiateDepartment(result);
                    departmentMap.put(result.getInt("departmentid"), dep);
                }

                Seller seller = instantiateSeller(result, dep);
                sellers.add(seller);
            }

            return sellers;
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
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement sqlStatement = null;
        ResultSet result = null; 

        try {
            sqlStatement = connection.prepareStatement("SELECT seller.*,department.name as DepName "
                                                        + "FROM seller INNER JOIN department "
                                                        + "ON seller.DepartmentId = department.Id "
                                                        + "WHERE DepartmentId = ? "
                                                        + "ORDER BY Name");

            sqlStatement.setInt(1, department.getId());

            result = sqlStatement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (result.next()) {
                Department dep = departmentMap.get(result.getInt("departmentid"));

                if (dep == null) {
                    dep = instantiateDepartment(result);
                    departmentMap.put(result.getInt("departmentid"), dep);
                }

                Seller seller = instantiateSeller(result, dep);
                sellers.add(seller);
            }

            return sellers;
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

}
