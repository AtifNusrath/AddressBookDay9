package com.bridgelabz.services;

import com.bridgelabz.model.Address;
import com.bridgelabz.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ImpAddressBook implements IAddressBook {
        Scanner scanner = new Scanner(System.in);

    static ArrayList<Person> persons = new ArrayList<>();
    int indexOfPerson;
    int counter = 0;
        String statename = "";

        public static void PrintPersonDetails(ArrayList<Person> persons, String statename) {
            System.out.println("Person detail\n");
            for (Person i : persons) {
                if (!statename.isEmpty() && statename.equals(i.getAddressObj().getState())) {
                    System.out.println(i.getFirstname() + " " + i.getLastname() + " " + i.getAddressObj().getAddressLocal()
                            + " " + i.getAddressObj().getCity() + " " + i.getAddressObj().getState() + " "
                            + i.getAddressObj().getZip() + " " + i.getMobile());
                }

            }
    }


    @Override
    public void createNewAddressBook() {
        System.out.println("-----------------------New Address Book-----------------------");
        System.out.println("Enter state name: ");
        statename = scanner.next();

        boolean isFoundState = false;
        for (Person person : persons) {
            if (person.getAddressObj().getState().equals(statename)) {
                isFoundState = true;
                break;
            }
        }

        if (!isFoundState) {
            System.out.println("->State is added<-");
            boolean close = false;

            while (!close) {
                System.out.println("Select option: \n1.add\n2.Edit\n3.print\n4.Delete\n5.close");
                int ch = scanner.nextInt();
                switch (ch) {
                    case 1:
                        addPerson();
                        break;
                    case 2:

                        editPerson();
                        break;
                    case 3:
                        if (counter > 0) {
                            System.out.println("Printing all records...");

                            ImpAddressBook.PrintPersonDetails(persons, statename);

                        } else
                            System.out.println("There is no record to print...");
                        break;
                    case 4:
                        deletePerson();
                        break;
                    case 5:
                        close = true;
                        System.out.println("Closing...");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        } else
            System.out.println("State exist please try again");

        System.out.println("-----------------------New Address Book-----------------------");

    }


    @Override
    public void addPerson() {
        System.out.println("Add person details...");
        Person person = new Person();

        System.out.println("Enter mobile");
        Long mobile = scanner.nextLong();

        boolean isMobileTaken = false;
        for (Person value : persons) {
            if (Objects.equals(value.getMobile(), mobile)) {
                isMobileTaken = true;
                break;
            }
        }
        if (isMobileTaken) {
            System.out.println("This mobile number is already taken");
        } else {
            person.setMobile(mobile);
            System.out.println("Enter person first name: ");
            person.setFirstname(scanner.next());
            System.out.println("Enter person last name: ");
            person.setLastname(scanner.next());
            System.out.println("Enter address Details: ");
            Address address = new Address();
            System.out.println("Enter address: ");
            address.setAddressLocal(scanner.next());
            System.out.println("Enter city: ");
            address.setCity(scanner.next());
            address.setState(statename);
            System.out.println("Enter zip: ");
            address.setZip(scanner.nextInt());

            person.setAddressObj(address);

            persons.add(person);

            System.out.println();
            System.out.println("Person added");
            counter++;

        }
    }
    @Override
    public void editPerson() {
        if (counter > 0) {
            System.out.println("Enter Persons FirstName you want to edit: ");
            String searchName = scanner.next();
            indexOfPerson = 0;
            boolean isFoundPerson = false;

                for  (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getFirstname().equals(searchName)) {
                    isFoundPerson = true;
                    indexOfPerson = i;
                    break;
                }
            }
            if (isFoundPerson) {

                System.out.print("\nEnter new address: ");
                persons.get(indexOfPerson).getAddressObj().setAddressLocal(scanner.next());
                System.out.println("Enter new city searchName: ");
                persons.get(indexOfPerson).getAddressObj().setCity(scanner.next());
                System.out.println("Enter new zip: ");
                persons.get(indexOfPerson).getAddressObj().setZip(scanner.nextInt());
                System.out.println("Enter mobile number: ");
                persons.get(indexOfPerson).setMobile(scanner.nextLong());
                persons.get(indexOfPerson).setFirstname(searchName);
                System.out.println("Enter Last Name: ");
                persons.get(indexOfPerson).setLastname(scanner.next());

                System.out.println();
                System.out.println("Edit completed");

            } else
                System.out.println("No person found with this searchName");
        } else
            System.out.println("There is no record to edit");

    }

    @Override
    public void deletePerson() {
        if (counter > 0) {
            System.out.println("Enter Persons FirstName you want to delete: ");
            String searchName = scanner.next();
            indexOfPerson = 0;
            boolean isFoundPerson = false;

            for  (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getFirstname().equals(searchName)) {
                    isFoundPerson = true;
                    indexOfPerson = i;
                    break;
                }
            }
            if (isFoundPerson) {
                persons.remove(indexOfPerson);
                counter--;
                System.out.println();
                System.out.println("Delete completed");
            } else
                System.out.println("No person found with this number");
        } else
            System.out.println("No records to delete");
    }
    @Override
    public void openAddressBook() {
        System.out.println("-----------------------Open Address Book-----------------------");
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < persons.size(); i++) {
            map.put(persons.get(i).getAddressObj().getState(), persons.get(i).getAddressObj().getState());
        }
        System.out.println("states available " + map.keySet());
        System.out.println("Enter state");
        statename = scanner.next();
        boolean isFoundState = false;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getAddressObj().getState().equals(statename)) {
                isFoundState = true;
                break;
            }
        }
        if (isFoundState) {
            System.out.println("->State is found<-");
            boolean close2 = false;

            while (!close2) {
                System.out.println("Select option: \n1.add\n2.edit\n3.delete\n4.print\n5.quit");
                switch (scanner.nextInt()) {
                    case 1:
                        // add person
                        addPerson();
                        break;
                    case 2:
                        // edit person
                        editPerson();
                        break;
                    case 3:
                        // delete person
                        deletePerson();
                        break;

                    case 4:
                        // print
                        if (counter > 0) {
                            System.out.println("Printing all records...");

                            ImpAddressBook.PrintPersonDetails(persons, statename);

                        } else
                            System.out.println("There is no record to print...");

                        break;
                    case 5:
                        close2 = true;
                        System.out.println("Closing...");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }

        } else
            System.out.println("Please create new state of that name\nelse try new state name");

        System.out.println("-----------------------Open Address Book-----------------------");

    }
}
