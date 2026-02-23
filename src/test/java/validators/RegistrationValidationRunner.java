package validators;

import form.RegistrationForm;

import java.util.List;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Registration Validation runner.
 */
public class RegistrationValidationRunner {
    private final RegistrationForm form;
    private final List<RegistrationValidator> validators;
    public RegistrationValidationRunner(RegistrationForm form, List<RegistrationValidator> validators) {
        this.form = form;
        this.validators = validators;
    }
    public void runAll(){
        validators.forEach(v->{
            form.open();
            RegistrationBaseline.fillValid(form);
            v.makeInvalid(form);
            form.submit();
            v.assertError(form);
        });
    }
}
