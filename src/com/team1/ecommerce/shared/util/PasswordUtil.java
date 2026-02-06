package com.team1.ecommerce.shared.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password hashing utility for the ecommerce system.
 *
 * <p>For a real system you would use a strong algorithm with salt (e.g. BCrypt),
 * but for this educational project a single SHA-256 hash is sufficient.</p>
 */
public final class PasswordUtil {

    private PasswordUtil() {
    }

    /**
     * Returns a SHA‑256 hex string for the given input.
     */
    public static String hash(String rawPassword) {
        if (rawPassword == null) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(
                    rawPassword.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            return rawPassword;
        }
    }
}

