package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

import java.util.List;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Validate invalid email format
 */
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
        List<String> err= form.getFieldErrorMessage();
        boolean found = err.stream()
                .anyMatch(e -> e.contains("E-Mail Address does not appear to be valid!"));

        Assert.assertTrue("Expected email error, got: " + err, found);
    }
}
