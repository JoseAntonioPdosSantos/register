package com.lat.gsb.register.handle.util;

import lombok.SneakyThrows;

import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CriptUtil {

    private CriptUtil() {
        throw new IllegalArgumentException();
    }

    @SneakyThrows
    public static String encript(String value) {
        if (value == null) {
            return null;
        }
        var algorithm = MessageDigest.getInstance("MD5");
        byte[] messageDigest = algorithm.digest(value.getBytes(UTF_8));

        StringBuilder hexStringSenhaUser = new StringBuilder();
        for (byte b : messageDigest) {
            hexStringSenhaUser.append(String.format("%02X", 0xFF & b));
        }
        return hexStringSenhaUser.toString();

    }
}
