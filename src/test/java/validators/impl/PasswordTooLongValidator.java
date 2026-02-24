package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;

import java.util.List;

public class PasswordTooLongValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Password too long";
    }

    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("password", "a".repeat(45));
        form.fillField("confirmPassword", "a".repeat(45));
    }

    @Override
    public void assertError(RegistrationForm form) {
        List<String> err = form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Password must be between 4 and 20 characters!"));

        Assertions.assertTrue(found, "Expected password length error, got: " + err);
    }
}
