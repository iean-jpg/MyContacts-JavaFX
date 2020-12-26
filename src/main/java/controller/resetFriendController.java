package controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pojo.Group;
import pojo.Person;
import service.MainService;
import utils.Message;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class resetFriendController extends Application implements Initializable {
    @FXML
    public TextField nameText = new TextField();
    @FXML
    public TextField phoneText = new TextField();
    @FXML
    public TextField addressText = new TextField();
    @FXML
    public RadioButton manChoice = new RadioButton();
    @FXML
    public RadioButton womenChoice = new RadioButton();
    @FXML
    private ToggleGroup tg = new ToggleGroup();

    private String sex = "男";

    @FXML
    private Button postButton = new Button();
    @FXML
    private Button exitButton = new Button();

    @FXML
    private ChoiceBox<String> belongGroup = new ChoiceBox<>();

    private MainService mainService = new MainService();

    @FXML
    public void getPerson() {
        if (nameText != null) {
            Person person = mainService.getPersonByName(nameText.getText());
            if (person != null) {
                phoneText.setText(person.getPersonPhone());
                addressText.setText(person.getPersonAddress());
                String groupNameByID = mainService.getGroupNameByID(person.getPGId());
                belongGroup.getSelectionModel().select(groupNameByID);
                if ("男".equals(person.getPersonSex())) {
                    manChoice.setSelected(true);
                } else {
                    womenChoice.setSelected(true);
                }
                Message.showMeg("提示", "查询成功");
                nameText.setEditable(false);
                return;
            }
        }
        phoneText.setText(null);
        addressText.setText(null);
        belongGroup.setItems(null);
        manChoice.setSelected(false);
        womenChoice.setSelected(false);
        Message.showMeg("提示", "查询失败");

    }

    @FXML
    public void post() {
        List<Group> allGroup = mainService.getAllGroup();

        Person person = new Person();
        person.setPersonName(nameText.getText());
        person.setPersonPhone(phoneText.getText());
        person.setPersonAddress(addressText.getText());
        person.getPersonSex();
        for (Group group : allGroup) {
            if (group.getGroupName().equals(belongGroup.getValue())) {
                person.setPGId(group.getGroupID());
                break;
            }
        }
        if (0 != mainService.updatePerson(person)) {
            Message.showMeg("提示", "修改成功");
            onExit();
            return;
        }
        Message.showMeg("提示", "修改失败");
        onExit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Person/resetFriend.fxml"));
        primaryStage.setTitle("修改好友");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tg.getToggles().addAll(manChoice, womenChoice);
        manChoice.setSelected(true);
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton r = (RadioButton) newValue;
                sex = r.getText();
            }
        });
        initBox();
    }

    @FXML
    private void initBox() {
        List<Group> allGroup = mainService.getAllGroup();
        for (Group group : allGroup) {

            belongGroup.getItems().add(group.getGroupName());
        }
//        belongGroup.getSelectionModel().selectFirst();
    }


    @FXML
    public void onExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }
}
