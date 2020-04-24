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

    public String getLoginFieldProperty() {
        return loginFieldProperty.get();
    }

    public StringProperty loginFieldPropertyProperty() {
        return loginFieldProperty;
    }

    public void setLoginFieldProperty(String loginFieldProperty) {
        this.loginFieldProperty.set(loginFieldProperty);
    }

    public String getPasswordFieldProperty() {
        return passwordFieldProperty.get();
    }

    public StringProperty passwordFieldPropertyProperty() {
        return passwordFieldProperty;
    }

    public void setPasswordFieldProperty(String passwordFieldProperty) {
        this.passwordFieldProperty.set(passwordFieldProperty);
    }

    public boolean isDisablePasswordProperty() {
        return disablePasswordProperty.get();
    }

    public BooleanProperty disablePasswordPropertyProperty() {
        return disablePasswordProperty;
    }

    public void setDisablePasswordProperty(boolean disablePasswordProperty) {
        this.disablePasswordProperty.set(disablePasswordProperty);
    }

    public boolean isLoginButtonProperty() {
        return loginButtonProperty.get();
    }

    public BooleanProperty loginButtonPropertyProperty() {
        return loginButtonProperty;
    }

    public void setLoginButtonProperty(boolean loginButtonProperty) {
        this.loginButtonProperty.set(loginButtonProperty);
    }
}
