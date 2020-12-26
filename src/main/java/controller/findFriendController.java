package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pojo.Person;
import service.MainService;
import utils.Message;

public class findFriendController extends Application {
    @FXML
    public TextField nameText = new TextField();
    @FXML
    public TextField phoneText = new TextField();
    @FXML
    public TextField addressText = new TextField();
    @FXML
    public TextField groupText = new TextField();
    @FXML
    public TextField sexText = new TextField();
    @FXML
    private Button getButton = new Button();
    @FXML
    private Button exitButton = new Button();

    private MainService mainService = new MainService();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Person/findFriend.fxml"));
        primaryStage.setTitle("查询");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void getPerson() {
        if(nameText!=null){
            Person person = mainService.getPersonByName(nameText.getText());
            if(person!=null){
                phoneText.setText(person.getPersonPhone());
                addressText.setText(person.getPersonAddress());
                String groupNameByID = mainService.getGroupNameByID(person.getPGId());
                groupText.setText(groupNameByID);
                sexText.setText(person.getPersonSex());
                Message.showMeg("提示","查询成功");
                return;
            }
        }
        phoneText.setText(null);
        addressText.setText(null);
        groupText.setText(null);
        sexText.setText(null);
        Message.showMeg("提示","查询失败");
    }

    @FXML
    public void onExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }
}
