package com.example;

import com.example.dao.MemberDAO;
import com.example.model.MemberEntity;
import com.example.model.RoleEntity;
import com.example.service.MemberService;
import com.example.service.MemberServiceImpl;
import com.example.service.RoleService;
import com.example.service.RoleServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {public static void main(String[] args) {
    RoleService roleService = new RoleServiceImpl("JPA-NAME");
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println("\n=== Role Management System ===");
        System.out.println("1. Add Role");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");

        choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                System.out.print("Enter role name: ");
                String roleName = scanner.nextLine();

                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setName(roleName);

                roleService.addRole(roleEntity);
                System.out.println("Role added successfully!");
                break;

            case 2:
                System.out.println("Exiting...");
                break;

            default:
                System.out.println("Invalid choice!");
        }
    } while (choice != 2);

    scanner.close();
}

}
