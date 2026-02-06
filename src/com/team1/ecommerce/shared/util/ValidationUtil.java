package com.team1.ecommerce.shared.util;

/**
 * Validation utility for the ecommerce system.
 *
 * <p>The methods are intentionally simple and easy to read so that
 * business rules in the services stay clean.</p>
 */
public final class ValidationUtil {

    private ValidationUtil() {
        // utility class
    }

    /**
     * Returns {@code true} if the text is not {@code null} and not blank.
     */
    public static boolean hasText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Very small email check suitable for this project.
     */
    public static boolean isValidEmail(String email) {
        if (!hasText(email)) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    /**
     * Username must be at least 3 characters and contain no spaces.
     */
    public static boolean isValidUsername(String username) {
        if (!hasText(username)) {
            return false;
        }
        return username.length() >= 3 && !username.contains(" ");
    }

    /**
     * Password must be at least 6 characters.
     */
    public static boolean isValidPassword(String password) {
        if (!hasText(password)) {
            return false;
        }
        return password.length() >= 6;
    }
}

