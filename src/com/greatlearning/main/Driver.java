package com.greatlearning.main;

import com.greatlearning.models.Customer;
import com.greatlearning.services.BankingService;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		BankingService bankingService = new BankingService();

		Customer customer1 = new Customer("password", 123456, 1000);

		Scanner scn = new Scanner(System.in);
		String password;
		int bankAccountNo;

		System.out.println("----------Welcome to the login page----------");
		System.out.println();
		System.out.print("Enter your account number: ");
		bankAccountNo = scn.nextInt();
		System.out.print("Enter the password: ");
		password = scn.next();

		Customer loggedInCustomer = new Customer(password, bankAccountNo, 1000);

		if (validCustomer(loggedInCustomer, customer1)) {
			int option = 0;
			do {
				System.out.println("--------------------");
				System.out.println("1. Deposit");
				System.out.println("2. Withdrawal");
				System.out.println("3. Transfer");
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
						bankingService.transfer(loggedInCustomer);
						break;
					default:
						System.out.println("Please enter a valid option from the list provided");
				}
			} while (option != 0);
		} else {
			System.out.println("Please enter valid credentials!");
		}
	}

	private static boolean validCustomer(Customer loggedInCustomer, Customer customer1) {
		return loggedInCustomer.getBankAccountNo() == customer1.getBankAccountNo()
				&& loggedInCustomer.getPassword().equals(customer1.getPassword());
	}
}
