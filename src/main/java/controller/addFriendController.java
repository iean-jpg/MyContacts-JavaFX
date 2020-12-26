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

public class addFriendController extends Application implements Initializable {
    @FXML
    public TextField FriendName = new TextField();
    @FXML
    public TextField FriendPhone = new TextField();
    @FXML
    public TextField FriendCall = new TextField();
    @FXML
    public TextField FriendAddress = new TextField();
    @FXML
    public RadioButton Man = new RadioButton();
    @FXML
    public RadioButton woman = new RadioButton();
    @FXML
    private ToggleGroup tg = new ToggleGroup();

    private String sex = "男";

    @FXML
    private Button post = new Button();
    @FXML
    private Button cancel = new Button();

    @FXML
    private ChoiceBox<String> blongGroup = new ChoiceBox<>();

    private MainService mainService = new MainService();

    @FXML
    public void post() {
        List<Group> allGroup = mainService.getAllGroup();

        Person person = new Person();
        person.setPersonName(FriendName.getText());
        person.setPersonPhone(FriendPhone.getText());
        person.setPersonCall(FriendCall.getText());
        person.setPersonAddress(FriendAddress.getText());
        person.setPersonSex(sex);
        for (Group group : allGroup) {
            if (group.getGroupName().equals(blongGroup.getValue())) {
                person.setPGId(group.getGroupID());
                break;
            }
        }

        if (0 != mainService.addPerson(person)) {
            Message.showMeg("提示", "添加成功");
            cancel();
            return;
        }
        Message.showMeg("提示", "添加失败");
        cancel();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/person/addFriend.fxml"));
        primaryStage.setTitle("添加好友");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tg.getToggles().addAll(Man, woman);
        Man.setSelected(true);
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
            blongGroup.getItems().add(group.getGroupName());
        }
        blongGroup.getSelectionModel().selectFirst();
    }


    @FXML
    public void cancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
