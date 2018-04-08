# Length Filter 
这是一个EditText的工具类，主要解决的是开发中常见的对输入长度的限制：例如30个字符/15个中文汉字的需求

## 实现思路
1. 如何计算传长度: ASCII编码肯定是1字符站一个字节，Android中汉字使用的是unicode编码，汉字是占2个字节。因此可以通过检查输入内容的byte长度进行限制

2. 如何监控输入：

 - 通过TextWatcher
 - 通过InputFilter

## 核心代码
这里采用的是InputFilter：
``` java
public class LengthFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end,
            Spanned dest, int dstart, int dend) {
        int destCount = dest.toString().length()
                + TextUtils.countChineseChar(dest.toString());

        int sourceCount = source.toString().length()
                + TextUtils.countChineseChar(source.toString());

        if (destCount + sourceCount > MAX_EN) {
            if (mLengthWarnListener != null) {
                mLengthWarnListener.onLengthWarn();
            }
            return "";
        } else {
            return source;
        }
    }
}
```

统计汉字
``` java
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
```