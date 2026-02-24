package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;

import java.util.List;

public class EmptyConfirmPasswordValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Empty Password Confirm";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("password", "123test");
        form.fillField("confirmPassword", "");
    }
    @Override
    public void assertError(RegistrationForm form) {
        List<String> err = form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Password confirmation does not match password!"));

        Assertions.assertTrue(found, "Expected password confirm match error, got: " + err);
    }
}
