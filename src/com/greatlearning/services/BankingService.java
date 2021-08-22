package com.greatlearning.services;

import com.greatlearning.models.Customer;
import com.greatlearning.services.BankingService;

import java.util.Scanner;

public class BankingService {
	
	Scanner sc = new Scanner(System.in);
	int bankBalance = 1000;

	public void logOut() {
		System.out.println("----------Thank you for banking with us----------");
	}

	public void deposit(Customer customer) {
		int amount;
		System.out.print("Enter the amount to be deposited: ");
		amount = sc.nextInt();
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
		System.out.println("Enter the amount to be withdrawn: ");
		amount = sc.nextInt();
		if (customer.getBalance() >= amount) {
			System.out.println("Amount withdrawn successfully!");
			customer.setBalance(customer.getBalance() - amount);
			System.out.println("Remaining balance is: " + customer.getBalance());
		} else {
			System.out.println("Please provide a valid input!");
		}
	}

	public void transfer(Customer customer) {
		int amount;
		int otp;
		int otpGenerated;
		int transferAccountNumber;
		System.out.println("Enter the OTP: ");
		OTPService otpService = new OTPService();
		otpGenerated = otpService.generateOTP();
		System.out.println("Generated OTP is: ");
		System.out.println(otpGenerated);
		System.out.println("Enter the OTP: ");
		otp = sc.nextInt();
		if (otp == otpGenerated) {
			System.out.println("OTP validated successfully!");
			System.out.println("Enter the amount to be transferred: ");
			amount = sc.nextInt();
			System.out.println("Enter the account no. to be transferred to: ");
			transferAccountNumber = sc.nextInt();
			if (customer.getBalance() >= amount) {
				System.out.println("Amount was transferred!");
				customer.setBalance(customer.getBalance() - amount);
				System.out.println("Remaining balance is: " + customer.getBalance());
			} else {
				System.out.println("Insufficient balance!");
			}
		}
	}
}
