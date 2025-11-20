package application;


import java.time.LocalDate;
import java.util.List;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args){

        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDAO.findById(1);
        System.out.println(seller);

        System.out.println("\n=== TEST 2: seller findByDepartment =====");
        Department department = new Department(2, null);
        List<Seller> sellers = sellerDAO.findByDepartment(department);
        for (Seller obj : sellers){
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 3: seller findAll =====");
        sellers = sellerDAO.findAll();
        for (Seller obj : sellers){
            System.out.println(obj);
        }

        System.out.println("\n=== TEST 4: seller insert =====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", LocalDate.now(), 4000.00, department);
        sellerDAO.insert(newSeller);
        System.out.println("Inserted! New id: " + newSeller.getId());
    }
}
