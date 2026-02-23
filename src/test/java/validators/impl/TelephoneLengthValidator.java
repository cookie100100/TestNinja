package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

import java.util.List;

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
        List<String> err=form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("Telephone must be between 3 and 32 characters!"));

        Assert.assertTrue("Expected telephone length error, got: " + err, found);
        //Assert.assertTrue("Expected telephone error, got: "+err, err.contains("Telephone must be between 3 and 32 characters!"));
    }
}
