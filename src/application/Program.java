package application;

import java.time.LocalDate;

import model.entities.Department;
import model.entities.Seller;

public class Program {
    public static void main(String[] args){
        Department department = new Department(1, "books");

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", LocalDate.now(), 3000.00, department);
        System.out.println(seller);
    }
}
