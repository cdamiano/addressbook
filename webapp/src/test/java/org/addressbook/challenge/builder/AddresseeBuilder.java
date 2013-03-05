package org.addressbook.challenge.builder;

import org.addressbook.challenge.domain.Addressee;

public class AddresseeBuilder {
    
    private String name;
    private String phone;
    
    
    public static AddresseeBuilder anAddressee() {
        return new AddresseeBuilder();
    }
    
    public AddresseeBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public AddresseeBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }
    
    public Addressee build() {
        Addressee addressee = new Addressee();
        addressee.setName(name);
        addressee.setPhone(phone);
        return addressee;
    }
    

}
