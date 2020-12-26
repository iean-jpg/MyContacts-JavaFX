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
import service.MainService;
import utils.Message;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class delFriendController extends Application implements Initializable {
    private MainService mainService = new MainService();
    @FXML
    private ChoiceBox<String> BelongGroup = new ChoiceBox<>();

    @FXML
    public Button del = new Button();

    @FXML
    public Button quit = new Button();

    @FXML
    public TextField FriendName = new TextField();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Person/delFriend.fxml"));
        primaryStage.setTitle("删除好友");
        primaryStage.setScene(new Scene(parent));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBox();
    }

    @FXML
    private void initBox() {
        List<Group> allGroup = mainService.getAllGroup();
        for (Group group : allGroup) {
            BelongGroup.getItems().add(group.getGroupName());
        }
        BelongGroup.getSelectionModel().selectFirst();
    }

    @FXML
    public void del() {
        if (FriendName.getText() != null) {
            if (mainService.delPersonByName(FriendName.getText()) != 0) {
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
