package com.bharadwaj.addressbookapplication;

import com.bharadwaj.addressbookapplication.exception.ContactNotFoundException;
import com.bharadwaj.addressbookapplication.exception.DuplicateContactException;
import com.bharadwaj.addressbookapplication.pojo.AddressBook;
import com.bharadwaj.addressbookapplication.pojo.Contact;
import com.bharadwaj.addressbookapplication.service.AddressBookService;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Application {


    AddressBookService service = new AddressBookService();
    AddressBook addressBook = new AddressBook("AddressBook1",new ArrayList<Contact>());

    public static void main(String[] args){
        Application app = new Application();
        app.displayMenu();


    }

    public  void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n#############################");
        System.out.println("Please select an action");
        System.out.println("1. Add a person");
        System.out.println("2. Edit");
        System.out.println("3. Delete a person");
        System.out.println("4. Search a person");
        System.out.println("5. Write to CSV");
        System.out.println("6. Write to JSON");
        System.out.println("7. Quit");
        System.out.print(">>");
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                try {
                    service.addContact(addressBook);
                }catch (DuplicateContactException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 2:
                try {
                    service.editContact(addressBook);
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                displayMenu();
                break;
            case 3:
                try {
                    service.deleteContact(addressBook);
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 4:
                try {
                    service.displayContact(addressBook);
                }catch (ContactNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 5:
                try {
                    service.writeToCSV(addressBook, "C:\\Users\\1028837\\Desktop\\test.csv");
                }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            case 6:
                try {
                    service.writeToJSON(addressBook, "C:\\Users\\1028837\\Desktop\\test.json");
                }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                displayMenu();
                break;
            default:
                break;
        }
        scanner.close();
    }




}
