package form;
import org.openqa.selenium.WebDriver;

public class GenericFormWrapper<F>{

    protected final F form;

    public GenericFormWrapper( F form) {
        this.form = form;
    }

    public F getForm() {
        return form;
    }
}