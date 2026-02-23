package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

import java.util.List;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Validate password confirmation mismatch
 */
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
        List<String> err=form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Password confirmation does not match password!"));

        Assert.assertTrue("Expected password confirm match error, got: " + err, found);
        //Assert.assertTrue("Expected confirm password mismatch error, got:"+err, err.contains("Password confirmation does not match password!"));
    }
}
