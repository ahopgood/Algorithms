package com.alexander.examples.design.security;

/**
 * Created by Alexander on 05/07/2016.
 */
public class OneTimePad {

    private String pad;

    public OneTimePad(String pad){
        this.pad = pad;
    }
    public String encrypt(String plaintext){
        return plaintext;
    }

    public String decrypt(String ciphertext){
        return ciphertext;
    }
}
