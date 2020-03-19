package sample.utils;

import sample.model.CountResult;
import sample.model.TableItem;

import java.io.File;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 用于处理JavaBean
 * @date 2020-03-19 18:54
 */
public class BeanUtil {


    /**
     * 构建表格中的一行数据
     *
     * @param countResult 统计结果
     * @param file        文件信息
     * @param begin       开始运行的时间
     * @return
     */
    public static TableItem toTableItem(CountResult countResult, File file, long begin) {
        TableItem tableItem = new TableItem();
        tableItem.setCharacter(countResult.character);
        tableItem.setAnnotationLine(countResult.annotationLine);
        tableItem.setCodeLine(countResult.codeLine);
        tableItem.setEmptyLine(countResult.emptyLine);
        tableItem.setWord(countResult.word);
        tableItem.setLine(countResult.line);
        tableItem.setFile(file.getAbsolutePath());
        tableItem.setTime(getRunTime(begin));
        tableItem.setLength(getFileLength(file));
        return tableItem;
    }


    /**
     * 返回文件的信息(路径+大小)
     *
     * @param file
     */
    public static String getFileInfo(File file) {
        return file.getAbsolutePath() + "  " + getFileLength(file);
    }

    /**
     * 返回文件的大小
     *
     * @param file
     */
    public static String getFileLength(File file) {
        long length = file.length();
        if (length < 1024) {
            return length + "B";
        } else if (length < (1024 * 1024)) {
            return length / 1024 + "KB";
        } else if (length < (1024 * 1024 * 1024)) {
            return length / (1024 * 1024) + "MB";
        } else {
            return length / (1024 * 1024 * 1024) + "GB";
        }
    }

    /**
     * 返回运行耗时信息
     *
     * @param begin 开始执行的时间（时间戳毫秒数）
     * @return
     */
    public static String getRunTime(long begin) {
        long time = System.currentTimeMillis() - begin;
        if (time < 1000) {
            return time + "ms";
        } else {
            return time / 1000 + "s";
        }
    }
}
