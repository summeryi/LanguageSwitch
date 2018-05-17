package net.yeah.liliLearne.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SupportLanguageUtil {


    private static Map<String, Locale> mSupportLanguages = new HashMap<String, Locale>(7) {{
        put(LanguageConstants.SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE);//中文
        put(LanguageConstants.TRADITIONAL_CHINESE, Locale.TRADITIONAL_CHINESE);//台湾/繁体
        put(LanguageConstants.ENGLISH, Locale.ENGLISH);//英文

    }};

    /**
     * 是否支持此语言
     *
     * @param language language
     * @return true:支持 false:不支持
     */
    public static boolean isSupportLanguage(String language) {
        return mSupportLanguages.containsKey(language);
    }

    /**
     * 获取支持语言
     *
     * @param language language
     * @return 支持返回支持语言，不支持返回系统首选语言
     */
    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSupportLanguage(String language) {
        if (isSupportLanguage(language)) {
            return mSupportLanguages.get(language);
        }
        return getSystemPreferredLanguage();
    }

    /**
     * 获取系统语言
     * 只有一开始进入APP，才获取到系统语言，否则修改默认语言后，不能做有效判断
     *
     * @return Locale
     */
    public static Locale getSystemPreferredLanguage() {
        Locale locale;
        if (LanguageConstants.FOLLOW_SYSTEM != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                locale = LocaleList.getDefault().get(0);
            } else {
                locale = Locale.getDefault();
            }
            LanguageConstants.FOLLOW_SYSTEM = locale;
            //全局设置系统语言
            return locale;
        } else {
            return LanguageConstants.FOLLOW_SYSTEM;
        }
    }
}
