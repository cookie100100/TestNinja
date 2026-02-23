package validators;

import form.RegistrationForm;

public interface RegistrationValidator {
    String name();
    void makeInvalid(RegistrationForm form);
    void assertError(RegistrationForm form);
}
