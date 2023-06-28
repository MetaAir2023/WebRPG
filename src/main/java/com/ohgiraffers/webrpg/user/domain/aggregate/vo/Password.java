package com.ohgiraffers.webrpg.user.domain.aggregate.vo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {
    private String value;


    public Password() {

    }

    public Password(String value) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((value).getBytes());
            byte[] pwdSalt = md.digest();

            StringBuffer pwdSb = new StringBuffer();
            for(byte b : pwdSalt) {
                pwdSb.append(String.format("%02x", b));
            }
            this.value = pwdSb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue() {
        return this.value;
    }
}
