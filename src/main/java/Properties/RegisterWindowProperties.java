package Properties;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;


public class RegisterWindowProperties {

    private StringProperty loginTextFieldProperty = new SimpleStringProperty();

    private StringProperty passwordTextFieldProperty = new SimpleStringProperty();

    private StringProperty passwordTextField2Property = new SimpleStringProperty();

    private BooleanProperty registerButtonProperty = new SimpleBooleanProperty(false);


    public RegisterWindowProperties() {
        registerButtonProperty.bind(passwordTextField2Property.isEmpty());
    }


    public StringProperty loginTextFieldPropertyProperty() {
        return loginTextFieldProperty;
    }

    public StringProperty passwordTextFieldPropertyProperty() {
        return passwordTextFieldProperty;
    }


    public StringProperty passwordTextField2PropertyProperty() {
        return passwordTextField2Property;
    }


    public BooleanProperty registerButtonPropertyProperty() {
        return registerButtonProperty;
    }
}


