package net.yeah.liliLearne.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageUtil {

    public static Context attachBaseContext(Context context, String language) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // API 25以后推荐我们使用
            return createConfigurationResources(context, language);
        } else {
            applyLanguage(context, language);
            return context;
        }
    }

    //    API24以下设置语言
    public static void applyLanguage(Context context, String language) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(language)) {//如果没有指定语言使用系统首选语言
            locale = SupportLanguageUtil.getSystemPreferredLanguage();
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = SupportLanguageUtil.getSupportLanguage(language);
        }
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, null);
    }

    //    API24以上设置语言
    @TargetApi(Build.VERSION_CODES.N)
    private static Context createConfigurationResources(Context context, String language) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale;
        if (TextUtils.isEmpty(language)) {//如果没有指定语言使用系统首选语言
            locale = SupportLanguageUtil.getSystemPreferredLanguage();
        } else {//指定了语言使用指定语言，没有则使用首选语言
            locale = SupportLanguageUtil.getSupportLanguage(language);
        }
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }
}
