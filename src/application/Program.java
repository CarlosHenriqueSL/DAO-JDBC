package application;


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

        System.out.println("\n=== TEST 1: seller findById =====");
        List<Seller> sellers = sellerDAO.findByDepartment(new Department(2, null));
        for (Seller obj : sellers){
            System.out.println(obj);
        }
    }
}
