package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import pojo.Group;
import pojo.Person;
import service.MainService;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private MainService mainService = new MainService();

    @FXML
    private TreeView<String> tree = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }



    @FXML
    private synchronized void init() {
//        tree.setCellFactory(p -> new TextFieldTreeCell<>(new DefaultStringConverter()));

        TreeItem<String> root = new TreeItem<>();
        root.setValue("通讯录");
        root.setExpanded(true);
        tree.setRoot(root);

        for (Group group : MainService.getAllGroup()) {
            TreeItem<String> item = createGroup(group.getGroupName());
            for (Person person : MainService.getAllPerson()) {
                if (Objects.equals(person.getPGId(), group.getGroupID())) {
                    createPerson(item, person.getPersonName());
                }
            }
        }
    }
    @FXML
    public void quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void refresh(ActionEvent event) {
        init();
    }

    @FXML
    public void addGroup(ActionEvent event) {
        try {
            new addGroupController().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void delGroup(ActionEvent event) {
        try {
            new delGroupController().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void resetGroup(ActionEvent event) {
        try {
            new resetGroupController().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addFriend(ActionEvent event){
        try{
            new addFriendController().start(new Stage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void delFriend(ActionEvent event){
        try{
            new delFriendController().start(new Stage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void findFriend(ActionEvent event){
        try{
            new findFriendController().start(new Stage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void resetFriend(ActionEvent event){
        try{
            new resetFriendController().start(new Stage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public TreeItem<String> createGroup(String msg) {
        TreeItem<String> newItem = new TreeItem<>();
        newItem.setValue(msg);
        newItem.setExpanded(true);
        tree.getRoot().getChildren().add(newItem);
        tree.requestFocus();
        return newItem;
    }

    @FXML
    public void createPerson(TreeItem<String> group, String name) {
        TreeItem<String> newItem = new TreeItem<>();
        newItem.setValue(name);
        newItem.setExpanded(true);
        group.getChildren().add(newItem);
    }
}
