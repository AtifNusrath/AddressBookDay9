package com.bridgelabz.services;

import com.bridgelabz.model.Address;
import com.bridgelabz.model.Person;

import java.util.ArrayList;
import java.util.Scanner;

public class ImpAddressBook implements IAddressBook {
        Scanner scanner = new Scanner(System.in);

    static ArrayList<Person> persons = new ArrayList<Person>();

    int counter = 0;
        String statename = "";

        public static void PrintPersonDetails(ArrayList<Person> persons, String statename) {
            String str = "";
            str += "Person detail\n";
		for (int i = 0; i < persons.size(); i++) {
			if (!statename.isEmpty() && statename.equals(persons.get(i).getAddressObj().getState())) {
				str += persons.get(i).getFirstname() + " ";
				str += persons.get(i).getLastname() + " ";
				str += persons.get(i).getAddressObj().getAddressLocal() + " ";
				str += persons.get(i).getAddressObj().getCity() + " ";
				str += persons.get(i).getAddressObj().getState() + " ";
				str += persons.get(i).getAddressObj().getZip() + " ";
				str += persons.get(i).getMobile() + " \n";
			}

		}
            System.out.println(str);
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
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getAddressObj().getState().equals(statename)) {
                isFoundState = true;
                break;
            }
        }
        if (!isFoundState) {

            System.out.println("->State is added<-");
            boolean close = false;

            while (!close) {
                System.out.println("Select option: \n1.add\n2.print\n3.close");
                int ch = scanner.nextInt();
                switch (ch) {
                    case 1:
                        addPerson();
                        break;

                    case 2:
                        if (counter > 0) {
                            System.out.println("Printing all records...");

                            ImpAddressBook.PrintPersonDetails(persons, statename);

                        } else
                            System.out.println("There is no record to print...");
                        break;
                    case 3:
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
        // validating mobile is not taken by anyone
        boolean isMobileTaken = false;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getMobile() == mobile) {
                isMobileTaken = true;
                break;
            }
        }
        if (isMobileTaken) {
            System.out.println("This mobile number is already taken");
        } else {
            person.setMobile(mobile);
            System.out.println("Enter person first name: ");
            person.setFirstname(scanner.next().toLowerCase());
            System.out.println("Enter person last name: ");
            person.setLastname(scanner.next().toLowerCase());
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
}
