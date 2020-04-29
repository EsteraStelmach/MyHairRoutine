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

    public boolean isRepeatPasswordEqualsPassword(String password, String repeatPassword){
        return password.equals(repeatPassword);
    }

   public RegisterWindowProperties(){
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

    public boolean isRegisterButtonProperty() {
        return registerButtonProperty.get();
    }

    public BooleanProperty registerButtonPropertyProperty() {
        return registerButtonProperty;
    }

    public String getLoginTextFieldProperty() {
        return loginTextFieldProperty.get();
    }

    public String getPasswordTextFieldProperty() {
        return passwordTextFieldProperty.get();
    }

    public String getPasswordTextField2Property() {
        return passwordTextField2Property.get();
    }
}


