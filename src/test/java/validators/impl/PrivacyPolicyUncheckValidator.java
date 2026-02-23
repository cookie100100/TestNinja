package validators.impl;

import form.RegistrationForm;
import org.junit.Assert;
import validators.RegistrationValidator;

public class PrivacyPolicyUncheckValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Privacy Prolicy Uncheck";
    }
    @Override
    public void makeInvalid(RegistrationForm form){
        form.uncheck("privacyPolicy");
    }
    @Override
    public void assertError(RegistrationForm form){
        String page=form.getPageErrorMessage();
        Assert.assertTrue("Expected page warning for privacy policy, got:"+page, page.contains("Warning") && page.contains("You must agree to the Privacy Policy!"));
    }
}
