package com.andy.length_filter;

import android.text.InputFilter;
import android.text.Spanned;


/**
 * @author dell
 * @date 2018/4/8.
 */
public class LengthFilter implements InputFilter {
    private int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母
    private LengthWarnListener mLengthWarnListener;

    public LengthFilter(int maxLength) {
        super();
        MAX_EN = maxLength;
    }

    public void setLengthWarnListener(
            LengthWarnListener lengthWarnListener) {
        mLengthWarnListener = lengthWarnListener;
    }

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

    public interface LengthWarnListener {
        void onLengthWarn();
    }
}
