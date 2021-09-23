package com.bridgelabz.controller;

import com.bridgelabz.services.ImpAddressBook;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args){
        System.out.println("Welcome to Address Book Program");
        ImpAddressBook util = new ImpAddressBook();
        Scanner scanner = new Scanner(System.in);

        boolean isExitAddressBook = false;
        System.out.println("Address book manager\n");
        while (!isExitAddressBook) {

            System.out.println("Select menu");
            System.out.println("1. New Address Book\n2. Quit");
            System.out.println("Enter option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    util.createNewAddressBook();
                    break;
                case 2:
                    System.out.println("-----------------------Quit Address Book-----------------------");
                    isExitAddressBook = true;
                    System.out.println("Thank you for your time");
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        scanner.close();
    }
}
