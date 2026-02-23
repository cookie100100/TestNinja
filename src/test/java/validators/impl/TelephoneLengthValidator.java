package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

public class TelephoneLengthValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Telephone Length 3-32";
    }
    @Override
    public void makeInvalid(RegistrationForm form) {
        form.fillField("telephone", "1");
    }
    @Override
    public void assertError(RegistrationForm form) {
        String err=form.getFieldErrorMessage("telephone");
        Assert.assertTrue("Expected telephone error, got: "+err, err.contains("Telephone must be between 3 and 32 characters!"));
    }
}
