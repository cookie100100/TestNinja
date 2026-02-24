package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;

import java.util.List;

public class EmptyLastNameValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Empty LastName Length";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("lastName","");
    }
    @Override
    public void assertError(RegistrationForm form) {
        List<String> err = form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Last Name must be between 1 and 32 characters!"));

        Assertions.assertTrue(found, "Expected last name error, got: " + err);
    }
}
