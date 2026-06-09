package org.pcx.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Hash
{
    public static String getMD5(java.io.File file) throws IOException {
        return hashFile(file, "MD5");
    }

    public static String getSHA1(java.io.File file) throws IOException {
        return hashFile(file, "SHA-1");
    }

    public static String getSHA256(java.io.File file) throws IOException {
        return hashFile(file, "SHA-256");
    }

    public static String getSHA512(java.io.File file) throws IOException {
        return hashFile(file, "SHA-512");
    }

    private static String hashFile(java.io.File file, String algorithm)
            throws IOException {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] byteArray = new byte[8192];
                int bytesRead;

                while ((bytesRead = fis.read(byteArray)) != -1) {
                    digest.update(byteArray, 0, bytesRead);
                }
            }

            return bytesToHex(digest.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo no soportado: " + algorithm, e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}


