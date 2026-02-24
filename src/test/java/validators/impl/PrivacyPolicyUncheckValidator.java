package validators.impl;

import form.RegistrationForm;
import org.junit.jupiter.api.Assertions;
import validators.RegistrationValidator;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe validate checked privacy policy box
 */
public class PrivacyPolicyUncheckValidator implements RegistrationValidator {
    @Override
    public String name(){
        return "Privacy Policy Uncheck";
    }
    @Override
    public void makeInvalid(RegistrationForm form){
        form.uncheck("privacyPolicy");
    }
    @Override
    public void assertError(RegistrationForm form){
        String page=form.getPageWarningMessage();
        Assertions.assertTrue( page.contains("Warning") && page.contains("You must agree to the Privacy Policy!"),"Expected page warning for privacy policy, got:"+page);
    }
}
