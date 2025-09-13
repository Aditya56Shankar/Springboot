package com.OTP.aditya;

import com.OTP.aditya.config.OtpEntity;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AdityaApplication {

	@Autowired
	private OtpEntity otp;

	@PostConstruct
	public void initTwilio() {
		Twilio.init(otp.getAccount_sid(), otp.getAuth_token());
	}

	public static void main(String[] args) {
		SpringApplication.run(AdityaApplication.class, args);
	}
}
