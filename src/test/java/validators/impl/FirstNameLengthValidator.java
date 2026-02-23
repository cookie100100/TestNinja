package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

import java.util.List;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Validate first name length
 */
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
        List<String> err=form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("First Name must be between 1 and 32 characters!"));

        Assert.assertTrue("Expected first name error, got: " + err, found);
        //Assert.assertTrue("Expected firstName error, got: "+err, err.contains("First Name must be between 1 and 32 characters!"));
    }
}
