package com.greatlearning.services;

public class OTPService {
	public int generateOTP() {
		int randomPin = (int) (Math.random() * 9000) + 1000;
		return randomPin;
	}
}