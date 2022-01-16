package com.bharadwaj.addressbookapplication.service;

import com.bharadwaj.addressbookapplication.exception.ContactNotFoundException;
import com.bharadwaj.addressbookapplication.exception.DuplicateContactException;
import com.bharadwaj.addressbookapplication.pojo.AddressBook;
import com.bharadwaj.addressbookapplication.pojo.Contact;
import com.google.gson.Gson;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AddressBookService {

    Scanner scanner = new Scanner(System.in);


    public void deleteContact(AddressBook addressBook) throws ContactNotFoundException{
        System.out.println("Enter FirstName and LastName of the contact to search");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        Contact contact = findContact(addressBook,firstName,lastName);
        addressBook.getContacts().remove(contact);
    }

    public void displayContact(AddressBook addressBook) throws ContactNotFoundException{
        System.out.println("Enter FirstName and LastName of the contact to search");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        Contact contact = findContact(addressBook,firstName,lastName);
        System.out.println(contact);
    }

    public void editContact(AddressBook addressBook) throws ContactNotFoundException{
        System.out.println("Enter FirstName and LastName of the contact to search");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        Contact contact = findContact(addressBook,firstName,lastName);
        getContactAndUpdate(contact);
    }

    public void addContact(AddressBook addressBook) throws DuplicateContactException{
        Contact contact = getContactAndUpdate(new Contact());
        if(addressBook.getContacts().contains(contact)) {
            throw new DuplicateContactException("Contact already exists");
        }
        addressBook.getContacts().add(contact);
    }

    private Contact findContact(AddressBook addressBook, String firstName, String lastName) throws ContactNotFoundException {
        return addressBook.getContacts().stream().filter(contact -> contact.getFirstName().equalsIgnoreCase(firstName) &&
                contact.getLastName().equalsIgnoreCase(lastName)).findFirst().orElseThrow(()
                -> new ContactNotFoundException("No contact Found with firstName:"+firstName+" " +
                "and LastName:"+lastName ));
    }

    private Contact getContactAndUpdate(Contact contact) {
        System.out.print("Enter First Name and Last Name\n");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        System.out.println("Enter Address, City, State, Zip");
        String address = scanner.nextLine();
        String city = scanner.nextLine();
        String state = scanner.nextLine();
        String zip = scanner.nextLine();
        System.out.println("Enter email and phoneNo");
        String email = scanner.nextLine();
        String phoneNo = scanner.nextLine();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAddress(address);
        contact.setCity(city);
        contact.setState(state);
        contact.setZip(zip);
        contact.setEmail(email);
        contact.setPhoneNo(phoneNo);

        return contact;
    }

    public void writeToCSV(AddressBook addressBook,String path) throws IOException {
        FileWriter outputfile = new FileWriter(path);
        CSVWriter csvWriter = new CSVWriter(outputfile);
        String[] header = { "FirstName", "LastName", "Address","City","State","Zipcode","Email","PhoneNo"};
        csvWriter.writeNext(header);

        addressBook.getContacts().forEach(contact -> {
            String[] data = {contact.getFirstName(),contact.getLastName(),contact.getAddress(),contact.getCity(),contact.getState(),
            contact.getZip(),contact.getEmail(),contact.getPhoneNo()};
            csvWriter.writeNext(data);
        });
        outputfile.flush();
        outputfile.close();
        csvWriter.close();
    }

    public void writeToJSON(AddressBook addressBook, String path) throws IOException{
        FileWriter writer = new FileWriter(path);
        Gson gson = new Gson();
        gson.toJson(addressBook.getContacts(),writer);
        writer.flush();
        writer.close();
    }
}
