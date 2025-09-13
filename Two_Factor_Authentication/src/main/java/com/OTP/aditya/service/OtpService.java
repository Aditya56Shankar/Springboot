package com.OTP.aditya.service;

import com.OTP.aditya.config.OtpEntity;
import com.OTP.aditya.dto.PasswordRe;
import com.OTP.aditya.dto.PasswordResponse;
import com.OTP.aditya.dto.Status;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    @Autowired
    private OtpEntity otpe;
    Map<String,String> map=new HashMap<>();

//    public Mono<PasswordResponse> sendOtp(PasswordRe request){
//        PasswordResponse res=null;
//        try {
//            String otp=generateOtp();
//            String otpMessage="your otp is "+otp;
//            PhoneNumber to_phone=new PhoneNumber(request.getNumber());
//            PhoneNumber from_phone=new PhoneNumber(otpe.getTrial_number());
//
//
//            Message message= Message
//                    .creator(
//                            to_phone,
//                            from_phone,
//                            otpMessage)
//                    .create();
//            map.put(request.getUsername(),otp);
//            res = new PasswordResponse(otpMessage, Status.deliverd);
//        }catch (Exception e){
//            res=new PasswordResponse(e.getMessage(), Status.failed);
//        }
//        return Mono.just(res);
//
//
//    }

    public PasswordResponse sendOtp(PasswordRe request) {
        PasswordResponse res;
        try {
            String otp = generateOtp();
            String otpMessage = "Your OTP is " + otp;

            PhoneNumber toPhone = new PhoneNumber(request.getNumber());
            PhoneNumber fromPhone = new PhoneNumber(otpe.getTrial_number());

            Message.creator(
                    toPhone,
                    fromPhone,
                    otpMessage
            ).create();

            map.put(request.getUsername(), otp);
            res = new PasswordResponse(otpMessage, Status.deliverd);
        } catch (Exception e) {
            res = new PasswordResponse(e.getMessage(), Status.failed);
        }
        return res;
    }

    public boolean validate(String Input, String Username){
        if(Input.equals(map.get(Username))){
            return true;
        }
        return false;
    }

    private String generateOtp(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
