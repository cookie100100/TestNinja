package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;

import java.util.List;

public class EmptyEmailValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Email empty";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("email","");
    }
    @Override
    public void assertError(RegistrationForm form) {
        List<String> err= form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("E-Mail Address does not appear to be valid!"));

        Assertions.assertTrue(found, "Expected email error, got: " + err);
    }
}
