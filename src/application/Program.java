package application;

import java.time.LocalDate;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args){
        Department department = new Department(1, "books");

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", LocalDate.now(), 3000.00, department);

        SellerDAO sellerDAO = DAOFactory.createSellerDAO();
        
        System.out.println(seller);
    }
}
