package sample.service.impl;

import sample.model.CountResult;
import sample.service.CountService;
import sample.utils.CountUtil;
import sample.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 计数服务实现类
 * @date 2020-03-19 09:17
 */
public class CountServiceImpl implements CountService {


    /**
     * 统计字符数
     *
     * @param file 待查找文件
     * @return 字符数
     */
    @Override
    public int countCharacter(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader).character;
    }

    /**
     * 统计单词数
     *
     * @param file 待查找文件
     * @return
     */
    @Override
    public int countWord(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader).word;
    }

    /**
     * 统计行数
     *
     * @param file 待查找文件
     * @return
     */
    @Override
    public int countLine(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader).line;
    }

    /**
     * 统计空行数
     *
     * @param file 待查找文件
     * @return
     */
    @Override
    public int countEmptyLine(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader).emptyLine;
    }

    /**
     * 统计代码行数
     *
     * @param file 待查找文件
     * @return
     */
    @Override
    public int countCodeLine(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader).codeLine;
    }

    /**
     * 统计注释行数
     *
     * @param file 待查找文件
     * @return
     */
    @Override
    public int countAnnotationLine(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader).annotationLine;
    }

    /**
     * 返回全部统计信息
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public CountResult countAll(File file) throws Exception {
        BufferedReader reader = FileUtil.getBufferedReader(file);
        return count(reader);
    }


    /**
     * 核心统计方法
     *
     * @param reader
     * @return
     * @throws IOException
     */
    private static CountResult count(BufferedReader reader) throws IOException {
        CountResult result = new CountResult();
        String character = "\\w";
        String word = "[a-zA-Z]+";
        String line;
        //多行注释
        boolean isAnnotation = false;
        while ((line = reader.readLine()) != null) {
            //统计一行中的字符数
            result.character += CountUtil.count(line, character);
            //统计一行中的单词数
            result.word += CountUtil.count(line, word);
            //统计行数
            result.line++;
            //统计空行,代码行,注释行
            if (line.trim().isEmpty()) {
                //空行
                result.emptyLine++;
                //开始多行注释
            } else if (isAnnotation) {
                result.annotationLine++;
                //多行注释结束
                if (line.trim().endsWith("*/")) {
                    isAnnotation = false;
                }
            } else if (line.trim().contains("//") || line.trim().contains("*")) {
                //注释行
                result.annotationLine++;
                //如果是行后注释
                if (!(line.trim().startsWith("/") || line.trim().startsWith("*"))) {
                    result.codeLine++;
                }
                //多行注释开始
                if (line.trim().startsWith("/*")) {
                    isAnnotation = true;
                }
            } else {
                //代码行
                result.codeLine++;
            }
        }
        return result;
    }

}
