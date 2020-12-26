package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Group;
import service.MainService;
import utils.Message;


public class addGroupController extends Application {
    private MainService mainService = new MainService();
    @FXML
    public Button add = new Button();
    @FXML
    public Button quit = new Button();
    @FXML
    public TextField GroupName = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Group/addGroup.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void addGroup() {
        Group group = new Group();
        group.setGroupName(GroupName.getText());
        if (GroupName.getText() != null) {
            if (mainService.addGroup(group) != 0) {
                Message.showMeg("提示", "添加成功");
                quit();
                return;
            }
        }
        Message.showMeg("提示", "添加失败");
        quit();
    }

    @FXML
    public void quit() {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }
}
