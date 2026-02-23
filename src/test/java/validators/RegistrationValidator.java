package validators;

import form.RegistrationForm;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe interface for registration validator.
 */
public interface RegistrationValidator {
    String name();
    void makeInvalid(RegistrationForm form);
    void assertError(RegistrationForm form);
}
