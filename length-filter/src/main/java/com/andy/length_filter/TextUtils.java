package com.andy.length_filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dell
 * @date 2018/4/8.
 */
public class TextUtils {

    public static final String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字

    public static int countChineseChar(String source) {
        if (android.text.TextUtils.isEmpty(source)) {
            return 0;
        }

        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(source);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }
}
