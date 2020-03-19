package sample.controller;

import sample.constant.Constant;
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
                            count = countService.countCharacter(file);
                            System.out.println("字符数：" + count);
                            break;
                        case Constant.OPS_W:
                            count = countService.countWord(file);
                            System.out.println("单词数：" + count);
                            break;
                        case Constant.OPS_L:
                            count = countService.countLine(file);
                            System.out.println("行数：" + count);
                            break;
                        case Constant.OPS_A:

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
