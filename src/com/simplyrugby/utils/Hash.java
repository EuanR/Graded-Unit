package com.simplyrugby.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Euan
 */
public class Hash {

    /**
     * Hashes a given string using the SHA512 secure hash algorithm
     *
     * <pre>
     *     {@code
     *      String hashedPassword = Hash.getSha512("mySecretPassword");
     *     }
     * </pre>
     *
     * @param password The password that will be hashed
     * @return The hashed version of the password provided in the parameters
     */
    public static String getSha512(String password) {
        String hash;
        hash = DigestUtils.sha512Hex(password);
        return hash;
    }

    /**
     * Hashes a given string using the SHA384 secure hash algorithm
     *
     * <pre>
     *     {@code
     *      String hashedPassword = Hash.getSha384("mySecretPassword");
     *     }
     * </pre>
     *
     * @param password The password that will be hashed
     * @return The hashed version of the password provided in the parameters
     */
    public static String getSha384(String password) {
        String hash;
        hash = DigestUtils.sha384Hex(password);
        return hash;
    }

    /**
     * Hashes a given string using the SHA256 secure hash algorithm
     *
     * <pre>
     *     {@code
     *      String hashedPassword = Hash.getSha256("mySecretPassword");
     *     }
     * </pre>
     *
     * @param password The password that will be hashed
     * @return The hashed version of the password provided in the parameters
     */
    public static String getSha256(String password) {
        String hash;
        hash = DigestUtils.sha256Hex(password);
        return hash;
    }

    /**
     * Hashes a given string using the MD5 secure hash algorithm
     *
     * <pre>
     *     {@code
     *      String hashedPassword = Hash.getMD5("mySecretPassword");
     *     }
     * </pre>
     *
     * @param password The password that will be hashed
     * @return The hashed version of the password provided in the parameters
     */
    public static String getMD5(String password) {
        String hash;
        hash = DigestUtils.md5Hex(password);
        return hash;
    }
}
