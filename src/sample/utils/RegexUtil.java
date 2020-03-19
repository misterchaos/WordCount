package sample.utils;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 正则工具类
 * @date 2020-03-19 16:06
 */
public class RegexUtil {


    /**
     * 将输入的通配符转成正则表达式
     *
     * @param src
     * @return
     */
    public static String toRegex(String src) {
        String regex;
        regex = src.replaceAll("\\*", ".*");
        regex = regex.replaceAll("\\?", ".{1}");
        return regex;
    }


}
