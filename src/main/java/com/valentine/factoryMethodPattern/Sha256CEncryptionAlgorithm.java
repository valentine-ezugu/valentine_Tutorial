package com.valentine.factoryMethodPattern;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * This is product creator so it implements product
 */
public class Sha256CEncryptionAlgorithm implements EncryptionAlgorithm {


    @Override
    public String encrypt(String plaintext) {
        return DigestUtils.sha256Hex(plaintext);
    }

}
