package validators;

import form.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Registration Validation runner.
 */
public class RegistrationValidationRunner {
    private final RegistrationForm form;
    private final List<RegistrationValidator> validators;
    private static final Logger log = LoggerFactory.getLogger(RegistrationValidationRunner.class);
    public RegistrationValidationRunner(RegistrationForm form, List<RegistrationValidator> validators) {
        this.form = form;
        this.validators = validators;
    }
    public void runAll(){
        log.info("Running {} registration validators", validators.size());
        validators.forEach(v->{
            String name=v.name();
            log.info("Validating START: {}", name);
            try {
                form.open();
                RegistrationBaseline.fillValid(form);
                v.makeInvalid(form);
                form.submit();
                v.assertError(form);
                log.info("Validating PASS: {}", name);
            } catch(AssertionError ae){
                log.error("Validation FAILED: {} | {}", name, ae.getMessage());
                throw ae;
            } catch(Exception e){
                log.error("Validation FAILED: {} | {}", name, e.toString());
                throw e;
            }
        });
        log.info("All registration validations finished");
    }
}
