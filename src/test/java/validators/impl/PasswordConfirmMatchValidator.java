package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

public class PasswordConfirmMatchValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Password Confirm Match";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("password", "123test");
        form.fillField("confirmPassword", "DIFFERENT");
    }
    @Override
    public void assertError(RegistrationForm form) {
        String err=form.getFieldErrorMessage("confirmPassword");
        Assert.assertTrue("Expected confirm password mismatch error, got:"+err, err.contains("Password confirmation does not match password!"));
    }
}
