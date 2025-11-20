package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Department;

public class DepartmentDAOJDBC implements DepartmentDAO {

     Connection connection;

    public DepartmentDAOJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement sqlStatement = null;

        try {
            sqlStatement = connection.prepareStatement("""
                                                        INSERT INTO department
                                                        (name)
                                                        VALUES
                                                        (?)
                                                        """, Statement.RETURN_GENERATED_KEYS);

            sqlStatement.setString(1, department.getName());

            int rowsAffected = sqlStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet result = sqlStatement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    department.setId(id);
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
    public void update(Department department) {
        PreparedStatement sqlStatement = null;

        try {
            sqlStatement = connection.prepareStatement("""
                                                        UPDATE department
                                                        SET name = ?
                                                        WHERE id = ?
                                                        """, Statement.RETURN_GENERATED_KEYS);

            sqlStatement.setString(1, department.getName());
            sqlStatement.setInt(2, department.getId());

            sqlStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(sqlStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement sqlStatement = null;

        try {
            sqlStatement = connection.prepareStatement("DELETE FROM department WHERE id = ?");
            sqlStatement.setInt(1, id);

            int rowsAffected = sqlStatement.executeUpdate();

            if (rowsAffected == 0){
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
    public Department findById(Integer id) {
        PreparedStatement sqlStatement = null;
        ResultSet result = null; 

        try {
            sqlStatement = connection.prepareStatement("SELECT * FROM department WHERE id = ?");

            sqlStatement.setInt(1, id);

            result = sqlStatement.executeQuery();

            if (result.next()) {
                Department department = instantiateDepartment(result);
                return department;
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

    private Department instantiateDepartment(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("id"));
        department.setName(result.getString("name"));
        return department;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement sqlStatement = null;
        ResultSet result = null; 

        try {
            sqlStatement = connection.prepareStatement("SELECT * FROM department ORDER BY Name");

            result = sqlStatement.executeQuery();

            List<Department> departments = new ArrayList<>();

            while (result.next()) {
                Department department = instantiateDepartment(result);

                departments.add(department);
            }

            return departments;
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
