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

public class resetGroupController extends Application {

    private MainService mainService = new MainService();

    @FXML
    public Button reset = new Button();

    @FXML
    public Button quit = new Button();

    @FXML
    public TextField GroupID = new TextField();

    @FXML
    public TextField GroupName = new TextField();

    @FXML
    public Button get = new Button();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Group/resetGroup.fxml"));
        primaryStage.setTitle("修改组");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void resetGroup() {
        if(GroupName.getText()!=null){
            Group group = new Group();
            group.setGroupName(GroupName.getText());
            group.setGroupID(Integer.parseInt(GroupID.getText()));
            int i = mainService.updateGroupByID(group);
            if(i!=0){
                Message.showMeg("提示", "修改成功");
                quit();
                return;
            }
        }
        Message.showMeg("提示", "修改失败");
        quit();
    }

    @FXML
    public void quit(){
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void getGroup(){
        if(GroupName.getText()!=null){
            Group group = mainService.getGroupByName(GroupName.getText());
            if(group!=null){
                GroupID.setText(String.valueOf(group.getGroupID()));
            }
        }
    }
}
