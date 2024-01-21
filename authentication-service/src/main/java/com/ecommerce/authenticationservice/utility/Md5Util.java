package com.ecommerce.authenticationservice.utility;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    public static final Md5Util singletonInstance = null;


    private Md5Util() {
    }

    public static synchronized Md5Util getInstance() {
        if (singletonInstance == null) {
            return new Md5Util();
        }
        return singletonInstance;
    }

    public String getMd5Hash(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(data.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }
}
