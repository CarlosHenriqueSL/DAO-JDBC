package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.dao.DAOFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

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

        System.out.println("\n=== TEST 5: seller update =====");
        seller = sellerDAO.findById(1);
        seller.setName("Martha Waine");
        sellerDAO.update(seller);
        System.out.println("Update completed!");

        System.out.println("\n=== TEST 6: seller delete =====");
        System.out.println("Enter id for delete test: ");
        int id = input.nextInt();
        sellerDAO.deleteById(id);
        System.out.println("Delete completed");

        input.close();
    }
}
