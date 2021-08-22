package com.greatlearning.main;

import com.greatlearning.models.Customer;
import com.greatlearning.services.BankingService;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		BankingService bankingService = new BankingService();

		Customer customer1 = new Customer("password", 123456);
		Customer customer2 = new Customer("PaSsWoRd", 12345);

		Scanner scn = new Scanner(System.in);
		String password;
		int bankAccountNo;

		System.out.println("----------Welcome to the login page----------");
		System.out.println();
		System.out.print("Enter your account number: ");
		bankAccountNo = scn.nextInt();
		System.out.print("Enter the password: ");
		password = scn.next();

		Customer loggedInCustomer = new Customer(password, bankAccountNo);
		Customer otherCustomer = new Customer();

		if (validCustomer(loggedInCustomer, customer1, customer2)
				|| validCustomer(loggedInCustomer, customer2, customer2)) {
			if (bankAccountNo == 123456) {
				otherCustomer = customer2;
			} else {
				otherCustomer = customer1;
			}
			int option = 0;
			do {
				System.out.println("--------------------");
				System.out.println("1. Deposit");
				System.out.println("2. Withdrawal");
				System.out.println("3. Transfer");
				System.out.println("4. Check balance");
				System.out.println("0. Logout");
				System.out.println("--------------------");
				System.out.print("What do you want to do? ");
				option = scn.nextInt();
				switch (option) {
					case 0:
						option = 0;
						bankingService.logOut();
						scn.close();
						break;
					case 1:
						bankingService.deposit(loggedInCustomer);
						break;
					case 2:
						bankingService.withdraw(loggedInCustomer);
						break;
					case 3:
						bankingService.transfer(loggedInCustomer, otherCustomer);
						break;
					case 4:
						bankingService.checkBalance(loggedInCustomer);
						break;
					default:
						System.out.println("Please enter a valid option from the list provided");
				}
			} while (option != 0);
		} else {
			System.out.println("Please enter valid credentials!");
		}
	}

	private static boolean validCustomer(Customer loggedInCustomer, Customer customer1, Customer customer2) {
		if (loggedInCustomer.getBankAccountNo() == customer1.getBankAccountNo()
				&& loggedInCustomer.getPassword().equals(customer1.getPassword())
				|| (loggedInCustomer.getBankAccountNo() == customer2.getBankAccountNo()
						&& loggedInCustomer.getPassword().equals(customer2.getPassword()))) {
			return true;
		} else {
			return false;
		}
	}
}
