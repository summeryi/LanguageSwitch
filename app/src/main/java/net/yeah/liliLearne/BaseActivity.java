package net.yeah.liliLearne;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.yeah.liliLearne.utils.LanguageUtil;

import java.util.concurrent.TimeUnit;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = newBase.getSharedPreferences("language", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("language", "");
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, selectedLanguage));
    }


}
