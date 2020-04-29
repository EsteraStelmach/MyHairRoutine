package Properties;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginWindowProperties {

    //Properties
    //login Password Field
    private StringProperty loginFieldProperty = new SimpleStringProperty();

    //login Password Password
    private StringProperty passwordFieldProperty = new SimpleStringProperty();

    //on/of Password Field
    private BooleanProperty disablePasswordProperty = new SimpleBooleanProperty(false);

    //login Button
    private BooleanProperty loginButtonProperty = new SimpleBooleanProperty(true);

    public LoginWindowProperties() {
        disablePasswordProperty.bind(loginFieldProperty.isEmpty());
        loginButtonProperty.bind(passwordFieldProperty.isEmpty());

    }

    public StringProperty loginFieldPropertyProperty() {
        return loginFieldProperty;
    }

    public StringProperty passwordFieldPropertyProperty() {
        return passwordFieldProperty;
    }

    public BooleanProperty disablePasswordPropertyProperty() {
        return disablePasswordProperty;
    }

    public BooleanProperty loginButtonPropertyProperty() {
        return loginButtonProperty;
    }

}
