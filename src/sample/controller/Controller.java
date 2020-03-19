package sample.controller;

import sample.constant.Constant;
import sample.model.CountResult;
import sample.service.CountService;
import sample.service.impl.CountServiceImpl;
import sample.utils.FileUtil;
import sample.utils.RegexUtil;

import java.io.File;
import java.util.List;

public class Controller {

    private static final CountService countService = new CountServiceImpl();


    /**
     * 根据命令行模式下的参数执行
     *
     * @param args
     */
    public static void wc(String[] args) {
        long begin = System.currentTimeMillis();
        try {
            switch (args[0]) {
                case Constant.OPS_S:
                    if (args.length == 3) {
                        List<File> files = FileUtil.listFileByRegex(RegexUtil.toRegex(args[2]));
                        for (File f : files) {
                            countFile(args[1], f);
                        }
                    } else {
                        throw new Exception("缺少必要的参数，指令示例：-s -a *.c");
                    }
                    break;
                default:
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
        //小于1KB按1KB显示
        long length = file.length() / 1024 == 0 ? 1 : file.length() / 1024;
        System.out.println("文件信息：" + file.getAbsolutePath() + "  " + length + "KB");
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
                break;
        }
    }

}
