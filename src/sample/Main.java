package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.constant.Constant;
import sample.controller.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Word Count | Copyright@2020 黄钰朝");
        primaryStage.setScene(new Scene(root, 1460, 900));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        if (null == args ||args.length==0|| Constant.OPS_X.equalsIgnoreCase(args[0])) {
            //启动图形界面
            launch(args);
        } else {
            //命令行界面
            Controller.wc(args);
        }
    }
}
