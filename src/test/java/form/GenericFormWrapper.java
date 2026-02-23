package form;
import org.openqa.selenium.WebDriver;
/*
 * @author     Laura Xu
 * @date     2026/02/23
 * @describe Generic Form Wrapper for forms.
 */

public class GenericFormWrapper<F>{

    protected final F form;

    public GenericFormWrapper( F form) {
        this.form = form;
    }

    public F getForm() {
        return form;
    }
}