package com.bharadwaj.addressbookapplication.pojo;

import java.util.List;

public class AddressBook {
    private String addressBookName;
    private List<Contact> contacts;

    public AddressBook(String addressBookName, List<Contact> contacts) {
        this.addressBookName = addressBookName;
        this.contacts = contacts;
    }

    public String getAddressBookName() {
        return addressBookName;
    }

    public void setAddressBookName(String addressBookName) {
        this.addressBookName = addressBookName;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
