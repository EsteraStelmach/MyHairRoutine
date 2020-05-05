package Properties;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SecondRegisterWindowProperties {

    private StringProperty hairTwistProperty = new SimpleStringProperty();
    private StringProperty hairPorosityProperty = new SimpleStringProperty();

    private BooleanProperty checkBoxHairTwistProperty = new SimpleBooleanProperty();
    private BooleanProperty checkBoxHairPorosityProperty = new SimpleBooleanProperty();


    public String getHairTwistProperty() {
        return hairTwistProperty.get();
    }

    public StringProperty hairTwistPropertyProperty() {
        return hairTwistProperty;
    }

    public boolean isCheckBoxHairTwistProperty() {
        return checkBoxHairTwistProperty.get();
    }

    public BooleanProperty checkBoxHairTwistPropertyProperty() {
        return checkBoxHairTwistProperty;
    }

    public boolean isCheckBoxHairPorosityProperty() {
        return checkBoxHairPorosityProperty.get();
    }

    public BooleanProperty checkBoxHairPorosityPropertyProperty() {
        return checkBoxHairPorosityProperty;
    }

    public void setHairTwistProperty(String hairTwistProperty) {
        this.hairTwistProperty.set(hairTwistProperty);
    }

    public String getHairPorosityProperty() {
        return hairPorosityProperty.get();
    }

    public StringProperty hairPorosityPropertyProperty() {
        return hairPorosityProperty;
    }

    public void setHairPorosityProperty(String hairPorosityProperty) {
        this.hairPorosityProperty.set(hairPorosityProperty);
    }
}
