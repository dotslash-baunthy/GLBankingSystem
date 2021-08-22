package com.greatlearning.services;

import com.greatlearning.models.Customer;
import com.greatlearning.services.BankingService;

import java.util.Scanner;

public class BankingService {

	Scanner scn = new Scanner(System.in);

	public void logOut() {
		System.out.println("----------Thank you for banking with us----------");
	}

	public void deposit(Customer customer) {
		int amount;
		System.out.print("Enter the amount to be deposited: ");
		amount = scn.nextInt();
		if (amount > 0) {
			customer.setBalance(customer.getBalance() + amount);
			System.out.println("Amount deposited successfully!");
			System.out.println("Remaining balance is: " + customer.getBalance());
		} else {
			System.out.println("Please provide a valid input!");
		}
	}

	public void withdraw(Customer customer) {
		int amount;
		System.out.print("Enter the amount to be withdrawn: ");
		amount = scn.nextInt();
		if (customer.getBalance() >= amount) {
			System.out.println("Amount withdrawn successfully!");
			customer.setBalance(customer.getBalance() - amount);
			System.out.println("Remaining balance is: " + customer.getBalance());
		} else {
			System.out.println("Please provide a valid input!");
		}
	}

	public void transfer(Customer loggedIncustomer, Customer otherCustomer) {
		int amount;
		int otp;
		int otpGenerated;
		int transferAccountNumber;
		System.out.print("Enter the OTP: ");
		OTPService otpService = new OTPService();
		otpGenerated = otpService.generateOTP();
		System.out.println("Generated OTP is: " + otpGenerated);
		System.out.print("Enter the OTP: ");
		otp = scn.nextInt();
		if (otp == otpGenerated) {
			System.out.println("OTP validated successfully!");
			System.out.print("Enter the amount to be transferred: ");
			amount = scn.nextInt();
			System.out.print("Enter the account no. to be transferred to: ");
			transferAccountNumber = scn.nextInt();

			// Check if the account number to be tranferred to exists in our database (from
			// the two customers added)
			if (transferAccountNumber == otherCustomer.getBankAccountNo()) {
				if (loggedIncustomer.getBalance() >= amount) {
					otherCustomer.setBalance(otherCustomer.getBalance() + amount);
					loggedIncustomer.setBalance(loggedIncustomer.getBalance() - amount);
					System.out.println("Amount was transferred!");
					System.out.println("Remaining balance is: " + loggedIncustomer.getBalance());
				} else {
					System.out.println("Insufficient balance!");
				}
			} else {
				System.out.println("The recipient does not exist in our database");
			}
		}
	}

	public void checkBalance(Customer loggedInCustomer) {
		System.out.println("Customer " + loggedInCustomer.getBankAccountNo() + " has a balance of "
				+ loggedInCustomer.getBalance());
	}
}
