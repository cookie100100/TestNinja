package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

public class PasswordLengthValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Password Length 4-20";
    }

    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("password", "123");
        form.fillField("confirmPassword", "123");
    }

    @Override
    public void assertError(RegistrationForm form) {
        String err=form.getFieldErrorMessage("password");
        Assert.assertTrue("Expected password error, got: "+err, err.contains("Password must be between 4 and 20 characters!"));
    }
}
