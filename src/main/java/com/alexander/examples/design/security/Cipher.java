package com.alexander.examples.design.security;

/**
 * Created by Alexander on 06/07/2016.
 */
public interface Cipher {
    public byte[] encrypt(byte[] plainBytes);

    public byte[] decrypt(byte[] cipherBytes);
}
