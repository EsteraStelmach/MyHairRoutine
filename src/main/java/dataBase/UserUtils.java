package dataBase;
import dataBase.domain.Products;
import dataBase.domain.User;
import dataBase.domain.WashRoutine;

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
    private static List<WashRoutine> washRoutineList;
    private static List<User> foundUsers = new ArrayList<>();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundles.messages");

    public static List<WashRoutine> getWashRoutineList() {
        washRoutineList = user.getWashRoutineList();
        return washRoutineList;
    }

    public static void setWashRoutineList(List<WashRoutine> washRoutineList) {
        UserUtils.washRoutineList = washRoutineList;
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


    public static void addNewWash(String washType,String washingDescription,EntityManager entityManager){
        int number = UserUtils.getWashRoutineList().size()+1;
        String washNumber = resourceBundle.getString("mainApplicationUtils.washRoutineTableView.wash")+" " + number;
        WashRoutine washRoutine = WashRoutineUtils.createNewWash(washNumber,washType,washingDescription);
        user.getWashRoutineList().add(washRoutine);
        entityManager.getTransaction().begin();
        entityManager.persist(washRoutine);
        entityManager.getTransaction().commit();
        entityManager.refresh(user);

    }

    public static String getHairPorosity() {
        String porosity=" ";
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
        hairTwistType = user.getHairTwistType();
        name = user.getName();
        lastName = user.getLastName();
        password = user.getPassword();
        id = user.getId();
        washRoutineList = user.getWashRoutineList();

    }
    public static void setFoundUser(String login, EntityManager entityManager){
        foundUsers.clear();
        Query query = entityManager.createQuery(
                "SELECT u from User u WHERE u.login= :login", User.class).
                setParameter("login", login);
        foundUsers = (List<User>) query.getResultList();

    }

    public static void persistUser(User user,EntityManager entityManager){
        washRoutineList = WashRoutineUtils.createDefaultWashRoutineForBeginning(entityManager);
        user.setWashRoutineList(washRoutineList);
       entityManager.persist(user);
       for(WashRoutine wash:washRoutineList){
           entityManager.persist(wash);
       }

    }

    public static String convertHairPorosityString(String hairPorosity){
        String porosity=null;
        if(hairPorosity == null){
            porosity = " ";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.low"))){
            porosity="L";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.medium"))){
            porosity="M";
        }else if(hairPorosity.equals(resourceBundle.getString("secondRegisterWindow.choiceBox.label.high"))){
            porosity="H";
        }
        return porosity;
    }

    public static void removeUserWashRoutine(WashRoutine washRoutine,EntityManager entityManager){
        user.getWashRoutineList().remove(washRoutine);
        entityManager.getTransaction().begin();
        entityManager.remove(washRoutine);
        entityManager.getTransaction().commit();
        entityManager.refresh(UserUtils.getUser());
    }



}
