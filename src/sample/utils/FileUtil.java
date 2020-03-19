package sample.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 用于文件处理
 * @date 2020-03-19 11:37
 */
public class FileUtil {


    /**
     * 将一个文件路径转为BufferedReader对象
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static BufferedReader getBufferedReader(File file) throws Exception {
        if (file.isDirectory()) {
            throw new Exception("不支持读取文件夹，请输入文件路径");
        } else if (!file.exists()) {
            throw new Exception("该文件不存在");
        } else {
            return new BufferedReader(new FileReader(file));
        }
    }
}
