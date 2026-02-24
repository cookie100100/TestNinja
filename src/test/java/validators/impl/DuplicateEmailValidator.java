package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Validate email already registered.
 */

public class DuplicateEmailValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Duplicate Email";
    }
    @Override
    public void makeInvalid(RegistrationForm form){
        form.fillField("email","longyang.xu12@gmail.com");
    }
    @Override
    public void assertError(RegistrationForm form){
        String page=form.getPageWarningMessage();
        Assertions.assertTrue(page.contains("Warning") && page.contains("E-Mail Address is already registered!"), "Expected page warning for email already registered, got:"+page);
    }
}

