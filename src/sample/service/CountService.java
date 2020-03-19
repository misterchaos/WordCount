package sample.service;

import sample.model.CountResult;

import java.io.File;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 计数服务
 * @date 2020-03-19 08:57
 */
public interface CountService {

    /**
     * 统计字符数
     *
     * @param file 待查找文件
     * @return 字符数
     */
    int countCharacter(File file) throws Exception;


    /**
     * 统计单词数
     *
     * @param file 待查找文件
     * @return
     */
    int countWord(File file) throws Exception;


    /**
     * 统计行数
     *
     * @param file 待查找文件
     * @return
     */
    int countLine(File file) throws Exception;


    /**
     * 统计空行数
     *
     * @param file 待查找文件
     * @return
     */
    int countEmptyLine(File file) throws Exception;


    /**
     * 统计代码行数
     *
     * @param file 待查找文件
     * @return
     */
    int countCodeLine(File file) throws Exception;


    /**
     * 统计注释行数
     *
     * @param file 待查找文件
     * @return
     */
    int countAnnotationLine(File file) throws Exception;


    /**
     * 返回全部统计信息
     *
     * @param file
     * @return
     * @throws Exception
     */
    CountResult countAll(File file) throws Exception;


}
