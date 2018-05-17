package net.yeah.liliLearne;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import net.yeah.liliLearne.adapter.LanguagesAdapter;
import net.yeah.liliLearne.model.LanguageBean;

import java.util.ArrayList;
import java.util.List;

public class LanguageDialog extends DialogFragment {

    RecyclerView recyclerView;
    LanguagesAdapter adapter;
    ImageView imgbtn_toolbar_back;
    private List<LanguageBean> languageList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.dialog_language_layout, container);
        //Do something
        // 设置宽度为屏宽、靠近屏幕底部。
        final Window window = getDialog().getWindow();
        window.setBackgroundDrawableResource(R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //设置dialog的 进出 动画
        getDialog().getWindow().setWindowAnimations(R.style.languagedialog);
        window.setAttributes(wlp);
        initview(view);
        return view;
    }

    private void initview(View view) {
        languageList = new ArrayList<>();
        languageList.add(new LanguageBean(0, "跟随系统", "", false));
        languageList.add(new LanguageBean(1, "简体中文", "zh", false));
        languageList.add(new LanguageBean(2, "繁体中文(台湾)", "zh-hant", false));
        languageList.add(new LanguageBean(3, "English", "en", false));
        SharedPreferences preferences = getActivity().getSharedPreferences("language", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("language", "");

        //设置打勾
        for (int i = 0; i < languageList.size(); i++) {
            if (languageList.get(i).getShortName().equals(selectedLanguage)) {
                languageList.get(i).setSelected(true);
                break;
            }
        }

        adapter = new LanguagesAdapter(getActivity(), languageList);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setmOnItemClickLitener(new LanguagesAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, int preposition) {
                languageList.get(position).setSelected(true);
                languageList.get(preposition).setSelected(false);
                adapter.notifyDataSetChanged();
                SharedPreferences preferences = getActivity().getSharedPreferences("language", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("language", languageList.get(position).getShortName());
                editor.apply();
                dismiss();
                getActivity().recreate();
            }
        });

        imgbtn_toolbar_back = view.findViewById(R.id.imgbtn_toolbar_back);
        imgbtn_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
