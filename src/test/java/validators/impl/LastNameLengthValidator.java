package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

import java.util.List;

public class LastNameLengthValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "LastName Length 1-32";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("lastName","a".repeat(33));
    }
    @Override
    public void assertError(RegistrationForm form) {
        List<String> err= form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Last Name must be between 1 and 32 characters!"));

        Assert.assertTrue("Expected last name error, got: " + err, found);
        //Assert.assertTrue("Expected lastName error, got"+err, err.contains("Last Name must be between 1 and 32 characters!"));
    }
}
