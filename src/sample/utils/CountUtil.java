package sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 用于文本统计功能
 * @date 2020-03-18 17:27
 */
public class CountUtil {

    /**
     * 统计字符串中特定字符的数量
     *
     * @param src
     * @param regex
     * @return
     */
    public static int count(String src, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(src);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
