package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

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
        String err= form.getFieldErrorMessage("lastName");
        Assert.assertTrue("Expected lastName error, got"+err, err.contains("Last Name must be between 1 and 32 characters!"));
    }
}
