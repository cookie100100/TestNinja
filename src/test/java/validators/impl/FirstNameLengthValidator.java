package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

public class FirstNameLengthValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "FirstName Length 1-32";
    }
    @Override
    public void makeInvalid(RegistrationForm form){
        form.fillField("firstName","a".repeat(33));
    }
    @Override
    public void assertError(RegistrationForm form){
        String err=form.getFieldErrorMessage("firstName");
        Assert.assertTrue("Expected firstName error, got: "+err, err.contains("First Name must be between 1 and 32 characters!"));
    }
}
