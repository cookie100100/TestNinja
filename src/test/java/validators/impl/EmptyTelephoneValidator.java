package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;

import java.util.List;

public class EmptyTelephoneValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Empty Telephone Length";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("telephone", "");
    }
    @Override
    public void assertError(RegistrationForm form) {
        List<String> err=form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Telephone must be between 3 and 32 characters!"));

        Assertions.assertTrue(found, "Expected telephone length error, got: " + err);
    }
}
