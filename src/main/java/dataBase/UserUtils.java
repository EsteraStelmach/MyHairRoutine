package dataBase;
import dataBase.domain.User;
import dataBase.domain.UserHairRoutine;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserUtils {

    private static String login;
    private static int id;
    private static String password;
    private static String hairPorosity;
    private static String hairTwistType;
    private static String name;
    private static String lastName;
    private static User user;
    private static List<User> foundUsers = new ArrayList<>();
    private static UserHairRoutine userHairRoutine= new UserHairRoutine();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");

    public static UserHairRoutine getUserHairRoutine() {
        return userHairRoutine;
    }

    public static void setUserHairRoutine(UserHairRoutine userHairRoutine) {
        UserUtils.userHairRoutine = userHairRoutine;
    }

    public static List<User> getFoundUsers() {
        return foundUsers;
    }

    public static User getUser() {
        return user;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        UserUtils.login = login;
    }

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserUtils.name = name;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        UserUtils.lastName = lastName;
    }

    public static void setId(int id) {
        UserUtils.id = id;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UserUtils.password = password;
    }

    public static void setHairPorosity(String hairPorosity) {
        UserUtils.hairPorosity = hairPorosity;
    }

    public static String getHairTwistType() {
        return hairTwistType;
    }

    public static void setHairTwistType(String hairTwistType) {
        UserUtils.hairTwistType = hairTwistType;
    }

    public static String getHairPorosity() {
        String porosity=null;
        if(hairPorosity.equals("L")){
            porosity= resourceBundle.getString("secondRegisterWindow.choiceBox.label.low");
        }else if(hairPorosity.equals("M")){
            porosity= resourceBundle.getString("secondRegisterWindow.choiceBox.label.medium");
        }else if(hairPorosity.equals("H")){
            porosity= resourceBundle.getString("secondRegisterWindow.choiceBox.label.high");
        }
        return porosity;
    }


    public static void upDateUserLogin(String newLogin, EntityManager entityManager){
        entityManager.getTransaction().begin();
        User helpUser = entityManager.find(User.class,user.getId());
        helpUser.setLogin(newLogin);
        entityManager.merge(helpUser);
        entityManager.getTransaction().commit();
        login=newLogin;
    }

    public static void upDateUserPassword(String newPassword, EntityManager entityManager){
        entityManager.getTransaction().begin();
        User helpUser = entityManager.find(User.class,user.getId());
        helpUser.setPassword(newPassword);
        entityManager.merge(helpUser);
        entityManager.getTransaction().commit();
        password=newPassword;

    }
    public static void upDateUserName(String newName, EntityManager entityManager){
        entityManager.getTransaction().begin();
        User helpUser = entityManager.find(User.class,user.getId());
        helpUser.setName(newName);
        entityManager.merge(helpUser);
        entityManager.getTransaction().commit();
        name=newName;

    }
    public static void upDateUserLastName(String newLastName, EntityManager entityManager){
        entityManager.getTransaction().begin();
        User helpUser = entityManager.find(User.class,user.getId());
        helpUser.setLastName(newLastName);
        entityManager.merge(helpUser);
        entityManager.getTransaction().commit();
        lastName=newLastName;

    }
    public static void upDateUserPorosity(String newPorosity, EntityManager entityManager){
        entityManager.getTransaction().begin();
        User helpUser = entityManager.find(User.class,user.getId());
        helpUser.setHairPorosity(newPorosity);
        entityManager.merge(helpUser);
        entityManager.getTransaction().commit();
        hairPorosity=newPorosity;

    }
    public static void upDateUserTwistType(String newTwistType, EntityManager entityManager){
        entityManager.getTransaction().begin();
        User helpUser = entityManager.find(User.class,user.getId());
        helpUser.setHairTwistType(newTwistType);
        entityManager.merge(helpUser);
        entityManager.getTransaction().commit();
        hairTwistType=newTwistType;

    }

    public static void setUserInformation() {
        user = foundUsers.get(0);
        hairPorosity = user.getHairPorosity();
        System.out.println(hairPorosity);
        hairTwistType = user.getHairTwistType();
        System.out.println(hairTwistType);
        name = user.getName();
        System.out.println(name);
        lastName = user.getLastName();
        System.out.println(lastName);
        password = user.getPassword();
        System.out.println(password);
        id = user.getId();
        System.out.println(id);
    }
    public static void setFoundUser(String login, EntityManager entityManager){
        foundUsers.clear();
        Query query = entityManager.createQuery(
                "SELECT u from User u WHERE u.login= :login", User.class).
                setParameter("login", login);
        foundUsers = (List<User>) query.getResultList();

    }

    public static void persistUser(User user,EntityManager entityManager){
       entityManager.persist(user);
       entityManager.persist(userHairRoutine);
       user.setUserHairRoutine(userHairRoutine);

    }

    public static String convertHairPorosityString(String hairPorosity){
        String porosity=null;
        if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.low"))){
            porosity="L";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.medium"))){
            porosity="M";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.high"))){
            porosity="H";
        }
        return porosity;
    }



}
