/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bankaccount;

public class Customer {

    private Address address;
    private Name name;
    private final String accountNumber;
    
    public Customer(Name n, Address a,  String aNumber){
        address = a;
        name = n;
        accountNumber = aNumber;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public Address getAdresss(){
        return address;
    }
    public Name getName(){
        return name;
    }
    public void changeAddress(Address newAddress){
        address = newAddress;
    }
    public void changeName(Name newName){
        name = newName;
    }
    @Override
    public String toString(){
        return "Name: " + name.toString()+"\n"+address.toString();
    }
    
    public static class Address{
        private String houseNumber;
        private String street;
        private String zip;
        private String city;
        private String state;
        
        public Address(String houseNumber, String street, String zip, String city, String state){
            this.houseNumber = houseNumber;
            this.street = street;
            this.zip = zip;
            this.city = city;
            this.state = state;
        }
        public String getHouseNumber(){return houseNumber;}
        public String getStreet(){return street;}
        public String getZip(){return zip;}
        public String getCity(){return city;}
        public String getState(){return state;}
        
        public void changeAddress(String houseNumber, String street, String zip, String city, String state){
            this.houseNumber = houseNumber;
            this.street = street;
            this.zip = zip;
            this.city = city;
            this.state = state;
        }
        @Override
        public String toString(){
            return (houseNumber
                    +"\n"+street
                    +"\n"+zip
                    +"\n"+city+", "+state);
        }
    }
    public static class Name{
        private String lastName;
        private String firstName;
        public Name(String first, String last){
            lastName = last;
            firstName = first;
        }
        public String getFirstName(){
            return firstName;
        }
        public String getLastName(){
            return lastName;
        }
        public void changeName(String first, String last){
            firstName = first;
            lastName = last;
        }
        @Override
        public String toString(){
            return firstName +" "+ lastName;
        }
    }
    
}