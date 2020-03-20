package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.constant.Constant;
import sample.model.CountResult;
import sample.model.TableItem;
import sample.service.CountService;
import sample.service.impl.CountServiceImpl;
import sample.utils.BeanUtil;
import sample.utils.FileUtil;
import sample.utils.RegexUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private static final CountService countService = new CountServiceImpl();

    @FXML
    private TableView resultTable;
    @FXML
    private TextField command;
    @FXML
    private TableColumn file;
    @FXML
    private TableColumn length;
    @FXML
    private TableColumn word;
    @FXML
    private TableColumn line;
    @FXML
    private TableColumn character;
    @FXML
    private TableColumn emptyLine;
    @FXML
    private TableColumn codeLine;
    @FXML
    private TableColumn annotationLine;
    @FXML
    private TableColumn time;

    @FXML
    private void selectFile() {
        try {
            //显示一个文件选择器
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(stage);
            //统计信息
            long begin = System.currentTimeMillis();
            //把表格的列和TableData的属性进行绑定
            bindProperty();
            //向表格中填充结果
            TableItem tableItem = BeanUtil.toTableItem(countService.countAll(file), file, begin);
            ObservableList<TableItem> items = FXCollections.observableArrayList(tableItem);
            resultTable.setItems(items);
        } catch (Exception e) {
            alert(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 点击页面按钮“执行指令”时运行
     */
    @FXML
    private void run() {
        try {
            bindProperty();

            //处理参数
            String text = command.getText();
            if (null == text || text.trim().isEmpty()) {
                throw new Exception("请先输入正确的指令!");
            }
            String[] args;
            try {
                args = text.split(" ");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("输入的参数个数不符合规则!");
            }

            //运行统计功能
            ObservableList<TableItem> items = null;
            //多文件处理
            if (Constant.OPS_S.equals(args[0].trim())) {
                if (args.length == 2) {
                    List<File> files = FileUtil.listFileByRegex(RegexUtil.toRegex(args[1].trim()));
                    List<TableItem> tableItemList = new LinkedList<>();
                    for (File f : files) {
                        long begin = System.currentTimeMillis();
                        tableItemList.add(BeanUtil.toTableItem(countService.countAll(f), f, begin));
                    }
                    items = FXCollections.observableList(tableItemList);
                } else {
                    throw new Exception("缺少必要的参数，指令示例：-s *.c");
                }
            } else {
                //单文件处理
                File file = new File(args[0].trim());
                long begin = System.currentTimeMillis();
                items = FXCollections.observableArrayList(BeanUtil.toTableItem(countService.countAll(file), file, begin));
            }
            resultTable.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
            alert("提示：" + e.getMessage());
        }
    }


    /**
     * 把表格的列和TableData的属性进行绑定
     */
    private void bindProperty() {
        character.setCellValueFactory(new PropertyValueFactory<>("character"));
        file.setCellValueFactory(new PropertyValueFactory<>("file"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        emptyLine.setCellValueFactory(new PropertyValueFactory<>("emptyLine"));
        word.setCellValueFactory(new PropertyValueFactory<>("word"));
        line.setCellValueFactory(new PropertyValueFactory<>("line"));
        codeLine.setCellValueFactory(new PropertyValueFactory<>("codeLine"));
        annotationLine.setCellValueFactory(new PropertyValueFactory<>("annotationLine"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
    }


    /**
     * 向用户发出提示
     *
     * @param message
     */
    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示信息");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * 根据命令行模式下的参数执行
     *
     * @param args
     */
    public static void wc(String[] args) {
        long begin = System.currentTimeMillis();
        try {
            //多文件处理
            if (Constant.OPS_S.equals(args[0])) {
                if (args.length == 3) {
                    List<File> files = FileUtil.listFileByRegex(RegexUtil.toRegex(args[2]));
                    for (File f : files) {
                        countFile(args[1], f);
                    }
                } else {
                    throw new Exception("缺少必要的参数，指令示例：-s -a *.c");
                }
            } else {
                //单文件处理
                countFile(args[0], new File(args[1]));
            }
            System.out.println("运行耗时：" + (System.currentTimeMillis() - begin) + "ms");
        } catch (Exception e) {
            System.out.println("运行出错：" + e.getMessage());
        }

    }


    /**
     * 统计输出一个文件的信息
     *
     * @param ops  要统计的信息
     * @param file 文件名
     * @throws Exception
     */
    public static void countFile(String ops, File file) throws Exception {
        System.out.println("文件信息：" + BeanUtil.getFileInfo(file));
        switch (ops) {
            case Constant.OPS_C:
                System.out.println("字符数：" + countService.countCharacter(file));
                break;
            case Constant.OPS_W:
                System.out.println("单词数：" + countService.countWord(file));
                break;
            case Constant.OPS_L:
                System.out.println("行数：" + countService.countLine(file));
                break;
            case Constant.OPS_A:
                CountResult result = countService.countAll(file);
                System.out.println("字符数：" + result.character);
                System.out.println("单词数：" + result.word);
                System.out.println("行数：" + result.line);
                System.out.println("空行数：" + result.emptyLine);
                System.out.println("代码行数：" + result.codeLine);
                System.out.println("注释行数：" + result.annotationLine);
                break;
            default:
                throw new Exception("无法识别的指令类型：" + ops);
        }
    }

}
