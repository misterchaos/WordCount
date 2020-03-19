package sample.controller;

import sample.constant.Constant;
import sample.model.CountResult;
import sample.service.CountService;
import sample.service.impl.CountServiceImpl;

import java.io.File;

public class Controller {

    private static final CountService countService = new CountServiceImpl();


    public static void wc(String[] args) {
        long begin = System.currentTimeMillis();
        try {
            switch (args[0]) {
                case Constant.OPS_S:

                    //TODO 统计该目录下符合条件的文件

                default:
                    File file = new File(args[1]);
                    //小于1KB按1KB显示
                    long length = file.length() / 1024 == 0 ? 1 : file.length() / 1024;
                    System.out.println("文件信息：" + file.getAbsolutePath() + "  " + length + "KB");
                    int count;
                    switch (args[0]) {
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
                            break;
                    }
            }
            System.out.println("运行耗时：" + (System.currentTimeMillis() - begin) + "ms");
        } catch (Exception e) {
            System.out.println("运行出错：" + e.getMessage());
        }

    }

}
