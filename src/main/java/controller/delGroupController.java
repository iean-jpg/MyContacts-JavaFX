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


public class delGroupController extends Application {

    private MainService mainService = new MainService();

    @FXML
    public Button del = new Button();

    @FXML
    public Button quit = new Button();

    @FXML
    public TextField GroupName = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/group/delGroup.fxml"));
        primaryStage.setTitle("删除组");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void delGroup(){
        String groupName = GroupName.getText();
        Group group = mainService.getGroupByName(groupName);
        if ( group != null) {
            int i = mainService.delGroupByName(groupName);
            //int j = mainService.delPersonByGroupID(group.getGroupID());
            if (i!=0) {
                Message.showMeg("提示", "删除成功");
                quit();
                return;
            }
        }
        Message.showMeg("提示", "删除失败");
        quit();
    }

    @FXML
    public void quit() {
        Stage stage = (Stage) quit.getScene().getWindow();
        stage.close();
    }

}
