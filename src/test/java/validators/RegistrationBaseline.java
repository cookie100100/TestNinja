package validators;

import form.RegistrationForm;

public final class RegistrationBaseline {
    private RegistrationBaseline() {}
    public static void fillValid(RegistrationForm form){
        String uniqueEmail="laura"+System.currentTimeMillis()+"@test.com";
        form.fillField("firstName","Laura");
        form.fillField("lastName","Xu");
        form.fillField("email",uniqueEmail);
        form.fillField("telephone","12345678");
        form.fillField("password","123test");
        form.fillField("confirmPassword","123test");
        form.check("privacyPolicy");
    }
}
