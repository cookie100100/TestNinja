package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

public class EmailFormatValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Email Format";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("email","111@11.11");
    }
    @Override
    public void assertError(RegistrationForm form) {
        String err= form.getFieldErrorMessage("email");
        Assert.assertTrue("Expected email error, got: " +err, err.contains("E-Mail Address does not appear to valid!"));
    }
}
